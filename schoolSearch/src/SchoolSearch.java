import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

public class SchoolSearch {
	
	private static StudentCollection studentCol = new StudentCollection(new HashMap<String, ArrayList<Student>>());
	private static TeacherCollection teacherCol = new TeacherCollection(new HashMap<String, ArrayList<Teacher>>());
	private static RouteCollection routeCol = new RouteCollection(new TreeSet<BusRoute>());
	private static GradeLevelCollection gradeLevelCol = new GradeLevelCollection(new HashMap<Integer, GradeLevel>());
	
	public static void main(String[] args) {
		
		//get data
		ArrayList<String> fileData = readFileDataFrom("students.txt");
		
		//init collections
		
		
		//put data into collection database
		populateCollections(fileData);
		
		System.out.println("Populated Data Succesfully\n");
		
		String response;
		Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
		do {
			
			String input = runGui(scanner);
			
			response = parseInput(input);
			
			System.out.print(response);
			
		} while (null != response);
		System.out.println("\n\nYou have decided to terminate the program");
		scanner.close();
	}
	
	public static String inputPrompt() {
		String prompt = "";
		
		prompt += ("[] = optional input, <> = values you provide\n" +
					"Commands:\n" +
					"S[tudent]: <lastname> [B[us]]\n" +
					"T[eacher]: <lastname>\n" +
					"B[us]: <number>\n" +
					"G[rade]: <number> [H[igh]|L[ow]]\n" +
					"A[verage]: <number>\n" +
					"I[nfo]\n" +
					"Q[uit]\n\n" +
					"Enter a command: "
				   );
		
		return prompt;
		
	}
	
	//gui code
	public static String runGui(Scanner scanner) {
		
		System.out.print(inputPrompt());
        String input = scanner.next().strip();
        System.out.print("You chose: " + input + "\n");
        return input;
	}
	
	//code to parse gui input
	public static String parseInput(String input) {
		
		String output = "";
		String [] commands;

		if (input.equals("I") || input.equals("Info")) 
		{
			output = getGeneralInfo();
			return output + "\n";
		} else if (input.equals("Q") || input.equals("Quit")) 
		{
			return null;
			
		} 
		
		commands = input.split(" ");
		
		//student
		if (commands[0].equals("S:") || commands[0].equals("Student:")) 
		{
			
			if (commands.length == 2) 
			{
				output = findStudentsByName(commands[1], false);
			} else if (
						commands.length >= 3 && 
						(commands[2].equals("B") || commands[2].equals("Bus"))
					  ) 
			{
				
				output = findStudentsByName(commands[1], true);
				
			} else 
			{
				output = "Invalid Command";
			}
			
		} else if (commands[0].equals("T:") || commands[0].equals("Teacher:")) 
		{
			if(commands.length == 2) {
				output = findTeachersByName(commands[1]);
			} else {
				output = "Invalid Command";
			}
			
		} else if (commands[0].equals("B:") || commands[0].equals("Bus:")) 
		{
			
			if(commands.length == 2) {
				output = findBusRoute(Integer.parseInt(commands[1]));
			} else {
				output = "Invalid Command";
			}
			
			
		} else if (commands[0].equals("G:") || commands[0].equals("Grade:")) 
		{
			
			if (commands.length>=2) {
				boolean byGrade = false;
				int gradeLevel = Integer.parseInt(commands[1]);
				if (commands.length >= 3) {
					
					byGrade = true;
					
					if(commands[2].equals("H") || commands[2].equals("High")) 
					{
						
						output = findGradeLevelInfo(gradeLevel, byGrade, true);
						
					} else if (commands[2].equals("L") || commands[2].equals("Low")) 
					{
						
						output = findGradeLevelInfo(gradeLevel, byGrade, false);
						
					} else 
					{
						
						output = "Invalid Command";
						
					}
				
					
				} else {
				
					output = findGradeLevelInfo(gradeLevel, byGrade, false);
				
				}
			
			} else {
				output = "Invalid Command";
			}
			
		} else if ((commands[0].equals("A:") || commands[0].equals("Average:")) && commands.length == 2) 
		{
			output = findGradeAverage(Integer.parseInt(commands[1]));
		} else {
			output = "Invalid Command";
		}
		
		return output + "\n";	
		
		
	}
	
	//queries START----------------------------------------------
	
	//returns number of students in grades 0-6 by grade
	public static String getGeneralInfo() {
		
		String output = "Number of students by grade level:\n";
		for (int level = 0; level < 7; level ++) {
			GradeLevel gradeLevel = gradeLevelCol.getGradeLevel(level);
			if (null != gradeLevel) {
				output += "\nGrade " + gradeLevel + ": " + gradeLevel.numStudents();
			} else {
				output += "\nGrade " + level + ": None";
			}
			
		}
		
		output += "\n";
		return output;
		
	}
	
	/*
	 * Returns A string containing the following information about students with matching last name:
	 * 
	 * last name, first name, grade, classroom, teacher (last name, first name)
	 * 
	 * if routeInfo, instead:
	 * Last name, first name, bus route number.
	 */
	public static String findStudentsByName(String lastName, boolean routeInfo) {
		
		String output = "";
		//get students from studentcol
		ArrayList<Student> students = studentCol.getStudentsByLastName(lastName);
		
		
		if (null == students) {
			output += "No such student\n";
			return output;
		}
		
		output += "Students:\n";
		
		if (routeInfo) {
			//add each students name and info to output. busRoute variant.
			for(Student student : students) {
				output += ("\n"+ 
						   student + " " +
						   student.getBusRoute()
						  );
			}
			
		} else {
				
			//add each students name and info to output. default.
			for(Student student : students) {
				output += ("\n"+ 
						   student + ", " +
						   student.getGradeLevel() + ", " +
						   student.getTeacher().getClassRoom() + ", " +
						   student.getTeacher()
						  );
			}
		}
		
		output += "\n";
		
		return output;
	}
	
	//returns teachers and a list of students in the teachers' classes based on teacher last name.
	public static String findTeachersByName(String lastName) {
		
		String output = "";
		//get teachers from teachercol
		ArrayList<Teacher> teachers = teacherCol.findTeachers(lastName);
		
		
		if (null == teachers) {
			output += "No such teacher\n";
			return output;
		}
		
		//iterate over teachers, build string of teachers and students
		for (Teacher teacher : teachers) {
			//add teacher name (Last, First)
			output += ("\nTeacher Name: " + teacher);
			output += "\n";
			
			//get all students under that teacher
			ArrayList<Student> students = teacher.getStudents();
			output += "Students:\n";
			//add each students name to the list
			for(Student student : students) {
				output += ("\n"+ student);
			}
			
			output += "\n";
			
		}
		
		return output;
	}
	
	
	/* returns the following information given a bus route number:
	 * 
	 * For each student taking the busRoute:
	 * Name (Last, first), grade, classrom.
	 * 
	 */
	public static String findBusRoute(int routeNumber) {
		
		String output = "";
		
		BusRoute route = routeCol.getRouteByNumber(routeNumber);
		//check if busroute exists
		if (null == route) {
			output += "No such bus route\n";
			return output;
		}
		
		output += "Students taking bus route " + routeNumber + ":\n";
		ArrayList<Student> students = route.getStudentList();
			
		//add each students name to the list
		for(Student student : students) {
			output += ("\n"+ 
		                student +
		                student.getGradeLevel().getGradeLevel() + ", " +
						student.getTeacher().getClassRoom()
		              );
		}
		
		output += "\n";
			
		
		
		return output;
	}
	
	/*Returns the following info about a grade level:
	 * 
	 * if byGrade user wants either highest or lowest gpa student:
	 * whether looking for high or low gpa is 3rd param.
	 * 
	 * returns GPA value, student name(last, first), teacher name(Last, first), bus route
	 * 
	 * else:
	 * returns list of names of all students in that grade.
	 */
	public static String findGradeLevelInfo(int gradeLevel, boolean byGrade, boolean high) {
		String output = "";
		
		GradeLevel grade = gradeLevelCol.getGradeLevel(gradeLevel);
		if (null == grade) {
			output += "No such grade\n";
			return output;
		}
		
		if (byGrade) {
			if (high) {
				output += grade.getHighestGradeInfo();
			} else {
				output += grade.getLowestGradeInfo();
			}
		} else {
			
			output += "Students in grade level " + gradeLevel + ":\n";
			ArrayList<Student> students = grade.getStudents();
			for(Student student : students) {
				output += ("\n"+ student);
			}
		}
		
		output += "\n";
		
		
		return output;
	}
	
	//gets average gpa for a given grade level if grade exists
	public static String findGradeAverage(int gradeLevel) {
		String output = "";
		
		GradeLevel grade = gradeLevelCol.getGradeLevel(gradeLevel);
		if (null == grade) {
			output += "No such grade\n";
			return output;
		}
		
		output += "Average GPA for Gradelevel " + gradeLevel + ": " + grade.getAverageGpa();
		
		output += "\n";
		
		return output;
	}
	
	//queries END ----------------------------------------------
	
	//code for filling collections using a line of data from the file in readFileDataFrom
	public static void populateCollections(ArrayList<String> fileData) {
		/*
		 * Each line formatted as follows: 
		 * [0] student last name
		 * [1] student first name
		 * [2] grade level
		 * [3] teacher classroom number
		 * [4] bus route
		 * [5] gpa
		 * [6] teacher last name
		 * [7] teacher first name
		*/
		for(String line : fileData){
			
			//break line into string array with above format
	        String [] info = line.split(",");
	        int routeNumber = Integer.parseInt(info[4]);
	        int gradeLevel = Integer.parseInt(info[2]);
	        int roomNumber = Integer.parseInt(info[3]);
	        double gpa = Double.parseDouble(info[5]);
	        
	        //add teacher
	        Teacher teacher;
	        if (!teacherCol.containsTeacher(info[6], info[7])) {
	        	teacherCol.addTeacher(new Teacher(info[7], info[6], new ArrayList<Student>(), roomNumber));
	        }
	        teacher = teacherCol.getTeacherByName(info[6], info[7]);
	        System.out.print(teacher.getLastName() + "\n");
	        
	        //add bus route
	        BusRoute busRoute;
	      
	        if (routeCol.containsRoute(routeNumber)) {
	        	busRoute = routeCol.getRouteByNumber(routeNumber);
	        	
	        } else {
	        	busRoute = new BusRoute(routeNumber, new ArrayList<Student>());
	        	routeCol.addRoute(busRoute);
	        }
	        
	        //add gradelevel to gradelevels. will only add if not already in the collection
	        gradeLevelCol.addGradeLevel(gradeLevel);
	        
	        //create new student
	        //gradelevel, first name, lastname, busroute, gpa, teacher
	        Student student = new Student(gradeLevelCol.getGradeLevel(gradeLevel), info[1], info[0], busRoute, gpa, teacher);
	        
	        //add student to student collection
	        studentCol.addStudent(student);
	        
	        //add student to teacher
	        teacher.addStudent(student);
	        
	        //add student to route
	        busRoute.addStudent(student);
	        
	        //add student to gradelevel
	        gradeLevelCol.getGradeLevel(gradeLevel).addStudent(student);;
	    }
		
		for (int gradeLevel=0; gradeLevel<13; gradeLevel++) {
			GradeLevel grade = gradeLevelCol.getGradeLevel(gradeLevel);
			if (null != grade) {
				grade.sortStudentsByGpa();
			}
		}
		
		
	}
	
	//obtain all the strings from the file
	public static ArrayList<String> readFileDataFrom(String fileName) {
		ArrayList<String> data = new ArrayList<>();
		try {
		      File file = new File(fileName);
		      Scanner fileReader = new Scanner(file);
		      
		      while (fileReader.hasNextLine()) {
		        String line = fileReader.nextLine();
		        data.add(line);
		      }
		      
		      fileReader.close();
		      
		      
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return data;
	}
}
