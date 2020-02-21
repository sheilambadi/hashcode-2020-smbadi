import java.io.*;
import java.util.*;

/**
 * @author Sheila Mbadi
 * 
 *         Hashcode 2020 Online Qualification Round
 *
 *         Book scanning driver class
 * 
 */

public class Sassy
{

	public static void main(String[] args)
	{
		try
		{
			List<String> inputs = inputFiles();
			List<String> outputs = outputFiles();

			for (int f = 0; f < inputs.size(); f++)
			{
				// read file
				FileReader fr = new FileReader(inputs.get(f).toString());
				BufferedReader br = new BufferedReader(fr);

				String line = br.readLine();
				
				// first line - no of diff books, no of libs, no of scanning days
				String[] line1 = line.split(" ");

				if (line1.length == 3)
				{
					int totalBooks = Integer.parseInt(line1[0]);
					int noOfLibs = Integer.parseInt(line1[1]);
					int maxScanningDays = Integer.parseInt(line1[2]);

					
					line = br.readLine();
					String[] scoresStrings = line.split(" ");
					//int bookScores[] = new int[totalBooks];
					List<Book> bookScores = new ArrayList<Book>();

					for (int i = 0; i < totalBooks; i++)
					{
						// read scores for the different books and save them in the book object
						Book book = new Book();
						
						int score = Integer.parseInt(scoresStrings[i]);
						book.setScore(score);
						
						bookScores.add(book);
						
						//bookScores[i] = Integer.parseInt(scoresStrings[i]);
					}

					// HashMap hm_libs = new HashMap();
					List hm_libs = new ArrayList();
					List libList = new ArrayList();

					int noOfBooks = 0;
					for (int i = 0; i < noOfLibs; i++)
					{
						// library to store books
						Library library = new Library();
						
						line = br.readLine();
						HashMap hm = new HashMap();

						String[] libraryDetails = line.split(" ");

						noOfBooks = Integer.parseInt(libraryDetails[0]);
						int signUpDuration = Integer.parseInt(libraryDetails[1]);
						int maxBooksToShip = Integer.parseInt(libraryDetails[2]);

						hm.put("totalBooks", noOfBooks);
						hm.put("signUpDuration", signUpDuration);
						hm.put("maxBooksToShip", maxBooksToShip);
						
						library.setNoOfBooks(noOfBooks);
						library.setSignUpTime(signUpDuration);
						library.setMaxBooksToShip(maxBooksToShip);

						line = br.readLine();
						String[] booksInLibString = line.split(" ");

						// int booksInLib[] = new int[noOfBooks];

						ArrayList booksInLib = new ArrayList();
						List books1 = new ArrayList();

						HashMap hm_book_score = new HashMap();

						for (int j = 0; j < noOfBooks; j++)
						{
							// hm_book_score.put(Integer.parseInt(booksInLibString[j]), bookScores[j]);
							// booksInLib.add(hm_book_score);
							int bookNo = Integer.parseInt(booksInLibString[j]);
							Book b = (Book) bookScores.get(j);
							b.setBookNo(bookNo);
							
							books1.add(b);
							
							//booksInLib.add(Integer.parseInt(booksInLibString[j]));
						}
						
						library.setBooks(books1);
						library.setLibNo(i);

						hm.put("booksInLib", booksInLib);
						hm.put("library", i);
						
						libList.add(library);

						// hm_libs.put(i, hm);
						hm_libs.add(hm);

					}
					
					System.out.println("Books: " + ((Library)libList.get(0)).getMaxBooksToShip());
					
					libList = sortLibraries(libList, 1);
					libList = sortLibraries(libList, 2);
					//hm_libs = sortLibraries(hm_libs, "maxBooksToShip", true);
					//hm_libs = sortLibraries(hm_libs, "signUpDuration", false);

					FileWriter fw = new FileWriter("./output/" + outputs.get(f).toString());
					BufferedWriter bw = new BufferedWriter(fw);

					// bw.write("" + hm_libs.size());
					bw.write("" + libList.size());
					bw.newLine();

					//for (int j = 0; j < hm_libs.size(); j++)
					for (int j = 0; j < libList.size(); j++)
					{

						HashMap hashMap = (HashMap) hm_libs.get(j);
						Library lib = (Library) (libList.get(j));

						//bw.write("" + hashMap.get("library") + " ");
						//bw.write("" + hashMap.get("totalBooks"));
						bw.write("" + lib.getLibNo() + " ");
						bw.write("" + lib.getNoOfBooks());
						bw.newLine();

						//ArrayList a = (ArrayList) hashMap.get("booksInLib");
						
						List<Book> libBooks = (List<Book>)lib.getBooks();

						//for (int k = 0; k < a.size(); k++)
						for (int k = 0; k < libBooks.size(); k++)
						{
							Book b = (Book) libBooks.get(k);
							//bw.write("" + a.get(k) + " ");
							bw.write("" + b.getBookNo() + " ");
						}

						// bw.write("" + hashMap.get("booksInLib"));
						bw.newLine();

					}

					bw.close();
					fw.close();

					// System.out.println("Hm: " + hm_libs);
				} else {
					System.out.println("Some parameters are missing in first line.");
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static List<String> inputFiles()
	{
		List<String> inputFiles = new ArrayList<String>();
		inputFiles.add("a_example.txt");
		inputFiles.add("b_read_on.txt");
		inputFiles.add("c_incunabula.txt");
		inputFiles.add("d_tough_choices.txt");
		inputFiles.add("e_so_many_books.txt");
		inputFiles.add("f_libraries_of_the_world.txt");

		return inputFiles;
	}

	public static List<String> outputFiles()
	{
		List<String> outputFiles = new ArrayList<String>();
		outputFiles.add("a_out.txt");
		outputFiles.add("b_out.txt");
		outputFiles.add("c_out.txt");
		outputFiles.add("d_out.txt");
		outputFiles.add("e_out.txt");
		outputFiles.add("f_out.txt");

		return outputFiles;
	}

	//public static List sortLibraries(List toSort, String key, boolean inAscendingOrder)
	public static List sortLibraries(List toSort, int key)
	{
		// Keys: 1-> maxBooksToShip, 2 -> signUpDuration
		Comparator<Library> comparator = new Comparator<Library>()
		{

//			@Override
//			public int compare(HashMap<String, Integer> o1, HashMap<String, Integer> o2)
//			{
//				Integer val1 = o1.get(key);
//				Integer val2 = o2.get(key);
//
//				if (inAscendingOrder)
//					return val2.compareTo(val2);
//				else
//					return val1.compareTo(val2);
//			}
			
			@Override
			public int compare(Library lib1, Library lib2)
			{
				if (key == 1) {
					Integer val1 = lib1.getMaxBooksToShip();
					Integer val2 = lib2.getMaxBooksToShip();
					return val2.compareTo(val2);
				} else {
					Integer val1 = lib1.getSignUpTime();
					Integer val2 = lib2.getSignUpTime();
					return val1.compareTo(val2);
				}
			}
		};

		Collections.sort(toSort, comparator);
		return toSort;
	}

}
