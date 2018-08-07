package terreIyaki.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.NonNull;

@Entity
public class OrderItem {

	private Long id;
	private @NonNull float price;
	private @NonNull float tax;
	private int quantite;
	private String comment;
	

	private Product product;

	private Set<Historisation> historisations;

	private Statut statut;
	
	private Combo combo;
	
	private MyOrder myOrder;

	public OrderItem() {
		super();
	}

	public OrderItem(float price, float tax, int quantite, String comment) {
		super();
		this.price = price;
		this.tax = tax;
		this.quantite=quantite;
		this.comment = comment;
	}

	public OrderItem(float price, float tax, String comment, Product product) {
		super();
		this.price = price;
		this.tax = tax;
		this.comment = comment;
		this.product = product;
	}

	public OrderItem(float price, float tax, String comment, Statut statut) {
		super();
		this.price = price;
		this.tax = tax;
		this.comment = comment;
		this.statut = statut;
	}

	public OrderItem(float price, float tax, String comment, Combo combo) {
		super();
		this.price = price;
		this.tax = tax;
		this.comment = comment;
		this.combo = combo;
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getTax() {
		return tax;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Transient
	public Float getVatPrice() {
		float vatPrice=0f;
		vatPrice = this.price + (this.price *this.tax);
		return vatPrice;
	}

	@ManyToOne
	@JoinColumn(name = "product_id")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Transient
	public String getProductName() {
		return this.getProduct().getName();
	}

	@Transient
	public Long getIdProduct() {
		return this.getProduct().getId();
		
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "orderItem")
	public Set<Historisation> getHistorisations() {
		return historisations;
	}

	public void setHistorisations(Set<Historisation> historisations) {
		this.historisations = historisations;
	}

	@ManyToOne
	@JoinColumn(name = "statut_id")
	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	
	@ManyToOne
	@JoinColumn(name = "myOrder_id")
	public MyOrder getMyOrder() {
		return myOrder;
	}

	public void setMyOrder(MyOrder myOrder) {
		this.myOrder = myOrder;
	}

	@ManyToOne
	@JoinColumn(name = "combo_id")
	public Combo getCombo() {
		return combo;
	}

	public void setCombo(Combo combo) {
		this.combo = combo;
	}

	@Override
	public String toString() {
		return "\nOrderItem [id=" + id + ", price=" + price + ", tax=" + tax + "]";
	}

}
