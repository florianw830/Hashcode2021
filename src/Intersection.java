import java.util.ArrayList;
import java.util.HashMap;

import java.util.concurrent.LinkedBlockingQueue;

public class Intersection {
	private int isId;
	private ArrayList<Integer> greenTime = new ArrayList<Integer>();
	private ArrayList<LinkedBlockingQueue<Car>> waiting = new ArrayList<LinkedBlockingQueue<Car>>();
	private HashMap<String, Street> outStreets = new HashMap<String,Street>();
	private HashMap<Integer, Street> inStreets = new HashMap<Integer,Street>();
	private HashMap<String, Integer> streetToNumber = new HashMap<String,Integer>();
	
	public int gesamtzeit() {
		int retVal = 0;
		for(Integer i : greenTime) {
			retVal+=i;
		}
		return retVal;
	}

	public void addInStreet(Street street) {
		greenTime.add(1);
		
		int streetPosition = greenTime.size()-1;
		LinkedBlockingQueue<Car> tmpQueue = new  LinkedBlockingQueue<Car>();
		
		street.setInIntersection(this);
		street.setQueue(tmpQueue);
		getStreetToNumber().put(street.getName(), streetPosition);
		inStreets.put(streetPosition, street);
		waiting.add(tmpQueue);
	}
	
	public void addOutStreet(Street street) {
		outStreets.put(street.getName(), street);
	}
	
	public int getID() {
		return isId;
	}
	
	public void setID(int iD) {
		isId = iD;
	}

	public HashMap<String, Integer> getStreetToNumber() {
		return streetToNumber;
	}

	public void setStreetToNumber(HashMap<String, Integer> streetToNumber) {
		this.streetToNumber = streetToNumber;
	}
	
	
	/*
	public int timeToGreen(int zeit, int position) {
		int retVal = 0;
		int gesamtzeit = gesamtzeit();
		
		
		//Alle Grünzeiten die kleiner als die aktuelle 
		//Position sind addieren 
		int tmp = 0;
		for(int i = 0; i<position;i++) {
			tmp+=greenTime.get(i);
		}
		retVal = (gesamtzeit - (zeit%gesamtzeit) ) + tmp;
		
		
		return retVal; 
		
	}
	*/

}
