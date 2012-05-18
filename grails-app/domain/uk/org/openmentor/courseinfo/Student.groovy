package uk.org.openmentor.courseinfo

class Student implements Comparable<Student>{
	
	String studentId
	String givenName
	String familyName
	
	static hasMany = [ courses: Course ]
	static belongsTo = Course
	
    static constraints = {
		studentId(nullable: false, unique: true)
		givenName(blank: true)
		familyName(blank: true)
    }

	static mapping = {
		id generator:'assigned', name:'studentId'
	}
	
	static transients = ['name', 'idAndName']
	
	String getName() {
		return givenName + " " + familyName
	}

	String getIdAndName() {
		return studentId + " - " + givenName + " " + familyName
	}

	int compareTo(Student other) {
		if(familyName != null && other.familyName!=null)
		{
			int familyCompared = familyName.compareTo(other.familyName)
			
			if(familyCompared != 0) 
				return familyCompared
			else
				if(givenName != null)
					return givenName.compareTo(other.givenName)
		}
		else 
			return 0
	}
}
