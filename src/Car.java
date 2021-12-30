import java.util.LinkedList;

public class Car {
	private Street currentStreet;
	private LinkedList<String> route;
	private int counter = 0;
	private int currentTime = 0;
	private boolean parking = false;
	
	public static Car fromString(String str) {
		Car retVal = new Car();
		String[] tmp = str.split(" ");
		retVal.route = new  LinkedList<String>();
		for(int i= 1; i<tmp.length;i++) {
			retVal.route.add(tmp[i]);
		}
		//so landen die Autos beim ersten Tick in den richtigen Queues
		String firstStreet = retVal.route.poll();
		retVal.counter = 1;
		retVal.currentStreet = Street.streets.get(firstStreet);
		
		return retVal;
	}
	
	public void tick() {
		if(parking) {
			return;
		}
		
		currentTime++;
		counter--;
		if(getCounter() ==0) {
			if(route.size() == 0) {
				//Ziel erreicht
				this.parking = true;
				return;
			}
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
