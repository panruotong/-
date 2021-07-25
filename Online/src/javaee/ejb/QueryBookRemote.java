package javaee.ejb;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Remote;

import model.Book;

@Remote
public interface QueryBookRemote {
	public Book QueryByName(String name);
	public Book QueryByISBN(String isbn);
	public List<Book> QueryByAuthor(String author);
	public List<Book> QueryByTime(Timestamp before, Timestamp after);
	public List<Book> QueryByPrice(float down, float up);
	public List<Book> QueryByCategory(int c);
	public Book QueryByID(int id);
}
