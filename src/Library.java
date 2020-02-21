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
	private int maxBooksToShip;
	private int noOfBooks;
	private int libNo;
	
	public List<Book> getBooks()			{ return books; }
	public int getSignUpTime()				{ return signUpTime; }
	public int getMaxBooksToShip()			{ return maxBooksToShip; }
	public int getNoOfBooks()				{ return noOfBooks; }
	public int getLibNo()					{ return libNo; }
	
	public void setBooks(List<Book> books)					{ this.books = books; }
	public void setSignUpTime(int signUpTime)				{	this.signUpTime = signUpTime; }
	public void setMaxBooksToShip(int maxBooksToShip)		{ this.maxBooksToShip = maxBooksToShip; }
	public void setNoOfBooks(int noOfBooks)					{ this.noOfBooks = noOfBooks; }
	public void setLibNo(int libNo)							{ this.libNo = libNo; }
}
