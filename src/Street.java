import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class Street {
	private String name;
	private int runtime = 0;
	private ArrayList<Car> queue;
	private Intersection inIntersection;
	
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
