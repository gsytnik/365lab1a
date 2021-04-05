import java.util.ArrayList;
import java.util.HashMap;

public class GradeLevelCollection {
	private HashMap<Integer, GradeLevel> gradeLevels;

	public GradeLevelCollection(HashMap<Integer, GradeLevel> gradeLevels) {
		super();
		this.gradeLevels = gradeLevels;
	}
	
	public void addGradeLevel(int gradeLevel) {
		
		if (!this.gradeLevels.containsKey(gradeLevel)) {
			this.gradeLevels.put(gradeLevel, new GradeLevel(gradeLevel, new ArrayList<Student>()));
		}
		
	}
	
	public GradeLevel getGradeLevel(int gradeLevel) {
		
		return this.gradeLevels.get(gradeLevel);
		
	}
}
