import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Strt {
	public static void main(String[] args) {
	    try {
	        File myObj = new File("C:\\data\\HiDrive\\Data\\uni\\algo\\Java\\ocr\\a.txt");
	        Scanner myReader = new Scanner(myObj);
	        String data
	        String firstLine = myReader.nextLine();
	        int intersections:
	        while (myReader.hasNextLine()) {
	           data += myReader.nextLine();
	          
	        }
	        myReader.close();
	        
	        System.out.println(data);
	      } catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	}
}
