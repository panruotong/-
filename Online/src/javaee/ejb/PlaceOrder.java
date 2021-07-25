package javaee.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import model.Book;
import model.Record;
import model.User;

/**
 * Session Bean implementation class PlaceOrder
 */
@Stateful
public class PlaceOrder implements PlaceOrderRemote {
	
	@PersistenceContext(unitName = "Online",type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	private List<Record> waitForSure = new ArrayList<Record>();
	
    /**
     * Default constructor. 
     */
    public PlaceOrder() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void add(Record r) {
		// TODO Auto-generated method stub
		waitForSure.add(r);
	}

	@Override
	public void submit(Record r) {
		// TODO Auto-generated method stub
		for (Book b:r.getBookList()){
			b.setNum(b.getNum()-1);
			em.merge(b);
		}
		em.persist(r);
	}

	@Override
	public User findUser(int id) {
		// TODO Auto-generated method stub
		return em.find(User.class, id);
	}

	@Override
	public Record getOrder(int orderid) {
		Record result = null;
		for(Record r:waitForSure){
			if(r.getId()==orderid){
				result = r;
				break;
			}
		}
		waitForSure.remove(result);
		return result;
	}

}
