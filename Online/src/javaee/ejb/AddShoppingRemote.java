package javaee.ejb;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import model.Book;

@Remote
public interface AddShoppingRemote {
	public void add(Book b);
	public Set<Book> getShopping();
}
