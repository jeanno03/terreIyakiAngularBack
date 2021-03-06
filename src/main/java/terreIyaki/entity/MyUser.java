package terreIyaki.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.NonNull;

@Entity
@Table(
		uniqueConstraints=
		@UniqueConstraint(columnNames={"email","login"}))
public class MyUser {

	private Long id;
	private @NonNull String email;
	private @NonNull String login;
	private String firstName;
	private String lastName;

	private Set<MyGrant> myGrants;

	private Set<MyOrder> myOrders;

	private Set<Historisation> historisations;
	
	private Set<Comment> comments;

	public MyUser() {
		super();
	}

	public MyUser(String email, String login, String firstName, String lastName) {
		this();
		this.email = email;
		this.login = login;
		this.firstName = firstName;
		this.lastName = lastName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@ManyToMany
	@JoinColumn(name = "my_grant_login")
	public Set<MyGrant> getMyGrants() {
		return myGrants;
	}

	public void setMyGrants(Set<MyGrant> myGrants) {
		this.myGrants = myGrants;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "myUser", cascade = CascadeType.ALL)
	public Set<MyOrder> getMyOrders() {
		return myOrders;
	}

	public void setMyOrders(Set<MyOrder> myOrders) {
		this.myOrders = myOrders;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "myUser", cascade = CascadeType.ALL)
	public Set<Historisation> getHistorisations() {
		return historisations;
	}

	public void setHistorisations(Set<Historisation> historisations) {
		this.historisations = historisations;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "myUser", cascade = CascadeType.ALL)
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "MyUser [id=" + id + ", email=" + email + ", login=" + login + ", firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}




}
