class BookNotAvailable extends Exception
{
	public BookNotAvailable()
	{
	System.out.println("Book Not Available");
	}
}
class Library
{
	private int validid[]= {101, 102, 103};
	void  borrowBook(int bookId) throws BookNotAvailable
	{
		for(int id:validid)
		{
			if(id==bookId)
			{
				System.out.println("Book Available");
				return;
			}
		}
		
	throw new BookNotAvailable();
	}
}
public class BookNotAvailableException
{

	public static void main(String[] args)
	{
		Library l=new Library();
		try
		{
		l.borrowBook(101);
		}
		catch(BookNotAvailable e)
		{
			
		}
	}

}