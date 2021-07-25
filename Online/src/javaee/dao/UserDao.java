package javaee.dao;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import model.User;

import com.mysql.jdbc.Connection;

public class UserDao {
	@PersistenceContext(unitName="Online")
	private EntityManager manager;
	
	@Resource
	private UserTransaction utx;
	public UserDao() {
	}
	public void addUser(String username, String password) throws SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException{
		//EntityManager manager = factory.createEntityManager();
		System.out.println(username+password);
		User oneUser = new User();
		oneUser.setUserName(username);
		oneUser.setPassword(password);
		try {
			utx.begin();
			manager.persist(oneUser);
			utx.commit();
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
