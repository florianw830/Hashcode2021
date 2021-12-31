import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Strt {
	public static void main(String[] args) {
	    try {
	        File myObj = new File("C:\\data\\HiDrive\\Data\\uni\\algo\\Java\\ocr\\a.txt");
	        Scanner myReader = new Scanner(myObj);
	        String data="";
	        while (myReader.hasNextLine()) {
	           data += myReader.nextLine() + "\n";
	          
	        }
	        myReader.close();
	        
	        Simulation s = Simulation.fromString(data);
	      } catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	}
}
