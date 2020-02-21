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
					List<Book> bookScores = new ArrayList<Book>();

					for (int i = 0; i < totalBooks; i++)
					{
						// read scores for the different books and save them in the book object
						int score = Integer.parseInt(scoresStrings[i]);

						Book book = new Book();
						book.setScore(score);

						bookScores.add(book);
					}

					List libList = new ArrayList();

					for (int i = 0; i < noOfLibs; i++)
					{
						// library to store books
						Library library = new Library();

						library.setLibNo(i);

						line = br.readLine();
						String[] libraryDetails = line.split(" ");

						// line -> no of books in lib, sign up duration, max books to ship daily
						int noOfBooks = Integer.parseInt(libraryDetails[0]);
						int signUpDuration = Integer.parseInt(libraryDetails[1]);
						int maxBooksToShip = Integer.parseInt(libraryDetails[2]);

						library.setNoOfBooks(noOfBooks);
						library.setSignUpTime(signUpDuration);
						library.setMaxBooksToShip(maxBooksToShip);

						line = br.readLine();
						String[] booksInLibString = line.split(" ");

						List booksInLib = new ArrayList();

						for (int j = 0; j < noOfBooks; j++)
						{
							int bookNo = Integer.parseInt(booksInLibString[j]);
							Book b = (Book) bookScores.get(j);

							int scoreValue = b.getScore();

							Book newBook = new Book();
							newBook.setBookNo(bookNo);
							newBook.setScore(scoreValue);

							booksInLib.add(newBook);
						}

						// sort books then add
						booksInLib = sortBooks(booksInLib);

						library.setBooks(booksInLib);

						libList.add(library);
					}

					System.out.println("Books: " + ((Library) libList.get(0)).getMaxBooksToShip());

					libList = sortLibraries(libList, 1);
					libList = sortLibraries(libList, 2);

					FileWriter fw = new FileWriter("./output/" + outputs.get(f).toString());
					BufferedWriter bw = new BufferedWriter(fw);

					bw.write("" + libList.size());
					bw.newLine();

					for (int j = 0; j < libList.size(); j++)
					{
						Library lib = (Library) (libList.get(j));

						bw.write("" + lib.getLibNo() + " ");
						bw.write("" + lib.getNoOfBooks());
						bw.newLine();

						List<Book> libBooks = (List<Book>) lib.getBooks();

						for (int k = 0; k < libBooks.size(); k++)
						{
							Book b = (Book) libBooks.get(k);
							bw.write("" + b.getBookNo() + " ");
						}

						bw.newLine();
					}

					bw.close();
					fw.close();
				} else
				{
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

	// public static List sortLibraries(List toSort, String key, boolean
	// inAscendingOrder)
	public static List sortLibraries(List toSort, int key)
	{
		// Keys: 1-> maxBooksToShip, 2 -> signUpDuration, 3 -> Book score
		Comparator<Library> comparator = new Comparator<Library>()
		{
			@Override
			public int compare(Library lib1, Library lib2)
			{
				if (key == 1)
				{
					// max book to ship in ascending order
					Integer val1 = lib1.getMaxBooksToShip();
					Integer val2 = lib2.getMaxBooksToShip();
					return val2.compareTo(val1);
				} else if (key == 2)
				{
					// sign up time in descending order
					Integer val1 = lib1.getSignUpTime();
					Integer val2 = lib2.getSignUpTime();
					return val1.compareTo(val2);
				} else
				{
					return 0;
				}
			}
		};

		Collections.sort(toSort, comparator);
		return toSort;
	}

	public static List sortBooks(List toSort)
	{
		// set in ascending order of score
		Comparator<Book> comparator = new Comparator<Book>()
		{
			@Override
			public int compare(Book book1, Book book2)
			{
				Integer val1 = book1.getScore();
				Integer val2 = book2.getScore();
				return val2.compareTo(val1);
			}
		};

		Collections.sort(toSort, comparator);
		return toSort;
	}

}
