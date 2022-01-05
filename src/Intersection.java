import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class Intersection {
	private int isId;
	private int lastCarPassesd =-1;
	private ArrayList<Integer> greenTime = new ArrayList<Integer>();
	private ArrayList<ArrayList<Car>> waiting = new ArrayList<ArrayList<Car>>();
	private HashMap<String, Street> outStreets = new HashMap<String,Street>();
	private HashMap<Integer, Street> inStreets = new HashMap<Integer,Street>();
	private HashMap<String, Integer> streetToNumber = new HashMap<String,Integer>();
	private ArrayList<Integer> order = new ArrayList<Integer>();
	

	
	public int getTotalTime() {
		int retVal = 0;
		for(Integer i : getGreenTime()) {
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
		int last = getOrder().get(0);
		int tmp= 0;
		for(int i = 0;i<getOrder().size();i++) {
			tmp += getGreenTime().get(getOrder().get(i));
			last = getOrder().get(i);
			if(tmp >= relativTime+1) {
				break;
			}
			
		}
		
		if(last == street) {
			return true;
		}
		
		return false;
		/*
		for(int i = 0; i<getGreenTime().size();i++) {
			tmp += getGreenTime().get(i);
			if(tmp >= relativTime) {
				break;
			}
			last = i;
		}
		if(last == street) {
			return true;
		}
		
		return false;
		*/
	}
	
	public boolean isGreen(Street street, int time) {
		if(this.lastCarPassesd == time) {
			return false;
		}
		int streetNo = streetToNumber.get(street.getName());
		int relativTime = time%getTotalTime();
		//System.out.println("street " + street.getName() + " "+ relativTime);
		return isGreen(relativTime, streetNo);
	}
	

	
	public void printStreetsByTime(int time) {
		for(int n: order) {
			Street tmp = this.inStreets.get(order.get(n));
			//System.out.println(time + " " + tmp.getName() + " " + this.isGreen(tmp, time));
		}
	}

	public void addInStreet(Street street) {
		getGreenTime().add(1);
		
		
		int streetPosition = getGreenTime().size()-1;
		getOrder().add(streetPosition);
		ArrayList<Car> tmpQueue = new  ArrayList<Car>();
		
		street.setInIntersection(this);
		street.setQueue(tmpQueue);
		getStreetToNumber().put(street.getName(), streetPosition);
		getInStreets().put(streetPosition, street);
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
	
	public ArrayList<Integer> setGreenTimeToOne(){
		this.greenTime = new ArrayList<Integer>();
		for(int i =0;i<inStreets.size();i++) {
			getGreenTime().add(1);
		}
		return new ArrayList<Integer>(this.getGreenTime());
	}
	
	public ArrayList<Integer> numOrder(){
		this.order = new ArrayList<Integer>();
		for(int i =0;i<inStreets.size();i++) {
			this.getOrder().add(i);
		}
		return new ArrayList<Integer>(this.getOrder());
	}
	
	public ArrayList<Integer> shuffleGreenTime(int max){
		this.greenTime = new ArrayList<Integer>();
		for(int i =0;i<inStreets.size();i++) {
			Random rand = new Random();
			int randomNum = rand.nextInt((max - 2) + 1)+1 ;
			getGreenTime().add(randomNum);
		}
		return new ArrayList<Integer>(this.getGreenTime());
	}

	public ArrayList<Integer> getGreenTime() {
		return greenTime;
	}

	public void setGreenTime(ArrayList<Integer> greenTime) {
		this.greenTime = greenTime;
	}

	public HashMap<Integer, Street> getInStreets() {
		return inStreets;
	}

	public void setInStreets(HashMap<Integer, Street> inStreets) {
		this.inStreets = inStreets;
	}

	public ArrayList<Integer> getOrder() {
		return order;
	}

	public void setOrder(ArrayList<Integer> order) {
		this.order = order;
	}

	public int getLastCarPassesd() {
		return lastCarPassesd;
	}

	public void setLastCarPassesd(int lastCarPassesd) {
		this.lastCarPassesd = lastCarPassesd;
	}
	




}
