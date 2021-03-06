import java.util.ArrayList;

public class Teacher {
	
	private String firstName;
	private String lastName;
	private ArrayList<Student> students;
	private int classRoom;
	
	public Teacher(String firstName, String lastName, ArrayList<Student> students, int classRoom) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.students = students;
		this.classRoom = classRoom;
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
	
	public ArrayList<Student> getStudents() {
		return students;
	}
	
	public void addStudent(Student student) {
		this.students.add(student);
	}
	
	public int getClassRoom() {
		return classRoom;
	}
	
	public void setClassRoom(int classRoom) {
		this.classRoom = classRoom;
	}
	
	@Override
	public String toString() {
		return this.lastName + ' ' + this.firstName;
		
	}
	
	
}
