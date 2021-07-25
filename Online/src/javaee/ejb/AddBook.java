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
    
	//添加书籍到确认列表
	public Boolean addToList(Book book){
		System.out.println("==============addToList===============");
		//复制书本对象添加到列表中
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
	//插入数据库
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
    	//调用这个方法销毁有状态会话bean
		System.out.println("==重置book");
    }
    @PreDestroy
    public void endEJB()
    {
    	//执行cacel前的操作
    	System.out.println("==predestroy is called");
    	entityManager.close();
    }
	
}
