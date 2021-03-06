package terreIyaki.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.NonNull;

@Entity
@Table(
		uniqueConstraints=
		@UniqueConstraint(columnNames={"name"}))
public class MyGrant {

	private Long id;

	private @NonNull String name;

	private Set<MyUser> myUsers;

	public MyGrant() {
		super();
	}

	public MyGrant(String name) {
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
	@ManyToMany(mappedBy = "myGrants", cascade = CascadeType.ALL)
	public Set<MyUser> getMyUsers() {
		return myUsers;
	}

	public void setMyUsers(Set<MyUser> myUsers) {
		this.myUsers = myUsers;
	}

	@Override
	public String toString() {
		return "\nMyGrant [id=" + id + ", name=" + name + "]";
	}


}
