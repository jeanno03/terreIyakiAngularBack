package terreIyaki.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
		@UniqueConstraint(columnNames={"name","numero"}))
public class Statut {

	private Long id;

	private @NonNull int numero;

	private @NonNull String name;

	private Set<Product> products;

	private Set<MyOrder> myOrders;

	private Set<Historisation> historisations;

	private Set<OrderItem> orderItems;

	private Set<Combo> combos;

	private Set<MyTable> myTables;

	public Statut() {
		super();
	}

	public Statut(int numero, String name) {
		super();
		this.numero = numero;
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

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "statut", cascade = CascadeType.ALL)
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "statut", cascade = CascadeType.ALL)
	public Set<MyOrder> getMyOrders() {
		return myOrders;
	}

	public void setMyOrders(Set<MyOrder> myOrders) {
		this.myOrders = myOrders;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "statut", cascade = CascadeType.ALL)
	public Set<Historisation> getHistorisations() {
		return historisations;
	}

	public void setHistorisations(Set<Historisation> historisations) {
		this.historisations = historisations;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "statut", cascade = CascadeType.ALL)
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "statut", cascade = CascadeType.ALL)
	public Set<Combo> getCombos() {
		return combos;
	}

	public void setCombos(Set<Combo> combos) {
		this.combos = combos;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "statut", cascade = CascadeType.ALL)
	public Set<MyTable> getMyTables() {
		return myTables;
	}

	public void setMyTables(Set<MyTable> myTables) {
		this.myTables = myTables;
	}

	@Override
	public String toString() {
		return "\nStatut [id=" + id + ", name=" + name + "]";
	}

}
