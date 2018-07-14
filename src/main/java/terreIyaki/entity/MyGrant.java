package terreIyaki.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

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

	public MyGrant(String name, Set<MyUser> myUsers) {
		super();
		this.name = name;
		this.myUsers = myUsers;
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

	@ManyToMany
	@JoinColumn(name = "my_user_login")
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
