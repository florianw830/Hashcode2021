import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Chromosom{
	private ArrayList<ArrayList<Integer>> config = new ArrayList<ArrayList<Integer>>();
	
	
	private Mutation mutationType;
	private Crosover crosoverType;
	private boolean crosoverStable;
	private double mutationRate;
	public Chromosom() {
		
	}
	public Chromosom(Chromosom c) {
		this.mutationRate = c.mutationRate;
		this.crosoverType = c.crosoverType;
		this.crosoverStable = c.crosoverStable;
		this.mutationRate = c.mutationRate;
	}
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
	
	public  ArrayList<Chromosom> exchange( Chromosom b){
		Chromosom retValA = new Chromosom();
		Chromosom retValB = new Chromosom();
		retValA.crosoverStable = this.crosoverStable;
		retValA.crosoverType = this.crosoverType;
		retValA.mutationRate = this.mutationRate;
		retValA.mutationType = this.mutationType;
		retValB.crosoverStable = this.crosoverStable;
		retValB.crosoverType = this.crosoverType;
		retValB.mutationRate = this.mutationRate;
		retValB.mutationType = this.mutationType;
		if(this.crosoverType == Crosover.FullGene) {
			for(int i=0;i<this.getConfig().size();i++) {
				ArrayList<Integer> _a = i<this.getConfig().size()/2 ? this.getConfig().get(i) :b.getConfig().get(i);
				ArrayList<Integer> _b = i<this.getConfig().size()/2 ? b.getConfig().get(i) :this.getConfig().get(i) ;
				retValA.config.add(_a);
				retValB.config.add(_b);
			}
		}else {
			for(int i=0;i<this.getConfig().size();i++) {
				ArrayList<Integer> _a = this.getConfig().get(i);
				ArrayList<Integer> _b = b.getConfig().get(i);
				retValA.config.add(exchange(_a,_b));
				retValB.config.add(exchange(_b,_a));
			}
		}

		return new ArrayList<Chromosom>(Arrays.asList(retValA,retValB));
	}
	private  ArrayList<Integer> exchange(ArrayList<Integer> a, ArrayList<Integer> b){
		ArrayList<Integer> retVal = new ArrayList<Integer>();
		if(this.crosoverType == Crosover.Ordered) {
			for(int i =0;i<(int)a.size()/2;i++) {
				retVal.add(a.get(i));
			}
			int start = 0;
			for(int i =(int)a.size()/2;i<a.size();i++) {
				while(retVal.contains(b.get(start))) {
					start++;
				}
				retVal.add(b.get(start));
			}
		}else {
			for(int i =0;i<a.size();i++) {
				ArrayList<Integer> _a =null;
				ArrayList<Integer> _b =null;
				//System.out.println("CH" + this.crosoverType);
				if(this.crosoverType== Crosover.SinglePoint) {
					_a = i<a.size()/2 ? a :b;
					_b = i<a.size()/2 ? b :a;
				}
				if (this.crosoverType== Crosover.Uniform) {
					_a = i%2==0 ? a :b;
					_b = i%2==0 ? b :a;
				}
				if (this.crosoverStable & retVal.contains(_a.get(i)) ) {
					retVal.add(_b.get(i));
				}else {
					retVal.add(_a.get(i));
				}
			}


		}
			return retVal;
	}

	public void setConfig(ArrayList<ArrayList<Integer>> config) {
		this.config = config;
	}
	
	//public abstract Chromosom mutate();
	public Mutation getMutationType() {
		return mutationType;
	}
	public void setMutationType(Mutation mutationType) {
		this.mutationType = mutationType;
	}
	public Crosover getCrosoverType() {
		return crosoverType;
	}
	public void setCrosoverType(Crosover crosoverType) {
		this.crosoverType = crosoverType;
	}
	public boolean isCrosoverStable() {
		return crosoverStable;
	}
	public void setCrosoverStable(boolean crosoverStable) {
		this.crosoverStable = crosoverStable;
	}
	public double getMutationRate() {
		return mutationRate;
	}
	public void setMutationRate(double mutationRate) {
		this.mutationRate = mutationRate;
	}

}
