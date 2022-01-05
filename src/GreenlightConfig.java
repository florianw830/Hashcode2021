import java.util.ArrayList;
import java.util.Random;

public class GreenlightConfig extends Chromosom{
	private int duration = 0;
	public GreenlightConfig mutate() {
		GreenlightConfig cfg = new GreenlightConfig();
		for(ArrayList<Integer> tmp : this.getConfig()) {
			ArrayList<Integer> t =  new ArrayList<Integer>();
			for(int i =0;i<tmp.size();i++) {
				Random rand = new Random();
				int n = rand.nextInt(99);
				if(n>0 & n <10) {
					int k = rand.nextInt(10)+1;
					int m = tmp.get(i)+k >= getDuration() ? getDuration() :tmp.get(i)+1;
					t.add(m);
				}else if(n>=10 & n <20) {
					int k = rand.nextInt(10)+1;
					int m = tmp.get(i)-k <=0 ?tmp.get(i):tmp.get(i)-k;
					t.add(m);
				}else {
					t.add(tmp.get(i));
				}
				
			}
			cfg.add(t);
		}
		return cfg;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
}
