/**
 * 
 */
package com.stacksimplifly.restservices.hello;

/**
 * @author PavaniPrasad
 *
 */
public class UserDetails {

	private String firstName;
	private String lastName;
	private String city;
	
	//Perameterized constructor  
	public UserDetails(String firstName, String lastName, String city) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}