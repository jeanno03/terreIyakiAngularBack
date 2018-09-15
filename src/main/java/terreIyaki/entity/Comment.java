package terreIyaki.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Comment {
	
private Long id;
private Date dateComment;
private String comment;

private MyUser myUser;

public Comment() {
	super();
}

public Comment(Date dateComment, String comment) {
	super();
	this.dateComment = dateComment;
	this.comment = comment;
}

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
public Long getId() {
	return id;
}

@Transient
public Long getTheId() {
	return this.id;
}

public void setId(Long id) {
	this.id = id;
}

public Date getDateComment() {
	return dateComment;
}

public void setDateComment(Date dateComment) {
	this.dateComment = dateComment;
}

public String getComment() {
	return comment;
}

public void setComment(String comment) {
	this.comment = comment;
}

@ManyToOne
@JoinColumn(name="my_user_id")
public MyUser getMyUser() {
	return myUser;
}

public void setMyUser(MyUser myUser) {
	this.myUser = myUser;
}

@Transient
public String getMyUserLogin() {
	return this.myUser.getLogin();
}

@Override
public String toString() {
	return "Comment [id=" + id + ", dateComment=" + dateComment + ", comment=" + comment + "]";
}




}
