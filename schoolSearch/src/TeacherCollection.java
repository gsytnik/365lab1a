import java.util.ArrayList;
import java.util.HashMap;

public class TeacherCollection {
	private HashMap<String, ArrayList<Teacher>> teacherList;

	public TeacherCollection(HashMap<String, ArrayList<Teacher>> teacherList) {
		super();
		this.teacherList = teacherList;
	}
	
	public void addTeacher(Teacher teacher) {
		
		if (!this.teacherList.containsKey(teacher.getLastName())) {
			this.teacherList.put(teacher.getLastName(), new ArrayList<Teacher>());
		}
		this.teacherList.get(teacher.getLastName()).add(teacher);
		
	}
	
	public boolean containsTeacher(String lastName, String firstName) {
		ArrayList<Teacher> teachers = this.teacherList.get(lastName);
		if (null == teachers) {
			return false;
		}
		
		for (Teacher t : teachers) {
			if (t.getFirstName().equals(firstName)) {
				return true;
			}
		}
		
		return false;
	}
	
	public Teacher getTeacherByName(String lastName, String firstName) {
		ArrayList<Teacher> teachers = this.teacherList.get(lastName);
		
		for (Teacher t : teachers) {
			if (t.getFirstName().equals(firstName)) {
				return t;
			}
		}
		
		return null;
	}
	
	public ArrayList<Teacher> findTeachers(String lastName) {
		return this.teacherList.get(lastName);
	}
	
	
}
