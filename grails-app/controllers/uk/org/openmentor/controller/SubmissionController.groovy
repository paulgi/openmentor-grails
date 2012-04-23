package uk.org.openmentor.controller

import grails.plugins.springsecurity.Secured;
import grails.converters.JSON
import java.util.Map;

import org.codehaus.groovy.grails.commons.ConfigurationHolder;
import org.springframework.web.multipart.MultipartFile;

import uk.org.openmentor.courseinfo.Course;
import uk.org.openmentor.data.Assignment;
import uk.org.openmentor.data.Submission;
import uk.org.openmentor.domain.DataBook;

@Secured(['ROLE_OPENMENTOR-USER'])
class SubmissionController {

	def analyzerService
	def assessmentService
	
	private Course getSelectedCourse() {
		def courseId = session.current_course
		def courseInstance = Course.get(courseId)

		if (! courseInstance) {
			redirect(action: "select", controller: "course")
			return
		}
		
		return courseInstance
	}
	
    def index = { 
		redirect(action: "new", params: params)
	}

	def upload = { 
		
		def courseInstance = getSelectedCourse()
		def grades = ConfigurationHolder.config.openmentor.grades;
		[grades: grades, courseInstance: courseInstance]
	}
	
	def show = {
		
		Submission sub = Submission.get(params.id)
		Set<Submission> submissions = [sub] as Set<Submission>
		DataBook book = assessmentService.buildDataBook(submissions)
		
		return [book: book, submissionInstance: sub]
	}
	
	def save = { SubmissionCommand cmd ->
		
		def courseInstance = getSelectedCourse()
		def grades = ConfigurationHolder.config.openmentor.grades;
		
		def model = [grades: grades, courseInstance: courseInstance]
		model.cmd = cmd

		if (cmd.hasErrors()) {
			render(view: "upload", model: model)
			return
		}
			
		log.error("Ready to save and start processing")
		log.error(cmd)
		
		// First of all, we may have the same submission already. This is not hugely
		// well-specified in the previous implementation.
		
		Assignment assignment = Assignment.get(cmd.assignmentId)
		Submission sub = analyzerService.newSubmission(
			assignment,
			[cmd.studentIds] as Set<String>,
			[cmd.tutorIds] as Set<String>,
			cmd.grade,
			cmd.dataFile.getOriginalFilename(),
			cmd.dataFile.getBytes()
		)
		model.submission = sub
		
		if (sub.validate() && sub.save(flush: true)) {
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'submission.label', default: 'Submission')])}"
			redirect(action: "show", id: sub.id)
		} else {
			sub.errors.each {
				log.error(it)
			}
			render(view: "upload", model: model)
		}
	}
	
}

final class SubmissionCommand {
	String courseId
	Integer assignmentId
	String grade
	String studentIds
	String tutorIds
	MultipartFile dataFile
	
	static constraints = {
		dataFile validator: { val, obj ->
			return (val.isEmpty()) ? ['empty.file'] : true
		}
		
		assignmentId(nullable: false)
		courseId(nullable: false)
		grade(nullable: false, blank: false)
		studentIds(nullable: false, blank: false)
		tutorIds(nullable: false, blank: false)
	}
}