package com.stacksimplifly.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.hateoas.ResourceSupport;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//Entity

@ApiModel(description = "This model is to create a user")
@Entity
@Table(name = "USER")
//@JsonIgnoreProperties({"firstname","lastname"})
//@JsonFilter(value = "userFilter")
public class User  extends ResourceSupport{
	
	@ApiModelProperty(notes = "userid - Unique identifier of user", required = true, position = 1)
	@Id
	@GeneratedValue
	private Long userid;
	
	@ApiModelProperty(notes = "user name should be format in flnamer", example = "DPrasad", required = false, position = 1)
	@Size(min=2,max = 50)
	@NotEmpty(message = "User Name Mandtory field. Please provide user name")
	@Column(name = "user_name", length = 50, nullable = false, unique = true)
	private String username;

	
	@Size(min=2 ,max = 50, message ="First Name Should have at least 2 characters")
	@Column(name = "first_name", length = 50, nullable = false)
	private String firstname;
	
	@Column(name = "last_name", length = 50, nullable = false)
	private String lastname;
	
	@Column(name = "email", length = 50, nullable = false)
	private String email;
	
	@Column(name = "role", length = 50, nullable = false)
	private String role;
	
	@Column(name = "ssn", length = 50, nullable = true)
	//@JsonIgnore
	private String ssn;

	@OneToMany(mappedBy = "user")
	private List<Order> orderList;
	
	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	// No arg construtor
	public User() {
	}

	public User(Long userid, String username, String firstname, String lastname, String email, String role, String ssn) {
		this.userid = userid;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	}

	// Getter and Setter
	public Long getUserid() {
		return userid;
	}

	public void setId(Long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}
}
