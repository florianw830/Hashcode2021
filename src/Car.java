import java.util.LinkedList;

public class Car {
	private Street currentStreet;
	private LinkedList<String> route;
	private int counter = 0;
	private int currentTime = 0;
	
	
	public void Tick() {
		currentTime++;
		counter--;
		if(getCounter() ==0) {			
			currentStreet.getQueue().add(this);		

		}
		if(getCounter() <0) {
			if(canDrive()) {
				String nextHop = route.poll();

				Street newStreet = this.currentStreet.getInIntersection().getOutStreetByName(nextHop);
				currentStreet.getQueue().remove(0);
				currentStreet = newStreet;
				counter = currentStreet.getRuntime();
						
			}
		}
	}
	
	private boolean canDrive() {
		if(currentStreet.getQueue().get(0) == this) {
			if(currentStreet.getInIntersection().isGreen(currentStreet,currentTime) == true) {
				return true;
			}
		}
		return false;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public Street getCurrentStreet() {
		return currentStreet;
	}

	public void setCurrentStreet(Street currentStreet) {
		this.currentStreet = currentStreet;
	}

}
