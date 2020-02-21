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
				FileReader fr = new FileReader(inputs.get(f).toString());
				BufferedReader br = new BufferedReader(fr);

				String line = br.readLine();
				String[] line1 = line.split(" ");

				if (line1.length == 3)
				{
					int totalBooks = Integer.parseInt(line1[0]);
					int noOfLibs = Integer.parseInt(line1[1]);
					int maxDays = Integer.parseInt(line1[2]);

					line = br.readLine();
					String[] scoresStrings = line.split(" ");
					int bookScores[] = new int[totalBooks];

					for (int i = 0; i < totalBooks; i++)
					{
						bookScores[i] = Integer.parseInt(scoresStrings[i]);
					}

					// HashMap hm_libs = new HashMap();
					List hm_libs = new ArrayList();

					int noOfBooks = 0;
					for (int i = 0; i < noOfLibs; i++)
					{
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

						// int booksInLib[] = new int[noOfBooks];

						ArrayList booksInLib = new ArrayList();

						HashMap hm_book_score = new HashMap();

						for (int j = 0; j < noOfBooks; j++)
						{
							// hm_book_score.put(Integer.parseInt(booksInLibString[j]), bookScores[j]);
							// booksInLib.add(hm_book_score);
							booksInLib.add(Integer.parseInt(booksInLibString[j]));
						}

						hm.put("booksInLib", booksInLib);
						hm.put("library", i);

						// hm_libs.put(i, hm);
						hm_libs.add(hm);

					}

					/*
					Comparator<HashMap<String, Integer>> maxBooksComparator = new Comparator<HashMap<String, Integer>>()
					{

						@Override
						public int compare(HashMap<String, Integer> o1, HashMap<String, Integer> o2)
						{
							// Get the distance and compare the distance.
							Integer val1 = o1.get("maxBooksToShip");
							Integer val2 = o2.get("maxBooksToShip");

							return val2.compareTo(val1);
						}
					};

					Comparator<HashMap<String, Integer>> signUpDurationComparator = new Comparator<HashMap<String, Integer>>()
					{

						@Override
						public int compare(HashMap<String, Integer> o1, HashMap<String, Integer> o2)
						{
							// Get the distance and compare the distance.
							Integer val1 = o1.get("signUpDuration");
							Integer val2 = o2.get("signUpDuration");

							return val1.compareTo(val2);
						}
					};

					// And then sort it using collections.sort().
					Collections.sort(hm_libs, maxBooksComparator);
					Collections.sort(hm_libs, signUpDurationComparator);*/
					
					hm_libs = sortLibraries(hm_libs, "maxBooksToShip", true);
					hm_libs = sortLibraries(hm_libs, "signUpDuration", false);

					FileWriter fw = new FileWriter("./output/" + outputs.get(f).toString());
					BufferedWriter bw = new BufferedWriter(fw);

					bw.write("" + hm_libs.size());
					bw.newLine();

					for (int j = 0; j < hm_libs.size(); j++)
					{

						HashMap hashMap = (HashMap) hm_libs.get(j);

						bw.write("" + hashMap.get("library") + " ");
						bw.write("" + hashMap.get("totalBooks"));
						bw.newLine();

						ArrayList a = (ArrayList) hashMap.get("booksInLib");

						for (int k = 0; k < a.size(); k++)
						{
							bw.write("" + a.get(k) + " ");
						}

						// bw.write("" + hashMap.get("booksInLib"));
						bw.newLine();

					}

					bw.close();
					fw.close();

					// System.out.println("Hm: " + hm_libs);
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

	public static List sortLibraries(List toSort, String key, boolean inAscendingOrder)
	{
		Comparator<HashMap<String, Integer>> comparator = new Comparator<HashMap<String, Integer>>()
		{

			@Override
			public int compare(HashMap<String, Integer> o1, HashMap<String, Integer> o2)
			{
				Integer val1 = o1.get(key);
				Integer val2 = o2.get(key);

				if (inAscendingOrder)
					return val2.compareTo(val2);
				else
					return val1.compareTo(val2);
			}
		};

		Collections.sort(toSort, comparator);
		return toSort;
	}

}
