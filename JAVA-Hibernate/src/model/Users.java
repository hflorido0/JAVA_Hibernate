package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name= "users")
@Table(name= "Users")
public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = true)
	private int id;
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "CREATE_DATE")
	private Date createDate;
	@Column(name = "CREATE_USER")
	private String createUser;
	@Column(name = "MODIFY_DATE")
	private Date modifyDate;
	@Column(name = "MODIFY_USER")
	private String modifyUser;
 
	public Users(){
 
	}
 
	public int getId() {
		return id;
	}
 
	public void setId(int id) {
		this.id = id;
	}
 
	public String getUserName() {
		return userName;
	}
 
	public void setUserName(String userName) {
		this.userName = userName;
	}
 
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
 
	public Date getCreateDate() {
		return createDate;
	}
 
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
 
	public String getCreateUser() {
		return createUser;
	}
 
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
 
	public Date getModifyDate() {
		return modifyDate;
	}
 
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
 
	public String getModifyUser() {
		return modifyUser;
	}
 
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", userName=" + userName + ", password="
				+ password + ", createDate=" + createDate + ", createUser="
				+ createUser + ", modifyDate=" + modifyDate + ", modifyUser="
				+ modifyUser + "]";
	}
	
}
