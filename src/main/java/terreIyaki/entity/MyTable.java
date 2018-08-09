package terreIyaki.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	private MyOrder myOrder;

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
	@OneToOne(mappedBy = "myTable", cascade = CascadeType.ALL)
	public MyOrder getMyOrder() {
		return myOrder;
	}

	public void setMyOrder(MyOrder myOrder) {
		this.myOrder = myOrder;
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
