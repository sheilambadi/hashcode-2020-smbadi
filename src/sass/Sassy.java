import java.awt.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * ToDo: Class descriptor
 * 
 * @author Sheila Mbadi
 * 
 * Andrew ID: smbadi
 *
 * On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work.
 *
 */

/**
 * @author Sheila Mbadi
 *
 */
public class Sassy
{

	public static void main(String[] args)
	{
		 try{
		      // FileReader fr = new FileReader("a_example.txt");
		      // FileReader fr = new FileReader("b_read_on.txt");
		      //FileReader fr = new FileReader("c_incunabula.txt");
		      //FileReader fr = new FileReader("d_tough_choices.txt");
		      //FileReader fr = new FileReader("e_so_many_books.txt");
		      FileReader fr = new FileReader("f_libraries_of_the_world.txt");
		      BufferedReader br = new BufferedReader(fr);

		      String line = br.readLine();
		      String[] line1 = line.split(" ");

		      if(line1.length == 3){
		        int totalBooks = Integer.parseInt(line1[0]);
		        int noOfLibs = Integer.parseInt(line1[1]);
		        int maxDays = Integer.parseInt(line1[2]);
		        
		        line = br.readLine();
		        String[] scoresStrings = line.split(" ");
		        int bookScores[] = new int[totalBooks];
		        
		        for(int i = 0; i < totalBooks; i++) {
		        	bookScores[i] = Integer.parseInt(scoresStrings[i]);
		        }
		        
		        //HashMap hm_libs = new HashMap();
		        ArrayList hm_libs = new ArrayList();
		        
		        int noOfBooks = 0;
		        for(int i=0; i < noOfLibs; i++) {
			        	line = br.readLine();
			        	HashMap hm = new HashMap();
			        	
			        
				        String[] libraryDetails = line.split(" ");
				        
				        noOfBooks = Integer.parseInt(libraryDetails[0]);
				        int signUpDuration = Integer.parseInt(libraryDetails[1]);
				        int maxBooksToShip = Integer.parseInt(libraryDetails[2]);
				        
				        hm.put("totalBooks", noOfBooks);
				        hm.put("signUpDuration", signUpDuration);
				        hm.put("maxBooksToShip", maxBooksToShip);
				        
				        line = br.readLine();
			        	String[] booksInLibString = line.split(" ");
			        	
			        	
			        	//int booksInLib[] = new int[noOfBooks];
			        	
			        	ArrayList booksInLib = new ArrayList();
			        	
			        	HashMap hm_book_score = new HashMap();
				        
				        for(int j = 0; j < noOfBooks; j++) {
				        	// hm_book_score.put(Integer.parseInt(booksInLibString[j]), bookScores[j]);
				        	// booksInLib.add(hm_book_score);
				        	booksInLib.add(Integer.parseInt(booksInLibString[j]));
				        }
				        
				        hm.put("booksInLib", booksInLib);
				        hm.put("library", i);
				        
				        //hm_libs.put(i, hm);
				        hm_libs.add(hm);
			        
		        }
		        
		        
		        Comparator<HashMap<String, Integer>> maxBooksComparator = new Comparator<HashMap<String,Integer>>() {

		            @Override
		            public int compare(HashMap<String, Integer> o1, HashMap<String, Integer> o2) {
		                // Get the distance and compare the distance.
		                Integer val1 = o1.get("maxBooksToShip");
		                Integer val2 = o2.get("maxBooksToShip");

		                return val2.compareTo(val1);
		            }
		        };
		        
		        Comparator<HashMap<String, Integer>> signUpDurationComparator = new Comparator<HashMap<String,Integer>>() {

		            @Override
		            public int compare(HashMap<String, Integer> o1, HashMap<String, Integer> o2) {
		                // Get the distance and compare the distance.
		                Integer val1 = o1.get("signUpDuration");
		                Integer val2 = o2.get("signUpDuration");

		                return val1.compareTo(val2);
		            }
		        };

		        // And then sort it using collections.sort().
		        Collections.sort(hm_libs, maxBooksComparator);
		        Collections.sort(hm_libs, signUpDurationComparator);
		        
	        	//FileWriter fw = new FileWriter("a_out.txt");
	        	// FileWriter fw = new FileWriter("b_out.txt");
		        //FileWriter fw = new FileWriter("c_out.txt");
		        //FileWriter fw = new FileWriter("d_out.txt");
		        //FileWriter fw = new FileWriter("e_out.txt");
		        FileWriter fw = new FileWriter("f_out.txt");
	            BufferedWriter bw = new BufferedWriter(fw);
	            
	            bw.write("" + hm_libs.size());
	            bw.newLine();
		        
		        for(int j = 0; j < hm_libs.size(); j++) {
		            
		            HashMap hashMap = (HashMap)hm_libs.get(j);
		            
		            bw.write("" + hashMap.get("library"));
		            bw.write("" + hashMap.get("totalBooks"));
		            bw.newLine();
		            
		            ArrayList a = (ArrayList)hashMap.get("booksInLib");
		            
		            for(int k=0; k<a.size(); k++) {
		            	bw.write("" + a.get(k));
		            }
		            
		            //bw.write("" + hashMap.get("booksInLib"));
		            bw.newLine();
		            
		            
		        }
		        
	            bw.close();
	            fw.close();
		        
			    System.out.println("Hm: " + hm_libs);
		      }

		    } catch (IOException e){
		      e.printStackTrace();
		    }

	}

}
