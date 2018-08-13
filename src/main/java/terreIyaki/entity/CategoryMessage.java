package terreIyaki.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.NonNull;

@Entity
@Table(
		uniqueConstraints=
		@UniqueConstraint(columnNames={"number"}))
public class CategoryMessage {

	private Long id;
	private @NonNull int number;
	private @NonNull String name;

	private Set<TheMessage> theMessages;

	public CategoryMessage() {
		super();
	}

	public CategoryMessage(int number, String name) {
		this();
		this.number = number;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "categoryMessage", cascade = CascadeType.ALL)
	public Set<TheMessage> getTheMessages() {
		return theMessages;
	}

	public void setTheMessages(Set<TheMessage> theMessages) {
		this.theMessages = theMessages;
	}

	@Override
	public String toString() {
		return "\nCategoryMessage [id=" + id + ", number=" + number + "]";
	}





}
