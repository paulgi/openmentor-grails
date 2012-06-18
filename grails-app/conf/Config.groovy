// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if(System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// whether to install the java.util.logging bridge for sl4j. Disable for AppEngine!
grails.logging.jul.usebridge = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

grails.config.locations = [ 
	"classpath:${appName}-config.properties",
	"classpath:${appName}-config.groovy",
	"file:${userHome}/.grails/${appName}-config.properties",
	"file:${userHome}/.grails/${appName}-config.groovy"
]

//email server settings
grails.mail.default.from="kclopenmentor@gmail.com"
//Change to your own email configuration
grails {
	mail {
	  host = "smtp.gmail.com"
	  port = 465
	  username = "kclopenmentor@gmail.com"
	  password = "kcl0penment0r"
	  props = ["mail.smtp.auth":"true",
			   "mail.smtp.socketFactory.port":"465",
			   "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
			   "mail.smtp.socketFactory.fallback":"false"]
 
	} 
}
//spring security settings
grails.plugins.springsecurity.ui.register.defaultRoleNames = ['ROLE_OPENMENTOR-USER']
grails.plugins.springsecurity.ui.register.postRegisterUrl = '/'
grails.plugins.springsecurity.ui.password.validationRegex = '^.*(?=.*\\d)(?=.*[a-zA-Z]).*$'

// set per-environment serverURL stem for creating absolute links
environments {
    production {
        //grails.serverURL = "http://lslvm-pz3.ecs.soton.ac.uk:8080/${appName}"
		//grails.serverURL = "http://localhost:8080/${appName}"
		grails.serverURL = "http://openmentor.kcl.ac.uk/${appName}"
    }
    development {
        grails.serverURL = "http://localhost/${appName}"
    }
    test {
        grails.serverURL = "http://localhost/${appName}"
    }
}

// log4j configuration
log4j = {
    
	appenders {
        console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    }
	
	debug  'grails.app.controller',
           'grails.app.domain'

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'

    warn   'org.mortbay.log'
}

//grails.plugins.springsecurity.rejectIfNoRule = false

grails.plugins.springsecurity.roleHierarchy = '''
ROLE_OPENMENTOR-ADMIN > ROLE_OPENMENTOR-USER
'''

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'uk.org.openmentor.auth.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'uk.org.openmentor.auth.UserRole'
grails.plugins.springsecurity.authority.className = 'uk.org.openmentor.auth.Role'

// Added for the Spring Security LDAP plugin:
//grails.plugins.springsecurity.ldap.context.managerDn = 'uid=admin,ou=system'
//grails.plugins.springsecurity.ldap.context.managerPassword = 'secret'
//grails.plugins.springsecurity.ldap.context.anonymousReadOnly=true
//grails.plugins.springsecurity.ldap.context.server = 'ldap://localhost:1389/'
//grails.plugins.springsecurity.ldap.search.base = 'dc=example,dc=com'
//grails.plugins.springsecurity.ldap.search.filter = '(uid={0})'
//grails.plugins.springsecurity.ldap.authorities.groupSearchBase = 'ou=Groups,dc=example,dc=com'
//grails.plugins.springsecurity.ldap.authorities.groupSearchFilter = 'uniqueMember={0}'
//grails.plugins.springsecurity.password.algorithm = 'SHA-256'
//grails.plugins.springsecurity.ldap.authorities.retrieveDatabaseRoles = true

// This exploits the significantly more flexible handling of types in Grails to 
// allow configuration down to the grading scheme. 
openmentor {
	grades = [
		"A", "B", "C", "D", "E", "F"
	]
	categories = [
		"A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3", "D1", "D2", "D3"
	]
	bands = [
		"A", "B", "C", "D"
	]
	bandLabels = [
		"A": "Positive comments",
		"B": "Teaching points",
		"C": "Questions",
		"D": "Negative comments"	
	]
	categoryBands = [
		"A1": "A",
		"A2": "A",
		"A3": "A",
		"B1": "B",
		"B2": "B",
		"B3": "B",
		"C1": "C",
		"C2": "C",
		"C3": "C",
		"D1": "D",
		"D2": "D",
		"D3": "D"
	]
	weights = [
		"A": [
			"A": 0.30,
            "B": 0.50,
			"C": 0.15,
			"D": 0.05
		],
		"B": [
			"A": 0.25,
            "B": 0.50,
			"C": 0.20,
			"D": 0.05
		],
		"C": [
			"A": 0.15,
            "B": 0.60,
			"C": 0.20,
			"D": 0.05
		],
		"D": [
			"A": 0.10,
            "B": 0.60,
			"C": 0.25,
			"D": 0.05
		],
		"E": [
			"A": 0.05,
            "B": 0.65,
			"C": 0.25,
			"D": 0.05
		],
		"F": [
			"A": 0.05,
            "B": 0.65,
			"C": 0.25,
			"D": 0.05
		],
	]
}
