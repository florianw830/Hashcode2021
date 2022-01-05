import java.util.ArrayList;
import java.util.Random;

public class Order extends Chromosom{

	@Override
	public Order mutate() {
		Order retVal =  new Order();
		for(ArrayList<Integer> tmp : this.getConfig()) {
			for(int x=0;x<tmp.size();x++) {
				Random rand = new Random();
				int n = rand.nextInt(99);
				if(n>0 & n <2) {
					int a = rand.nextInt(tmp.size());
					int b = rand.nextInt(tmp.size());
					int t = tmp.get(a);
					tmp.set(a, tmp.get(b));
					tmp.set(b, t);
				}	
			}
			ArrayList<Integer> ne = new ArrayList<Integer>(tmp);
			retVal.add(ne);
		}
		return retVal;
	}

}
