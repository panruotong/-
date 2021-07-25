package javaee.ejb;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import model.Book;

/**
 * Session Bean implementation class QueryBook
 */
@Stateless
public class QueryBook implements QueryBookRemote {

    /**
     * Default constructor. 
     */
    public QueryBook() {
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext(unitName = "Online",type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@Override
	public Book QueryByName(String name) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("from Book c where c.name = '"+name+"'");
		List results = query.getResultList();
		Book b = null;
		b=(Book)results.get(0);
		return b;
	}
	
	@Override
	public Book QueryByISBN(String isbn) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("from Book c where isbn = '"+isbn+"'");
		List results = query.getResultList();
		Book b = null;
		b=(Book)results.get(0);
		return b;
	}

	@Override
	public List<Book> QueryByAuthor(String author) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public List<Book> QueryByTime(Timestamp before, Timestamp after) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("from Book c where c.pubDate between '"+before+"' and '"+after+"'");
		List results = query.getResultList();
		List<Book> res = results;
		return res;
	}

	@Override
	public List<Book> QueryByPrice(float down, float up) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("from Book c where c.price between "+down+" and "+up);
		List results = query.getResultList();
		List<Book> res = results;
		return res;
	}

	@Override
	public List<Book> QueryByCategory(int c) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("from Book c where c.category = "+c);
		List results = query.getResultList();
		List<Book> res = results;
		return null;
	}

	@Override
	public Book QueryByID(int id) {
		Book res = entityManager.find(Book.class, id);
		return res;
	}
    

}
