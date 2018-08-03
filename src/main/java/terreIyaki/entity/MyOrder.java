package terreIyaki.entity;

import java.util.Date;
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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.NonNull;

@Entity
public class MyOrder {

	private Long id;
	private @NonNull Date orderDate;

	private MyUser myUser;

	private MyTable myTable;

	private Set<Historisation> historisations;

	private OrderType orderType;

	private Set<Payment> payment;

	private Statut statut;
	
	private Set<OrderItem> orderItems;

	public MyOrder() {
		super();
	}

	public MyOrder(Date orderDate) {
		super();
		this.orderDate = orderDate;
	}

	public MyOrder(Date orderDate, MyUser myUser) {
		super();
		this.orderDate = orderDate;
		this.myUser = myUser;
	}

	public MyOrder(Date orderDate, MyTable myTable) {
		super();
		this.orderDate = orderDate;
		this.myTable = myTable;
	}

	public MyOrder(Date orderDate, OrderType orderType) {
		super();
		this.orderDate = orderDate;
		this.orderType = orderType;
	}

	public MyOrder(Date orderDate, Statut statut) {
		super();
		this.orderDate = orderDate;
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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@ManyToOne
	@JoinColumn(name = "my_user_id")
	public MyUser getMyUser() {
		return myUser;
	}

	public void setMyUser(MyUser myUser) {
		this.myUser = myUser;
	}

	@OneToOne
	@JoinColumn(name = "mytable_id")
	public MyTable getMyTable() {
		return myTable;
	}

	public void setMyTable(MyTable myTable) {
		this.myTable = myTable;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "myOrder", cascade = CascadeType.ALL)
	public Set<Historisation> getHistorisations() {
		return historisations;
	}

	public void setHistorisations(Set<Historisation> historisations) {
		this.historisations = historisations;
	}

	@ManyToOne
	@JoinColumn(name = "myorder_type_id")
	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "myOrder", cascade = CascadeType.ALL)
	public Set<Payment> getPayment() {
		return payment;
	}

	public void setPayment(Set<Payment> payment) {
		this.payment = payment;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "myOrder", cascade = CascadeType.ALL)
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@ManyToOne
	@JoinColumn(name = "statut_id")
	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	@Override
	public String toString() {
		return "\nMyOrder [id=" + id + ", orderDate=" + orderDate + "]";
	}

}
