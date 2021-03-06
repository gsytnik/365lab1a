import java.util.ArrayList;
import java.util.HashMap;

public class StudentCollection {
	private HashMap<String, ArrayList<Student>> studentList;

	public StudentCollection(HashMap<String, ArrayList<Student>> studentList) {
		super();
		this.studentList = studentList;
	}
	
	public void addStudent(Student student) {
		
		if (!this.studentList.containsKey(student.getLastName())) {
			this.studentList.put(student.getLastName(), new ArrayList<Student>());
		}
		this.studentList.get(student.getLastName()).add(student);
		
	}
	
	public ArrayList<Student> getStudentsByLastName(String lastName) {
		
		return this.studentList.get(lastName);

	}
		

}
