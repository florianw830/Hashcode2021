
public class Car {
	private Street currentStreet;
	private int counter = 0;
	private int queueWaitTime = 0;
	public void Tick(int time) {
		counter--;
		if(getCounter() == 0) {
			
			currentStreet.getQueue().add(this);
			int position = currentStreet.getInIntersection().getStreetToNumber().get(currentStreet.getName());
			queueWaitTime = currentStreet.getInIntersection().timeToGreen(time, position);
			
		}
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
