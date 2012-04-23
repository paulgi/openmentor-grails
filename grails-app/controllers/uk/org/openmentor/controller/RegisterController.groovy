package uk.org.openmentor.controller

import groovy.text.SimpleTemplateEngine

import org.codehaus.groovy.grails.plugins.springsecurity.NullSaltSource
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.codehaus.groovy.grails.plugins.springsecurity.ui.RegistrationCode

import uk.org.openmentor.courseinfo.Tutor
import uk.org.openmentor.courseinfo.Course
import uk.org.openmentor.auth.User

class RegisterController extends grails.plugins.springsecurity.ui.AbstractS2UiController {
	// override default value from base class
	static defaultAction = 'index'
	
	// override default value from base class
	static allowedMethods = [register: 'POST']
	
	def mailService
	def messageSource
	def saltSource
	
	def index = {
		
		def copy = [:] + (flash.chainedParams ?: [:])
		copy.remove 'controller'
		copy.remove 'action'
		copy.remove 'course'
		copy.remove 'familyName'
		copy.remove 'givenName'
		copy.remove 'tutorId'
		def courseList = Course.list()
		[courseList: courseList,command: new RegisterCommand(copy), tutor:new Tutor()]
	}
	
	def register = { RegisterCommand command, Tutor tutor ->
	
		def courseList = Course.list()
		if (command.hasErrors() || tutor.hasErrors()) {
			render view: 'index', model: [command: command, tutor:tutor,courseList:courseList]
			return
		}
		
		Course course = Course.get(params.course)
		if(!course)
		{
			flash.error = "please select a course"
			render view: 'index', model: [command: command, tutor:tutor,courseList:courseList]
			return
		}
		User.withTransaction{status->
			String salt = saltSource instanceof NullSaltSource ? null : command.username
			def user = lookupUserClass().newInstance(email: command.email, username: command.username,
			accountLocked: true, enabled: true)
			
			tutor.addToCourses(course)
			if(!tutor.save(flush:true))
			{
				flash.error = "Cannot save tutor!"
				flash.chainedParams = params
				redirect action: 'index'
				return
			}
					
			org.codehaus.groovy.grails.plugins.springsecurity.ui.RegistrationCode registrationCode = springSecurityUiService.register(user, command.password, salt)
				if (registrationCode == null || registrationCode.hasErrors()) {
				// null means problem creating the user
				flash.error = message(code: 'spring.security.ui.register.miscError')
				flash.chainedParams = params
				redirect action: 'index'
				return
			}
			
			String url = generateLink('verifyRegistration', [t: registrationCode.token])
			
			def conf = SpringSecurityUtils.securityConfig
			def body = conf.ui.register.emailBody
			if (body.contains('$')) {
				body = evaluate(body, [user: user, url: url])
			}
			mailService.sendMail {
				to command.email
				from conf.ui.register.emailFrom
				subject conf.ui.register.emailSubject
				html body.toString()
			}
		}
		render view: 'index', model: [emailSent: true]
	}
	
	def verifyRegistration = {
	
		def conf = SpringSecurityUtils.securityConfig
		String defaultTargetUrl = conf.successHandler.defaultTargetUrl
		
		String token = params.t
		
		def registrationCode = token ? RegistrationCode.findByToken(token) : null
		if (!registrationCode) {
		flash.error = message(code: 'spring.security.ui.register.badCode')
		redirect uri: defaultTargetUrl
		return
		}
		
		def user
		RegistrationCode.withTransaction { status ->
			user = lookupUserClass().findByUsername(registrationCode.username)
			if (!user) {
				return
			}
			user.accountLocked = false
			user.save(flush:true)
			def UserRole = lookupUserRoleClass()
			def Role = lookupRoleClass()
			for (roleName in conf.ui.register.defaultRoleNames) {
				UserRole.create user, Role.findByAuthority(roleName)
			}
			registrationCode.delete()
		}
		
		if (!user) {
			flash.error = message(code: 'spring.security.ui.register.badCode')
			redirect uri: defaultTargetUrl
			return
		}
		
		springSecurityService.reauthenticate user.username
		
		flash.message = message(code: 'spring.security.ui.register.complete')
		redirect uri: conf.ui.register.postRegisterUrl ?: defaultTargetUrl
	}
		
	def forgotPassword = {
	
	if (!request.post) {
	// show the form
	return
	}
	
	String username = params.username
	if (!username) {
	flash.error = message(code: 'spring.security.ui.forgotPassword.username.missing')
	redirect action: 'forgotPassword'
	return
	}
	
	String usernameFieldName = SpringSecurityUtils.securityConfig.userLookup.usernamePropertyName
	def user = lookupUserClass().findWhere((usernameFieldName): username)
	if (!user) {
	flash.error = message(code: 'spring.security.ui.forgotPassword.user.notFound')
	redirect action: 'forgotPassword'
	return
	}
	
	def registrationCode = new org.codehaus.groovy.grails.plugins.springsecurity.ui.RegistrationCode(username: user."$usernameFieldName")
	registrationCode.save(flush: true)
	
	String url = generateLink('resetPassword', [t: registrationCode.token])
	
	def conf = SpringSecurityUtils.securityConfig
	def body = conf.ui.forgotPassword.emailBody
	if (body.contains('$')) {
	body = evaluate(body, [user: user, url: url])
	}
	mailService.sendMail {
	to user.email
	from conf.ui.forgotPassword.emailFrom
	subject conf.ui.forgotPassword.emailSubject
	html body.toString()
	}
	
	[emailSent: true]
	}
	
	def resetPassword = { ResetPasswordCommand command ->
	
	String token = params.t
	
	def registrationCode = token ? RegistrationCode.findByToken(token) : null
	if (!registrationCode) {
	flash.error = message(code: 'spring.security.ui.resetPassword.badCode')
	redirect uri: SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl
	return
	}
	
	if (!request.post) {
	return [token: token, command: new ResetPasswordCommand()]
	}
	
	command.username = registrationCode.username
	command.validate()
	
	if (command.hasErrors()) {
	return [token: token, command: command]
	}
	
	String salt = saltSource instanceof NullSaltSource ? null : registrationCode.username
	RegistrationCode.withTransaction { status ->
	def user = lookupUserClass().findByUsername(registrationCode.username)
	user.password = springSecurityUiService.encodePassword(command.password, salt)
	user.save()
	registrationCode.delete()
	}
	
	springSecurityService.reauthenticate registrationCode.username
	
	flash.message = message(code: 'spring.security.ui.resetPassword.success')
	
	def conf = SpringSecurityUtils.securityConfig
	String postResetUrl = conf.ui.register.postResetUrl ?: conf.successHandler.defaultTargetUrl
	redirect uri: postResetUrl
	}
	
	protected String generateLink(String action, linkParams) {
	createLink(base: "$request.scheme://$request.serverName:$request.serverPort$request.contextPath",
	controller: 'register', action: action,
	params: linkParams)
	}
	
	protected String evaluate(s, binding) {
	new SimpleTemplateEngine().createTemplate(s).make(binding)
	}
	
	static final passwordValidator = { String password, command ->
	if (command.username && command.username.equals(password)) {
	return 'command.password.error.username'
	}
	
	if (!checkPasswordMinLength(password, command) ||
	!checkPasswordMaxLength(password, command) ||
	!checkPasswordRegex(password, command)) {
	return 'command.password.error.strength'
	}
	}
	
	static boolean checkPasswordMinLength(String password, command) {
	def conf = SpringSecurityUtils.securityConfig
	
	int minLength = conf.ui.password.minLength instanceof Number ? conf.ui.password.minLength : 8
	
	password && password.length() >= minLength
	}
	
	static boolean checkPasswordMaxLength(String password, command) {
	def conf = SpringSecurityUtils.securityConfig
	
	int maxLength = conf.ui.password.maxLength instanceof Number ? conf.ui.password.maxLength : 64
	
	password && password.length() <= maxLength
	}
	
	static boolean checkPasswordRegex(String password, command) {
	def conf = SpringSecurityUtils.securityConfig
	
	String passValidationRegex = conf.ui.password.validationRegex ?:
	'^.*(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&]).*$'
	
	password && password.matches(passValidationRegex)
	}
	
	static final password2Validator = { value, command ->
	if (command.password != command.password2) {
	return 'command.password2.error.mismatch'
	}
	}
}

class RegisterCommand {
	
	String username
	String email
	//Customise
	//Tutor tutor
	String password
	String password2
	
	def grailsApplication
	
	static constraints = {
		username blank: false, nullable: false, validator: { value, command ->
			if (value) {
				def User = command.grailsApplication.getDomainClass(
					SpringSecurityUtils.securityConfig.userLookup.userDomainClassName).clazz
				if (User.findByUsername(value)) {
					return 'registerCommand.username.unique'
				}
			}
		}
		email blank: false, nullable: false, email: true
		password blank: false, nullable: false, validator: RegisterController.passwordValidator
		password2 validator: RegisterController.password2Validator
		//tutor blank:false
	}
}
	
class ResetPasswordCommand {
	String username
	String password
	String password2
	
	static constraints = {
		username nullable: false
		password blank: false, nullable: false, validator: RegisterController.passwordValidator
		password2 validator: RegisterController.password2Validator
	}
}
