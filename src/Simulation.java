import java.util.ArrayList;
public class Simulation {
	//private ArrayList<ArrayList<Integer>> greenLightConfig = new ArrayList<ArrayList<Integer>>();
	private GreenlightConfig greenLightConfig = new GreenlightConfig();
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
		System.out.println("simduration: "  + simDuration);
		System.out.println("intersections: "  + intersections);
		System.out.println("streets: "  + streets);
		System.out.println("cars: "  + cars);
		simulation.setDuration(simDuration);
		
		for (int i = 0; i<intersections;i++) {
			Intersection tmp = new Intersection();
			simulation.getIntersections().add(tmp);
			//System.out.println("intersections: "  + simulation.getIntersections().size());
		}
		int filePointer = 1;
		for (; filePointer<=streets;filePointer++) {
			
			Street s = Street.fromString(_data[filePointer], simulation.getIntersections());
			simulation.getStreets().add(s);
			//System.out.println("streets: "  + simulation.getStreets().size());
		}
		System.out.println(filePointer);
		for (int i =filePointer; i<filePointer+cars;i++) {
			Car c = Car.fromString(_data[i]);
			simulation.getCars().add(c);
			//System.out.println("cars: "  + simulation.getCars().size());
		}
		
		for(Intersection i : simulation.getIntersections()) {
			simulation.getGreenLightConfig().add(i.shuffleGreenTime(simDuration));
		}
		return simulation;
		
	}
	public void simulate() {
		for(int i = 0;i<=this.duration+1;i++) {
			System.out.println("TIME: " + i);
			for(Car c: cars) {
				

				c.tick();
				System.out.println(c.getCurrentStreet().getName());
				System.out.println(c.isParking());
			}
		}
	}
	
	public GreenlightConfig getGreenLightConfig() {
		return greenLightConfig;
	}
	public void setGreenLightConfig(GreenlightConfig greenLightConfig) {
		this.greenLightConfig = greenLightConfig;
		for(int i = 0;i<this.greenLightConfig.getConfig().size();i++) {
			ArrayList<Integer> tmp = new ArrayList<Integer>(greenLightConfig.getConfig().get(i));
			this.intersections.get(i).setGreenTime(tmp);
		}
		
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
