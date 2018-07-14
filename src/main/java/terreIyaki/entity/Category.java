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

    public class Category {

	private Long id;
	private @NonNull String name;
	private Set<Product> products;

	private Menu menu;

	private Set<ComboCategory> comboCategories;

	public Category() {
		super();
	}

	public Category(String name) {
		super();
		this.name = name;
	}

	public Category(String name, Menu menu) {
		super();
		this.name = name;
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

	@JsonIgnore
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
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
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	public Set<ComboCategory> getComboCategories() {
		return comboCategories;
	}

	public void setComboCategories(Set<ComboCategory> comboCategories) {
		this.comboCategories = comboCategories;
	}

	@Override
	public String toString() {
		return "\nCategory [id=" + id + ", name=" + name + "]";
	}



}
