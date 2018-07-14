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
@UniqueConstraint(columnNames={"name"}))
public class OrderType {
	
private Long id;

private @NonNull String name;

private Set<MyOrder> myOrders;

private Historisation historisation;

public OrderType() {
	super();
}

public OrderType(String name) {
	super();
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

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

@JsonIgnore
@OneToMany(mappedBy="orderType", cascade = CascadeType.ALL)
public Set<MyOrder> getMyOrders() {
	return myOrders;
}

public void setMyOrders(Set<MyOrder> myOrders) {
	this.myOrders = myOrders;
}

@JsonIgnore
@OneToOne(mappedBy ="orderType", cascade = CascadeType.ALL)
public Historisation getHistorisation() {
	return historisation;
}

public void setHistorisation(Historisation historisation) {
	this.historisation = historisation;
}

@Override
public String toString() {
	return "\nOrderType [id=" + id + ", name=" + name + "]";
}

}
