import java.util.ArrayList;
import java.util.Random;

public abstract class Chromosom {
	private ArrayList<ArrayList<Integer>> config = new ArrayList<ArrayList<Integer>>();
	
	public void add(int... cfg) {
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		for(int i : cfg) {
			tmp.add(i);
		}
		getConfig().add(tmp);
	}
	public void add(ArrayList<Integer> cfg) {
		ArrayList<Integer> tmp = new ArrayList(cfg);
		config.add(tmp);
	}
	public ArrayList<ArrayList<Integer>> getConfig() {
		return config;
	}

	public void setConfig(ArrayList<ArrayList<Integer>> config) {
		this.config = config;
	}
	
	public abstract Chromosom mutate();

}
