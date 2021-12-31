import java.util.ArrayList;
public class Simulation {
	private ArrayList<ArrayList<Integer>> greenLightConfig = new ArrayList<ArrayList<Integer>>();
	private ArrayList<Intersection> intersections = new ArrayList<Intersection>();
	private ArrayList<Street> streets = new ArrayList<Street>();
	private ArrayList<Car> cars = new ArrayList<Car>();
	private int duration;
	
	public static Simulation fromString(String data) {
		String[] _data = data.split("\n");
		Simulation simulation = new Simulation();
		int simDuration = Integer.parseInt(_data[0].split(" ")[0]);
		int intersections = Integer.parseInt(_data[0].split(" ")[1]);
		int streets = Integer.parseInt(_data[0].split(" ")[2]);
		int cars = Integer.parseInt(_data[0].split(" ")[3]);
	
		simulation.setDuration(simDuration);
		
		for (int i = 0; i<intersections;i++) {
			Intersection tmp = new Intersection();
			simulation.getIntersections().add(tmp);
		}
		int filePointer = 1;
		for (; filePointer<streets;filePointer++) {
			Street s = Street.fromString(_data[filePointer], simulation.getIntersections());
			simulation.getStreets().add(s);
		}
		
		for (int i =0; i+filePointer<cars;i++) {
			Street s = Street.fromString(_data[i+filePointer], simulation.getIntersections());
			Car c = Car.fromString(_data[i+filePointer]);
			simulation.getCars().add(c);
		}
		return simulation;
		
	}
	public ArrayList<ArrayList<Integer>> getGreenLightConfig() {
		return greenLightConfig;
	}
	public void setGreenLightConfig(ArrayList<ArrayList<Integer>> greenLightConfig) {
		this.greenLightConfig = greenLightConfig;
	}
	private ArrayList<Intersection> getIntersections() {
		return intersections;
	}
	private void setIntersections(ArrayList<Intersection> intersections) {
		this.intersections = intersections;
	}
	public ArrayList<Street> getStreets() {
		return streets;
	}
	public void setStreets(ArrayList<Street> streets) {
		this.streets = streets;
	}
	public ArrayList<Car> getCars() {
		return cars;
	}
	public void setCars(ArrayList<Car> cars) {
		this.cars = cars;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
}
