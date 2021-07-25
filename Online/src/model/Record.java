package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


/**
 * The persistent class for the record database table.
 * 
 */
@Entity
@NamedQuery(name="Record.findAll", query="SELECT r FROM Record r")
public class Record implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Date time;
	
	private String revName;
	
	private String Address;
	
	private String phone;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
	   name = "ref_book_record",
	   joinColumns = 
	          {@JoinColumn(name="bookid",referencedColumnName="id")},
	   inverseJoinColumns = 
	          {@JoinColumn(name="recordid",referencedColumnName="id")} 
	   )
	private Set<Book> bookList = new  HashSet<Book>();

	public Record() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRevName() {
		return revName;
	}

	public void setRevName(String revName) {
		this.revName = revName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setBookList(Set<Book> books){
		bookList = books;
	}
	
	public Set<Book> getBookList(){
		return bookList;
	}
}