import java.util.concurrent.LinkedBlockingQueue;

public class Street {
	private String name;
	private int runtime = 0;
	private LinkedBlockingQueue<Car> queue;
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

	public LinkedBlockingQueue<Car> getQueue() {
		return queue;
	}

	public void setQueue(LinkedBlockingQueue<Car> queue) {
		this.queue = queue;
	}




}
