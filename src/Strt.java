import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Strt {
	public static void main(String[] args) {
	    try {
	        String f = "f.txt";
	        String p = "C:\\data\\HiDrive\\Data\\uni\\algo\\Java\\ocr\\";
	    	File myObj = new File(p+f);
	        
	        Scanner myReader = new Scanner(myObj);
	        String data="";
	        while (myReader.hasNextLine()) {
	           data += myReader.nextLine() + "\n";
	          
	        }
	        myReader.close();
	        ArrayList<Integer> od = new ArrayList<Integer>();
	        od.add(1);
	        od.add(0);
	        
	        GreenlightConfig gc = new GreenlightConfig();
	        gc.add(2);
	        gc.add(1,2);
	        gc.add(2);
	        ArrayList<Simulation> sims = new ArrayList<Simulation>();
	        PriorityQueue<Simulation> q = new PriorityQueue<Simulation>();
	        int best =0;
	        Simulation bestSim = null;
	        Simulation s1 = Simulation.fromString(data,true,true);
	        s1.simulate();
        	if(s1.getFinishScore()>best) {
        		best=s1.getFinishScore();
        		bestSim =s1;
        	}
        	q.add(s1);
        	s1 = Simulation.fromString(data,true,true);
	        s1.simulate();
        	if(s1.getFinishScore()>best) {
        		best=s1.getFinishScore();
        		bestSim =s1;
        	}
        	q.add(s1);
	        for(int i = 0; i< 100;i++) {
	        	Simulation s = Simulation.fromString(data,true,false);
	        	System.out.println(i + " " + s.simulate());
	        	//System.out.println("ääää" + s.getGreenLightConfig().getCrosoverType());
	        	if(s.getFinishScore()>best) {
	        		best=s.getFinishScore();
	        		bestSim =s;
	        	}
	        	//System.out.println("uuuu" + s.getGreenLightConfig().getCrosoverType());
	        	q.add(s);
	        	//System.out.println("pppp" + s.getGreenLightConfig().getCrosoverType());
	        	//System.out.println("gggg" + q.poll().getGreenLightConfig().getCrosoverType());
	        }
	        System.out.println("Best " + best);
	        for(int ll =0; ll <1000;ll++) {
		        int tbest =0;
		        int oldBest = best;
	        	System.out.println("Generation " + ll);
	        	PriorityQueue<Simulation> tq = new PriorityQueue<Simulation>();
	        	for(int i = 0; i< 20 ;i++) {
	        		System.out.println(i +" " +q.size());
	        		int zz= i<3?5:1;
	        		for(int ii =0;ii<zz;ii++) {
	        			//System.out.println(ii +" " +q.size());
		        		Simulation parent1 = (Simulation) q.toArray()[i];
		        		Simulation parent2 = (Simulation) q.toArray()[ii];
		        		ArrayList<Chromosom> gCfgs = parent1.getGreenLightConfig().exchange(
		        				parent2.getGreenLightConfig());
		        		ArrayList<Chromosom> oCfgs = parent1.getOrder().exchange(
		        				parent2.getOrder());
		        		for(int k=0;k<2;k++) {
		        			Simulation c = Simulation.fromString(data,false,false);
		        			c.setGreenlightConfig(gCfgs.get(k),true);
		        			
		        			c.setOrder(oCfgs.get(k),true);
		        			c.simulate();
		        			//System.out.println(c.getFinishScore());
		        			if(c.getFinishScore()>best) {
		    	        		best=c.getFinishScore();
		    	        		bestSim =c;
		    	        		tbest = best;
		    	        		q.add(c);
		        			}
		        			//tq.add(c);
		        		}
		        		//System.out.println(tq.size());
	        		}

	        		//System.out.println("öööö" + parent1.getGreenLightConfig().getCrosoverType());
	        		
	        		//q=tq;

        		


	        	}
	       
        		//q =tq;

	        		
	        	
	        	
	        	
	        	System.out.println(best + " " + oldBest);
	        	//for(int k=0;k<10;k++) {
	        	//	q.add(tq.poll());
	        	//}
	        	
	        }
	        


	        FileWriter myWriter;
			try {
				myWriter = new FileWriter(p+f + ".out.txt");
				Simulation out = bestSim;
				System.out.println("====" + out.getFinishScore());
		        myWriter.write(out.createFileContent());
		        myWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	      } catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	}
}
