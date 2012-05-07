package uk.org.openmentor.courseinfo

class Tutor implements Comparable<Tutor>{
	
	String tutorId
	String givenName
	String familyName

	static hasMany = [ courses: Course ]
	static belongsTo = Course

    static constraints = {
		tutorId(nullable: false, unique: true)
		givenName(blank: true)
		familyName(blank: true)
    }

	static mapping = {
		id generator:'assigned', name:'tutorId'
	}

	static transients = ['name', 'idAndName']
	
	String getName() {
		return givenName + " " + familyName
	}

	String getIdAndName() {
		return tutorId + " - " + givenName + " " + familyName
	}

	int compareTo(Tutor other) {
		//int familyCompared = familyName.compareTo(other.familyName)
		//return (familyCompared != 0) ? familyCompared : givenName.compareTo(other.givenName)
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
