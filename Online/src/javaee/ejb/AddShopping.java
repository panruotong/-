package javaee.ejb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateful;

import model.Book;

/**
 * Session Bean implementation class AddShopping
 */
@Stateful
public class AddShopping implements AddShoppingRemote {

    private Set<Book> shopping = new HashSet<Book>();
    
    
    public AddShopping() {
        // TODO Auto-generated constructor stub
    }

	public void add(Book b) {
		// TODO Auto-generated method stub
		System.out.println("Add:"+b.getName());
		shopping.add(b);
	}

	public Set<Book> getShopping() {
		return shopping;
	}

}
