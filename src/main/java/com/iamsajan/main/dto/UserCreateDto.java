package com.iamsajan.main.dto;

import java.util.List;

public class UserCreateDto {
	protected String name;
	protected String email;
	protected String password;
	protected String mobileNumber;
	protected List<ContactCreateDto> contacts;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public List<ContactCreateDto> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactCreateDto> contacts) {
		this.contacts = contacts;
	}

}
