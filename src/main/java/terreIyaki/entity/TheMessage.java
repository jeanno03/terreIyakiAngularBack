package terreIyaki.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;


import lombok.NonNull;

@Entity
@Table(
uniqueConstraints=
@UniqueConstraint(columnNames={"number"}))
public class TheMessage {
	
	private Long id;
	private @NonNull int number;
	private @NonNull String theMessage;
	
	private CategoryMessage categoryMessage;
	
	public TheMessage() {
		super();
	}

	public TheMessage(int number, String theMessage) {
		this();
		this.number = number;
		this.theMessage = theMessage;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	@Transient
	public Long getTheId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getTheMessage() {
		return theMessage;
	}

	public void setTheMessage(String theMessage) {
		this.theMessage = theMessage;
	}
	
	@ManyToOne
	@JoinColumn(name = "category_message_id")
	public CategoryMessage getCategoryMessage() {
		return categoryMessage;
	}

	public void setCategoryMessage(CategoryMessage categoryMessage) {
		this.categoryMessage = categoryMessage;
	}
	
//	@Transient
//	public String getCategoryMessageName() {
//		return this.getCategoryMessage().getName();
//	}

	@Override
	public String toString() {
		return "\nTheMessage [id=" + id + ", number=" + number + ", theMessage=" + theMessage + "]";
	}
	
	

	
}
