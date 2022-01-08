import java.util.ArrayList;
import java.util.Random;

public class Order extends Chromosom implements Mutateable<Order>{
	public Order(Chromosom c) {
		super(c);
		this.setConfig(c.getConfig());
	}
	
	public Order() {
		super();
	}
	public void mutate() {
		
		for(ArrayList<Integer> tmp : this.getConfig()) {
			for(int x=0;x<tmp.size();x++) {
				Random rand = new Random();
				int n = rand.nextInt(99);
				if(n>0 & n <5) {
					int a = rand.nextInt(tmp.size());
					int b = rand.nextInt(tmp.size());
					int t = tmp.get(a);
					tmp.set(a, tmp.get(b));
					tmp.set(b, t);
				}	
			}

		}
	}
	
}
