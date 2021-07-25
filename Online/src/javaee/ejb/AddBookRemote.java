package javaee.ejb;

import javax.ejb.Remote;

import model.Book;

import java.util.ArrayList;

@Remote
public interface AddBookRemote {
	public ArrayList<Book> getList() ;
	public void setList(ArrayList<Book> list) ;
	public Boolean addToList(Book book);
	public Boolean deleteBookFromList(int i);
	public Boolean insertBook();
}
