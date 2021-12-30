import java.util.ArrayList;
import java.util.HashMap;

import java.util.concurrent.LinkedBlockingQueue;

public class Intersection {
	private int isId;
	private ArrayList<Integer> greenTime = new ArrayList<Integer>();
	private ArrayList<ArrayList<Car>> waiting = new ArrayList<ArrayList<Car>>();
	private HashMap<String, Street> outStreets = new HashMap<String,Street>();
	private HashMap<Integer, Street> inStreets = new HashMap<Integer,Street>();
	private HashMap<String, Integer> streetToNumber = new HashMap<String,Integer>();
	
	public int getTotalTime() {
		int retVal = 0;
		for(Integer i : greenTime) {
			retVal+=i;
		}
		return retVal;
	}
	
	public Street getOutStreetByName(String name) {
		return outStreets.get(name);
	}
	
	
	/*Erhält die aktuelle relative Zeit 
	 * 	Beispiel für relative Zeit:
	 * 		Drei Grünphasen 3,4,7 Sekunden -> Gesamtzeit 14 Sekunden
	 * 		aktuelle Simulationszeit 15 Sekunden -> relative Zeit 1 Sekunde
	 * 		aktuelle Simulationszeit 3 Sekunden -> relative Zeit 3 Sekunden 
	 * 		aktuelle Simulationszeit 32 Sekunden  -> relative Zeit 4 Sekunden
	 * und eine Straße und gibt true zurück falls die Straße aktuell grün ist.
	 * fals wenn nicht
	 */
	private boolean isGreen(int relativTime, int street) {
		int last = 0;
		int tmp= 0;
		for(int i = 0; i<greenTime.size();i++) {
			tmp += greenTime.get(i);
			if(tmp >= relativTime) {
				break;
			}
			last = i;
		}
		if(last == street) {
			return true;
		}
		
		return false;
	}
	
	public boolean isGreen(Street street, int time) {
		int streetNo = streetToNumber.get(street.getName());
		int relativTime = time%getTotalTime();
		return isGreen(relativTime, streetNo);
	}

	public void addInStreet(Street street) {
		greenTime.add(1);
		
		int streetPosition = greenTime.size()-1;
		ArrayList<Car> tmpQueue = new  ArrayList<Car>();
		
		street.setInIntersection(this);
		street.setQueue(tmpQueue);
		getStreetToNumber().put(street.getName(), streetPosition);
		inStreets.put(streetPosition, street);
		waiting.add(tmpQueue);
	}
	
	//getter und setter
	
	public void addOutStreet(Street street) {
		outStreets.put(street.getName(), street);
	}
	
	public int getIsId() {
		return isId;
	}
	
	public void setIsId(int isId) {
		this.isId = isId;
	}

	public HashMap<String, Integer> getStreetToNumber() {
		return streetToNumber;
	}

	public void setStreetToNumber(HashMap<String, Integer> streetToNumber) {
		this.streetToNumber = streetToNumber;
	}




}
