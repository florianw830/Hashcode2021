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
	
	
	/*
	 * Wird pro Zyklus aufgerufen und sorgt dafür das sich das Auto entlang
	 * einer Straße bewegt, an der Ampel wartet oder eine Kreuzung überquert.
	 * Wenn das Auto parkt, dann passiert nichts.
	 */
	public void tick() {
		if(parking) {
			return;
		}
		
		currentTime++;
		counter--;
		//Ist der Counter = 0, so sind wir am ende der  Straße angekommen
		//und reihen uns in die Warteschlange an der Kreuzung ein.
		if(getCounter() ==0) {
			if(route.size() == 0) {
				//Ziel erreicht
				this.parking = true;
				return;
			}
			currentStreet.getQueue().add(this);


		}
		
		//Es wird an einer Roten Ampel gewartet.
		if(getCounter() <0) {
			//Die Ampel ist grün und das aktuelle Auto ist das erste in der Warteschlange.
			if(canDrive()) {

				String nextHop = route.poll();
				Street newStreet = this.currentStreet.getInIntersection().getOutStreetByName(nextHop);
				currentStreet.getQueue().remove(0);
				currentStreet = newStreet;
				counter = currentStreet.getRuntime();
				
			}
		}
	}
	
	
	/*
	 * Lierft true, wenn das Auto das erste in seiner Warteschlange ist und die ensprechende 
	 * Ampel grün ist.
	 */
	private boolean canDrive() {
		if(currentStreet.getQueue().get(0) == this) {
			if(currentStreet.getInIntersection().isGreen(currentStreet,currentTime) == true) {
				return true;
			}
		}
		return false;
	}
	
	//getter und setter

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
