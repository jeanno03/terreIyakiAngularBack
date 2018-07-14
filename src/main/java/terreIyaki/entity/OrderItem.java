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
	private String comment;

	private Product product;

	private Set<Historisation> historisations;

	private Statut statut;
	
	private Combo combo;

	public OrderItem() {
		super();
	}

	public OrderItem(float price, float tax, String comment) {
		super();
		this.price = price;
		this.tax = tax;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@ManyToOne
	@JoinColumn(name = "product_id")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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