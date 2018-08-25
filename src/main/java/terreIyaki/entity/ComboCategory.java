package terreIyaki.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.NonNull;

@Entity
public class ComboCategory {

	private Long id;
	private @NonNull int number;
	private Set<Product> products;

	private Combo combo;

	private Category category;

	public ComboCategory() {
		super();
	}

	public ComboCategory(int number) {
		super();
		this.number = number;
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	//	@JsonIgnore
	//	@OneToMany(mappedBy="comboCategory", cascade = CascadeType.ALL)
	@ManyToMany
	@JoinColumn(name = "product_id")
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@ManyToOne
	@JoinColumn(name = "combo_id")
	public Combo getCombo() {
		return combo;
	}

	public void setCombo(Combo combo) {
		this.combo = combo;
	}

	@ManyToOne
	@JoinColumn(name = "category_id")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Transient
	public String getCategoryName() {
		try {
			return this.getCategory().getName();
		}catch(NullPointerException ex) {
			System.out.println(ex);
			return "void";
		}
	}

	@Override
	public String toString() {
		return "\nComboCategory [id=" + id + ", number=" + number + "]";
	}

}
