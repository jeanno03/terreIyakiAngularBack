package terreIyaki.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import lombok.NonNull;

@Entity
public class Historisation {

	private Long id;
	private @NonNull Date registeredDate;

	private MyUser myUser;

	private MyOrder myOrder;

	private Set<OrderItem> orderItems;

	private OrderType orderType;

	private Payment payment;

	private Statut statut;

	public Historisation() {
		super();
	}

	public Historisation(Date registeredDate) {
		super();
		this.registeredDate = registeredDate;
	}

	public Historisation(Date registeredDate, MyUser myUser) {
		super();
		this.registeredDate = registeredDate;
		this.myUser = myUser;
	}

	public Historisation(Date registeredDate, MyOrder myOrder) {
		super();
		this.registeredDate = registeredDate;
		this.myOrder = myOrder;
	}



	public Historisation(Long id, Date registeredDate, Set<OrderItem> orderItems) {
		super();
		this.id = id;
		this.registeredDate = registeredDate;
		this.orderItems = orderItems;
	}

	public Historisation(Date registeredDate, OrderType orderType) {
		super();
		this.registeredDate = registeredDate;
		this.orderType = orderType;
	}

	public Historisation(Date registeredDate, Payment payment) {
		super();
		this.registeredDate = registeredDate;
		this.payment = payment;
	}

	public Historisation(Date registeredDate, Statut statut) {
		super();
		this.registeredDate = registeredDate;
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

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	@ManyToOne
	@JoinColumn(name = "my_user_id")
	public MyUser getMyUser() {
		return myUser;
	}

	public void setMyUser(MyUser myUser) {
		this.myUser = myUser;
	}

	@ManyToOne
	@JoinColumn(name = "myorder_id")
	public MyOrder getMyOrder() {
		return myOrder;
	}

	public void setMyOrder(MyOrder myOrder) {
		this.myOrder = myOrder;
	}




	//	@ManyToOne
	//	@JoinColumn(name = "order_item_id")
	//	public OrderItem getOrderItem() {
	//		return orderItem;
	//	}
	//
	//	public void setOrderItem(OrderItem orderItem) {
	//		this.orderItem = orderItem;
	//	}

	@ManyToMany
	@JoinColumn
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@OneToOne
	@JoinColumn(name = "order_type_id")
	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	@OneToOne
	@JoinColumn(name = "payment_id")
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
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
		return "\nHistorisation [id=" + id + ", registeredDate=" + registeredDate + "]";
	}

}
