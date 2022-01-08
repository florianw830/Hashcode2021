import java.util.ArrayList;
import java.util.Random;

public class GreenlightConfig extends Chromosom implements Mutateable<GreenlightConfig>{
	private int duration = 0;
	
	public GreenlightConfig(Chromosom c) {
		super(c);
		this.setConfig(c.getConfig());
	}
	
	public GreenlightConfig() {
		super();
	}
	
	
	public void mutate() {
		//GreenlightConfig cfg = new GreenlightConfig();
		for(ArrayList<Integer> tmp : this.getConfig()) {
			
			for(int i =0;i<tmp.size();i++) {
				Random rand = new Random();
				int n = rand.nextInt(99);
				if(n>0 & n <10) {
					int k = rand.nextInt(1)+1;
					int m = tmp.get(i)+k >= getDuration() ? getDuration()-k :tmp.get(i)+1;
					tmp.set(i, m);

				}else if(n>=10 & n <20) {
					int k = rand.nextInt(1)+1;
					int m = tmp.get(i)-k <=0 ?tmp.get(i)+k:tmp.get(i)-k;
					tmp.set(i, m);
				}
				
			}

		}

	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
}
