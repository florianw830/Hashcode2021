import java.util.LinkedList;

public class Car {
	private Street currentStreet;
	private LinkedList<String> route;
	private int carID = 0;
	private int counter = 0;
	private int currentTime = -1;
	private boolean parking = false;
	
	
	public static Car fromString(String str) {
		Car retVal = new Car();
		String[] tmp = str.split(" ");
		
		retVal.route = new  LinkedList<String>();
		for(int i= 1; i<tmp.length;i++) {
			//System.out.println("Add" + tmp[i]);
			retVal.route.add(tmp[i]);
		}
		
		//so landen die Autos beim ersten Tick in den richtigen Queues
		String firstStreet = retVal.route.poll();
		//System.out.println("First Street" + firstStreet);
		retVal.counter = 0;
		retVal.currentStreet = Street.streets.get(firstStreet);
		retVal.currentStreet.getQueue().add(retVal);
		return retVal;
	}
	
	
	/*
	 * Wird pro Zyklus aufgerufen und sorgt dafür das sich das Auto entlang
	 * einer Straße bewegt, an der Ampel wartet oder eine Kreuzung überquert.
	 * Wenn das Auto parkt, dann passiert nichts.
	 */
	public void tick(int time) {
		if(isParking()) {
			return;
		}
		
		currentTime= time;
		counter--;
		//System.out.println(currentTime + " " + counter + " " + route.size());
		//Ist der Counter = 0, so sind wir am ende der  Straße angekommen
		//und reihen uns in die Warteschlange an der Kreuzung ein.
		//System.out.println("CAR " + this.carID + " counter " + counter + " route " + route.size());
		
		if(getCounter() ==0) {
			if(route.size() == 0) {
				//Ziel erreicht
				this.setParking(true);
				//System.out.println("CAR " + this.carID + " " + currentTime + " scores " + this.getScore(6, 1000));
				
				return;
			}
			//System.out.println("CAR " + this.carID +"waits at " + currentStreet.getName());
			currentStreet.getQueue().add(this);
		}
		
		//Es wird an einer Roten Ampel gewartet.
		if(getCounter() <=0) {
			//Die Ampel ist grün und das aktuelle Auto ist das erste in der Warteschlange.
			if(canDrive()) {
				//System.out.print("CAR " + this.carID);
				String nextHop = route.poll();
				this.currentStreet.getInIntersection().setLastCarPassesd(time);
				Street newStreet = this.currentStreet.getInIntersection().getOutStreetByName(nextHop);
				//System.out.print(" leaves " + currentStreet.getName());
				currentStreet.getQueue().remove(0);
				//System.out.println(" and goes to " + newStreet.getName());
				currentStreet = newStreet;
				
				counter = currentStreet.getRuntime();
			}
		}
		if(counter >0 ) {
			//System.out.print(" is driving at " + this.currentStreet.getName());
		}
		//System.out.println("");
	}
	
	
	/*
	 * Lierft true, wenn das Auto das erste in seiner Warteschlange ist und die ensprechende 
	 * Ampel grün ist.
	 */
	private boolean canDrive() {
		//System.out.println("QQ Car " + this.carID + " queue " + currentStreet.getQueue().get(0).carID);
		if(currentStreet.getQueue().get(0).carID == this.carID) {
			//System.out.println(currentStreet.getName());
			//System.out.println(currentStreet.getInIntersection().isGreen(currentStreet, currentTime));
			if(currentStreet.getInIntersection().isGreen(currentStreet,currentTime) == true) {
				return true;
			}
		}
		return false;
	}
	
	public int getScore(int finishScore, int simulationTime) {
		if(! isParking()) {
			return 0;
		}
		//System.out.println("C" + currentTime);
		//System.out.println("S" + simulationTime);
		return (simulationTime-currentTime) + finishScore; 
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


	public boolean isParking() {
		return parking;
	}


	public void setParking(boolean parking) {
		this.parking = parking;
	}


	public int getCarID() {
		return carID;
	}


	public void setCarID(int carID) {
		this.carID = carID;
	}

}
