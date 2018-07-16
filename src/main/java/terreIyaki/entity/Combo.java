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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.NonNull;

@Entity
@Table(
uniqueConstraints=
@UniqueConstraint(columnNames={"name"}))
public class Combo {

	private Long id;
	private @NonNull String name;
	private @NonNull float vatPrice;
	private String picture;
	private String description;

	private Statut statut;

	private Set<ComboCategory> comboCategories;

	private Menu menu;

	private Set<OrderItem> orderItems;

	public Combo() {
		super();
	}


	public Combo(String name, float vatPrice, String picture, String description) {
		super();
		this.name = name;
		this.vatPrice = vatPrice;
		this.picture = picture;
		this.description = description;
	}

	public Combo(String name, float vatPrice, String picture, String description, Statut statut) {
		super();
		this.name = name;
		this.vatPrice = vatPrice;
		this.picture = picture;
		this.description = description;
		this.statut = statut;
	}

	public Combo(String name, float vatPrice, String picture, String description, Menu menu) {
		super();
		this.name = name;
		this.vatPrice = vatPrice;
		this.picture = picture;
		this.description = description;
		this.menu = menu;
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getVatPrice() {
		return vatPrice;
	}

	public void setVatPrice(float vatPrice) {
		this.vatPrice = vatPrice;
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

	@ManyToOne
	@JoinColumn(name = "statut_id")
	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "combo", cascade = CascadeType.ALL)
	public Set<ComboCategory> getComboCategories() {
		return comboCategories;
	}

	public void setComboCategories(Set<ComboCategory> comboCategories) {
		this.comboCategories = comboCategories;
	}

	@ManyToOne
	@JoinColumn(name = "menu_id")
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "combo", cascade = CascadeType.ALL)
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "\nCombo [id=" + id + ", name=" + name + ", vatPrice=" + vatPrice + ", picture=" + picture + ", description="
				+ description + "]";
	}



}
