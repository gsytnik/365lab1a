
public class Student implements Comparable<Student> {
	
	private GradeLevel gradeLevel;
	private String firstName;
	private String lastName;
	private BusRoute busRoute; 
	private double gpa;
	private Teacher teacher;
	
	public Student(GradeLevel gradeLevel, String firstName, String lastName, BusRoute busRoute, double gpa, 
			Teacher teacher) {
		this.gradeLevel = gradeLevel;
		this.firstName = firstName;
		this.lastName = lastName;
		this.busRoute = busRoute;
		this.gpa = gpa;
		this.teacher = teacher;
	}

	public GradeLevel getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(GradeLevel gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public BusRoute getBusRoute() {
		return busRoute;
	}

	public void setBusRoute(BusRoute busRoute) {
		this.busRoute = busRoute;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Override
	public int compareTo(Student o) {
		return (this.lastName + this.firstName).compareTo(o.lastName + firstName);
	}
	
	@Override
	public String toString() {
		return this.lastName + ' ' + this.firstName;
		
	}
		
	
}
