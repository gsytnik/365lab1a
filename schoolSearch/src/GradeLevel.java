import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class GradeLevel {
	private HashMap<String, Double> gradeInfo;
	private ArrayList<Student> students;
	private int gradeLevel;
	
	public GradeLevel(int gradeLevel, ArrayList<Student> students) {
		super();
		this.setGradeLevel(gradeLevel);
		this.students = students;
		
		/*
		 * Set up HashMap with 3 keys 
		 * low = lowest gpa in grade level
		 * high = highest gpa in grade level
		 * average = average gpa for grade
		*/
		this.gradeInfo = new HashMap<String, Double>();
		this.gradeInfo.put("low", null);
		this.gradeInfo.put("high", null);
		this.gradeInfo.put("average", 0.0);
		
	}
	
	public void addStudent(Student student) {
		this.students.add(student);
		
		if (null == this.gradeInfo.get("low") || this.gradeInfo.get("low") > student.getGpa()) {
			this.gradeInfo.replace("low", student.getGpa());
		}
		
		if (null == this.gradeInfo.get("high") || this.gradeInfo.get("high") < student.getGpa()) {
			this.gradeInfo.replace("high", student.getGpa());
		}
		
		//update class average
		double average = this.gradeInfo.get("average");
		int numStudents = this.students.size();
		average *= (numStudents - 1);
		average += student.getGpa();
		average /= numStudents;
		this.gradeInfo.replace("average", average);
	}
	
	public String getHighestGradeInfo() {
		String info = "Highest GPA: ";
		double highGpa = this.gradeInfo.get("high");
		
		info = info + String.valueOf(highGpa) + "\nStudents: ";
		
		//get all students with the highest Gpa value.
		for (int idx = this.students.size() - 1; idx > 0; idx--) {
			Student student = this.students.get(idx);
			if (student.getGpa() == highGpa) {
				info = (info + student + ", "
					+ student.getTeacher() + ", "
					+ student.getBusRoute()
					+ "\n"); 
			} else if (student.getGpa() < highGpa) {
				break;
			}
		}
		
		return info;
	}
	
	public String getLowestGradeInfo() {
		String info = "Lowest GPA: ";
		double lowGpa = this.gradeInfo.get("low");
		
		info = info + String.valueOf(lowGpa) + "\nStudents: ";
		
		//get all students with the lowest Gpa value.
		for (int idx = 0; idx < this.students.size() - 1; idx++) {
			Student student = this.students.get(idx);
			if (student.getGpa() == lowGpa) {
				info = (info + student + ", "
					+ student.getTeacher() + ", "
					+ student.getBusRoute()
					+ "\n"); 
			} else if (student.getGpa() > lowGpa) {
				break;
			}
		}
		
		return info;
	}
	
	public void sortStudentsByGpa() {
		Comparator<Student> comp = Comparator.comparing(Student::getGpa);
		Collections.sort(this.students, comp);
	}

	public int getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(int gradeLevel) {
		this.gradeLevel = gradeLevel;
	}
	
	public int numStudents(){
		return this.students.size();
	}
	
	public double getAverageGpa() {
		double roundedGpa = round(this.gradeInfo.get("average"), 2);
		return roundedGpa;
	}
	
	//helper for rounding up gpa to 2 decimal places
	private static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(Double.toString(value));
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		String grade = "" + this.gradeLevel;
		return grade;
		
	}
}
