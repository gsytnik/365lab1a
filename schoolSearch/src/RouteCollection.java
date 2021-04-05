import java.util.ArrayList;
import java.util.TreeSet;

public class RouteCollection {
	private TreeSet<BusRoute> routes;

	public RouteCollection(TreeSet<BusRoute> routes) {
		super();
		this.routes = routes;
	}

	public BusRoute getRoute(BusRoute route) {
		
	    BusRoute ceil  = this.routes.ceiling(route);
	    BusRoute floor = this.routes.floor(route);   
	    return ceil == floor? ceil : null; 
	
	}
	
	public BusRoute getRouteByNumber(int routeNumber) {
		
		return this.getRoute(new BusRoute(routeNumber, new ArrayList<Student>()));
		
	}
	
	public boolean containsRoute(int routeNumber) {
		return this.routes.contains(new BusRoute(routeNumber, new ArrayList<Student>()));
	}

	public void addRoute(BusRoute busRoute) {
		this.routes.add(busRoute);
		
	}
	
}