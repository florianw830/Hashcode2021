import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class Simulation implements Comparable<Simulation>{
	//private ArrayList<ArrayList<Integer>> greenLightConfig = new ArrayList<ArrayList<Integer>>();
	private GreenlightConfig greenLightConfig = new GreenlightConfig();
	private Order order = new Order();
	private ArrayList<Intersection> intersections = new ArrayList<Intersection>();
	private ArrayList<Street> streets = new ArrayList<Street>();
	private ArrayList<Car> cars = new ArrayList<Car>();
	private int duration;
	private int finishScore=0;
	
	
	
	//public GreenlightConfig mutateG() {
		//greenLightConfig.setDuration(duration);
		//return greenLightConfig.mutate();
	//}
	//public Order mutateO() {
		//return order.mutate();
	//}
	
	public static Simulation fromString(String data, boolean gc,boolean toOne) {
		String[] _data = data.split("\n");
		Simulation simulation = new Simulation();
		int simDuration = Integer.parseInt(_data[0].split(" ")[0]);
		int intersections = Integer.parseInt(_data[0].split(" ")[1]);
		int streets = Integer.parseInt(_data[0].split(" ")[2]);
		int cars = Integer.parseInt(_data[0].split(" ")[3]);
		int score = Integer.parseInt(_data[0].split(" ")[4]);
		//System.out.println("simduration: "  + simDuration);
		//System.out.println("intersections: "  + intersections);
		//System.out.println("streets: "  + streets);
		//System.out.println("cars: "  + cars);
		simulation.setDuration(simDuration);
		simulation.setFinishScore(score);
		
		for (int i = 0; i<intersections;i++) {
			Intersection tmp = new Intersection();
			tmp.setIsId(i);
			simulation.getIntersections().add(tmp);
			//System.out.println("intersections: "  + simulation.getIntersections().size());
		}
		int filePointer = 1;
		for (; filePointer<=streets;filePointer++) {
			
			Street s = Street.fromString(_data[filePointer], simulation.getIntersections());
			simulation.getStreets().add(s);
			//System.out.println("streets: "  + simulation.getStreets().size());
		}
		//System.out.println(filePointer);
		int carId = 0;
		for (int i =filePointer; i<filePointer+cars;i++) {
			Car c = Car.fromString(_data[i]);
			c.setCarID(carId);
			carId++;
			simulation.getCars().add(c);
			//System.out.println("cars: "  + simulation.getCars().size());
		}
		if(gc) {
			if(toOne) {
				simulation.toOne();
			}else {
				simulation.shuffle();
			}
			
			
			simulation.numOrder();
		}
		//simulation.toOne();
		return simulation;
		
	}
	public void restart() {
		this.finishScore = 0;
		for(Car c: this.cars) {
			c.restart();
		}
		//this.order = this.mutateO();
		//this.greenLightConfig = this.mutateG();
	}
	
	private void numOrder() {
		this.order = new Order();
		this.order.setCrosoverStable(true);
		this.order.setCrosoverType(Crosover.Ordered);
		this.order.setMutationRate(0.1);
		this.order.setMutationType(Mutation.Flip);
		for(Intersection i : getIntersections()) {
			order.add(i.numOrder());
		}
	}
	
	private void toOne() {
		this.greenLightConfig = new GreenlightConfig();
		this.greenLightConfig.setCrosoverStable(false);
		this.greenLightConfig.setCrosoverType(Crosover.SinglePoint);
		this.greenLightConfig.setMutationRate(0.1);
		this.greenLightConfig.setMutationType(Mutation.RND);
		for(Intersection i : getIntersections()) {
			
			getGreenLightConfig().add(i.setGreenTimeToOne());
		}
	}
	private void shuffle() {
		this.greenLightConfig = new GreenlightConfig();
		this.greenLightConfig = new GreenlightConfig();
		this.greenLightConfig.setCrosoverStable(false);
		this.greenLightConfig.setCrosoverType(Crosover.SinglePoint);
		this.greenLightConfig.setMutationRate(0.1);
		this.greenLightConfig.setMutationType(Mutation.RND);
		for(Intersection i : getIntersections()) {
			getGreenLightConfig().add(i.shuffleGreenTime(this.duration));
			//simulation.getGreenLightConfig().add(i.setGreenTimeToOne());
		}
	}
	public int simulate() {
		for(int i = 0;i<=this.duration;i++) {
			//System.out.println("TIME: " + (i));
			for(Car c: cars) {
				c.tick(i);
			}
		}
		int score =0;
		for(Car c : cars) {
			score+= c.getScore(finishScore,duration);
		}
		finishScore =score;
		return score;
	}
	@Override
	public int compareTo(Simulation o) {
		if(this.finishScore < o.finishScore) {
			return 1;
		}
		if(this.finishScore> o.finishScore) {
			return -1;
		}
		return 0;
	}
	
	public GreenlightConfig getGreenLightConfig() {
		return greenLightConfig;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Chromosom c,boolean mutate) {
		Order o =new Order(c);
		if(mutate) {
			o.mutate();
		}
		setOrder(o);
	}
	
	public void setGreenlightConfig(Chromosom c,boolean mutate) {
		GreenlightConfig g = new GreenlightConfig(c);
		g.setDuration(duration);
		if(mutate) {
			g.mutate();
		}
		setGreenLightConfig(g);
	}
	
	public void setOrder(Order order) {
		this.order =order;
		for(int i = 0;i<this.order.getConfig().size();i++) {
			ArrayList<Integer> tmp = new ArrayList<Integer>(order.getConfig().get(i));
			this.intersections.get(i).setOrder(tmp);
		}
	}
	
	public void setGreenLightConfig(GreenlightConfig greenLightConfig) {
		this.greenLightConfig = greenLightConfig;
		for(int i = 0;i<this.greenLightConfig.getConfig().size();i++) {
			ArrayList<Integer> tmp = new ArrayList<Integer>(greenLightConfig.getConfig().get(i));
			this.intersections.get(i).setGreenTime(tmp);
		}
		
	}
	
	public String createFileContent() {
		String retVal = this.intersections.size() + "\n";
		for(Intersection i : this.intersections) {
			//System.out.println(i.getIsId() + " " + i.getGreenTime().size());
			retVal+= i.getIsId() + "\n";
			retVal+= i.getStreetToNumber().size() + "\n";
			for(int n=0;n<i.getGreenTime().size();n++) {
				//System.out.println("n" + n);
				//System.out.println(i.getOrder().size());
				//System.out.println(i.getGreenTime().size());
				retVal += i.getInStreets().get(i.getOrder().get(n)).getName(); 
				retVal += " " + i.getGreenTime().get(i.getOrder().get(n)) + "\n"; 
			}
			/*
			for (HashMap.Entry<String, Integer> entry : i.getStreetToNumber().entrySet()) {
				retVal += entry.getKey() + " " + i.getGreenTime().get(entry.getValue()) + "\n";
			}
			*/
			
			
		}
		return retVal;
		
	}
	
	
	public ArrayList<Intersection> getIntersections() {
		return intersections;
	}
	public void setIntersections(ArrayList<Intersection> intersections) {
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
	public int getFinishScore() {
		return finishScore;
	}
	public void setFinishScore(int finishScore) {
		this.finishScore = finishScore;
	}
	

	
}
