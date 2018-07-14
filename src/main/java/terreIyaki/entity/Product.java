package terreIyaki.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.NonNull;

import java.util.Set;

@Entity
//@Table(
//uniqueConstraints=
//@UniqueConstraint(columnNames={"name"}))
public class Product{

	private Long id;
	private @NonNull String name;
	private @NonNull float price;
	private String picture;
	private String description;

	private Set<ComboCategory> comboCategories;
	
//	private ComboCategory comboCategory;

	private Category category;

	private Vat vat;
	
	private Statut statut;
	
	private Set<OrderItem>orderItems;

	public Product() {
		super();
	}

	public Product(String name, float price, String picture, String description) {
		super();
		this.name = name;
		this.price = price;
		this.picture = picture;
		this.description = description;
	}	
	
	public Product(String name, float price, String picture, String description, Category category) {
		super();
		this.name = name;
		this.price = price;
		this.picture = picture;
		this.description = description;
		this.category = category;
	}

	public Product(String name, float price, String picture, String description, Vat vat) {
		super();
		this.name = name;
		this.price = price;
		this.picture = picture;
		this.description = description;
		this.vat = vat;
	}

	public Product(String name, float price, String picture, String description, Statut statut) {
		super();
		this.name = name;
		this.price = price;
		this.picture = picture;
		this.description = description;
		this.statut = statut;
	}

	public Product(String name, float price, String picture, String description, Set<OrderItem> orderItems) {
		super();
		this.name = name;
		this.price = price;
		this.picture = picture;
		this.description = description;
		this.orderItems = orderItems;
	}

	public Product(String name, float price, String picture, String description, Category category, Vat vat) {
		super();
		this.name = name;
		this.price = price;
		this.picture = picture;
		this.description = description;
		this.category = category;
		this.vat = vat;
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
	
//	@Transient
//	public Long getTheComboCategoryId() {
//		Long ComboCategoryId=null;
//		try {
//			ComboCategoryId = this.comboCategory.getId();
//		}catch (NullPointerException ex) {
//			//
//		}
//		return ComboCategoryId;
//	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonIgnore
	@ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
	public Set<ComboCategory> getComboCategories() {
		return comboCategories;
	}

	public void setComboCategories(Set<ComboCategory> comboCategories) {
		this.comboCategories = comboCategories;
	}
	
	

	@ManyToOne
	@JoinColumn(name = "category_id")
	public Category getCategory() {
		return category;
	}

//	@ManyToOne
//	@JoinColumn(name = "comboCategory_id")
//	public ComboCategory getComboCategory() {
//		return comboCategory;
//	}
//
//	public void setComboCategory(ComboCategory comboCategory) {
//		this.comboCategory = comboCategory;
//	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@ManyToOne
	@JoinColumn(name = "vat_id")
	public Vat getVat() {
		return vat;
	}

	public void setVat(Vat vat) {
		this.vat = vat;
	}

	@ManyToOne
	@JoinColumn(name = "statut_id")
	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	@Transient
	public Float getVatPrice() {
		Float vatPrice = this.price;
		try {
//		if(this.getVat().getId()!=null) {
			vatPrice += this.price * this.getVat().getRate();
//		}
		}catch(NullPointerException ex) {
			
		}
		return vatPrice;
	}

	@Override
	public String toString() {
		return "\nProduct [id=" + id + ", name=" + name + ", price=" + price + ", picture=" + picture + ", description="
				+ description + "]";
	}

}
