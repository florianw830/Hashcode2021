import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Street {
	private String name;
	private int runtime = 0;
	private ArrayList<Car> queue;
	private Intersection inIntersection;
	public static HashMap<String, Street> streets = new HashMap<String,Street>();
	
	public static Street fromString(String str, ArrayList<Intersection> intersections) {
		String[] tmp = str.split(" ");
		int isOut = Integer.parseInt(tmp[0]);
		int isIn = Integer.parseInt(tmp[1]);
		int rt = Integer.parseInt(tmp[3]);
		
		Street retVal = new Street();
		
		retVal.setName(tmp[2]);
		retVal.setRuntime(rt);
		
		streets.put(tmp[2], retVal);
		//System.out.println(retVal.name + " out " + isOut + " in "+ isIn);
		intersections.get(isOut).addOutStreet(retVal);
		intersections.get(isIn).addInStreet(retVal);	
		
		return retVal;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public Intersection getInIntersection() {
		return inIntersection;
	}

	public void setInIntersection(Intersection inIntersection) {
		this.inIntersection = inIntersection;
	}

	public ArrayList<Car> getQueue() {
		return queue;
	}

	public void setQueue(ArrayList<Car> queue) {
		this.queue = queue;
	}




}
