package javaee.ejb;

import javax.ejb.Remote;

import model.Record;
import model.User;

@Remote
public interface PlaceOrderRemote {
	public void add(Record r);
	public void submit(Record r);
	public User findUser(int id);
	public Record getOrder(int orderid);
}
