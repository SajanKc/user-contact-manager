package com.iamsajan.main.controller;

import java.util.List;

import com.iamsajan.main.dto.ContactResponseDto;

public class ContactListResponseDto {

	private List<ContactResponseDto> contacts;

	public List<ContactResponseDto> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactResponseDto> contacts) {
		this.contacts = contacts;
	}

}
