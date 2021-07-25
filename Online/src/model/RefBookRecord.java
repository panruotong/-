package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ref_book_record database table.
 * 
 */
@Entity
@Table(name="ref_book_record")
@NamedQuery(name="RefBookRecord.findAll", query="SELECT r FROM RefBookRecord r")
public class RefBookRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int bookid;

	private int recordid;

	public RefBookRecord() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookid() {
		return this.bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

}