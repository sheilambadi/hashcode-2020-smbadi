import java.util.*;

/**
 * @author Sheila Mbadi
 * 
 * Hashcode 2020 Online Qualification Round
 *
 * Library class 
 * 
 */
public class Library
{
	private List<Book> books;
	private int signUpTime;
	private int maxBooksScannedDaily;
	
	public List<Book> getBooks()			{ return books; }
	public int getSignUpTime()				{ return signUpTime; }
	public int getMaxBooksScannedDaily()	{ return maxBooksScannedDaily; }
	
	public void setBooks(List<Book> books)							{ this.books = books; }
	public void setSignUpTime(int signUpTime)						{	this.signUpTime = signUpTime; }
	public void setMaxBooksScannedDaily(int maxBooksScannedDaily)	{ this.maxBooksScannedDaily = maxBooksScannedDaily; }
}
