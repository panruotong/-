package javaee.ejb;

import java.util.ArrayList;

import javax.annotation.PreDestroy;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import model.Book;

/**
 * Session Bean implementation class AddBook
 */
@Stateful
public class AddBook implements AddBookRemote {

    public AddBook() {
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext(unitName = "Online",type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
    
    private ArrayList<Book> list = new ArrayList<Book>();
    
	public ArrayList<Book> getList() {
		return list;
	}
	
	public void setList(ArrayList<Book> list) {
		this.list = list;
	}
    
	//����鼮��ȷ���б�
	public Boolean addToList(Book book){
		System.out.println("==============addToList===============");
		//�����鱾������ӵ��б���
		this.list.add(book);

		System.out.println("==============addToList===============");
		return true;
	};
	
	public Boolean deleteBookFromList(int i){
		System.out.println("==============deleteBookFromList===============");
		if(i>=0&&i<this.list.size()){
			this.list.remove(i);
		}
		System.out.println("==============deleteBookFromList===============");
		return true;
	};
	//�������ݿ�
	public Boolean insertBook(){
		for(Book bi: list){
			System.out.println("aaa");
			entityManager.persist(bi);
		}
		entityManager.flush();
		entityManager.clear();
		return true;
		
	}
	
	@Remove
    public void cancel(){
    	this.insertBook();
    	//�����������������״̬�Ựbean
		System.out.println("==����book");
    }
    @PreDestroy
    public void endEJB()
    {
    	//ִ��cacelǰ�Ĳ���
    	System.out.println("==predestroy is called");
    	entityManager.close();
    }
	
}
