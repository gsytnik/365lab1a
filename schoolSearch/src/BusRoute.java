import java.util.ArrayList;


public class BusRoute implements Comparable<BusRoute> {
	
	private int routeNumber;
	private ArrayList<Student> studentList;
	
	public BusRoute(int routeNumber, ArrayList<Student> studentList) {
		this.routeNumber = routeNumber;
		this.studentList = studentList;
	}
	
	public int getRouteNumber() {
		return routeNumber;
	}
	
	public void setRouteNumber(int routeNumber) {
		this.routeNumber = routeNumber;
	}
	
	public ArrayList<Student> getStudentList() {
		return studentList;
	}
	
	public void addStudent(Student student) {
		this.studentList.add(student);
	}
	
	public void setStudentList(ArrayList<Student> studentList) {
		this.studentList = studentList;
	}
	
	//override methods____________________________________________
	@Override
	public int compareTo(BusRoute o) {
		
		if (this.routeNumber > o.routeNumber) {
			return 1;
		} else if (this.routeNumber < o.routeNumber) {
			return -1;
		} else {
			return 0;
		}
		
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof BusRoute && 
				this.routeNumber == ((BusRoute) o).getRouteNumber()
				);
		
	}
	
	@Override
	public String toString() {
		String route = "" + this.routeNumber;
		return route;
		
	}
	
}
