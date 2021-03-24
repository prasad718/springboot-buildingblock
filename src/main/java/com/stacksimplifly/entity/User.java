package com.stacksimplifly.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//Entity

@Entity
@Table(name = "USER")
public class User {
	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty(message = "User Name Mandtory field. Please provide user name")
	@Column(name = "user_name", length = 50, nullable = false, unique = true)
	private String username;

	@Size(min=2 , message ="First Name Should have at least 2 characters")
	@Column(name = "first_name", length = 50, nullable = false)
	private String firstname;
	
	@Column(name = "last_name", length = 50, nullable = false)
	private String lastname;
	
	@Column(name = "email", length = 50, nullable = false)
	private String email;
	
	@Column(name = "role", length = 50, nullable = false)
	private String role;
	
	@Column(name = "ssn", length = 50, nullable = true)
	private String ssn;

	// No arg construtor
	public User() {
	}

	public User(Long id, String username, String firstname, String lastname, String email, String role, String ssn) {
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	}

	// Getter and Setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return "User [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}
}
