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
	        String f = "e.txt";
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
	        for(int i = 0; i< 100;i++) {
	        	Simulation s = Simulation.fromString(data,true);
	        	if(s.getFinishScore()>best) {
	        		best=s.getFinishScore();
	        		bestSim =s;
	        	}
	        	System.out.println(i + " " + s.simulate());
	        	q.add(s);
	        }
	        
	        for(int ll =0; ll <1000;ll++) {
		        int tbest =0;

	        	System.out.println("Generation " + ll);
	        	PriorityQueue<Simulation> tq = new PriorityQueue<Simulation>();
	        	for(int i = 0; i< 5;i++) {
	        		Simulation parent = q.poll();
	        		q.add(parent);
	        		for(int n= 0; n<10;n++) {
	        			Simulation s = Simulation.fromString(data,false);
	        			s.setGreenLightConfig(parent.mutateG());
	        			s.setOrder(parent.mutateO());
	        			s.simulate();
	    	        	if(s.getFinishScore()>best) {
	    	        		best=s.getFinishScore();
	    	        		bestSim =s;
	    	        		tbest = best;
	    	        	}
	    	        	
	        			tq.add(s);
	        		}
	        	}
	        	//if(tbest==best) {
	        	q = tq;
	        	//}
	        	
	        	
	        	System.out.println(best + " " + tq.peek().getFinishScore());
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
