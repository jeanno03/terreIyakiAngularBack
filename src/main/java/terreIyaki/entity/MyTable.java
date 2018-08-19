package terreIyaki.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.NonNull;

@Entity
@Table(
		uniqueConstraints=
		@UniqueConstraint(columnNames={"tableNumber"}))
public class MyTable {

	private Long id;

	private @NonNull int tableNumber;

	private Set<MyOrder> myOrders;

	private Statut statut;

	public MyTable() {
		super();
	}

	public MyTable(int tableNumber) {
		super();
		this.tableNumber = tableNumber;
	}

	public MyTable(int tableNumber, Statut statut) {
		super();
		this.tableNumber = tableNumber;
		this.statut = statut;
	}
	

	public MyTable(int tableNumber, Set<MyOrder> myOrders) {
		super();
		this.tableNumber = tableNumber;
		this.myOrders = myOrders;
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

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}


	@JsonIgnore
	@OneToMany(mappedBy = "myTable", cascade = CascadeType.ALL)
	public Set<MyOrder> getMyOrders() {
		return myOrders;
	}

	public void setMyOrders(Set<MyOrder> myOrders) {
		this.myOrders = myOrders;
	}

	@ManyToOne
	@JoinColumn(name = "statut_id")
	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	@Transient
	public String getStatutName() {
		try {
			return this.getStatut().getName();
		}catch(NullPointerException ex) {
			System.out.println(ex);
			return "void";
		}
	}

	@Override
	public String toString() {
		return "MyTable [id=" + id + ", tableNumber=" + tableNumber + "]";
	}



}
