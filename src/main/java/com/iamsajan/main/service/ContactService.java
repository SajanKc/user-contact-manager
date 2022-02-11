package com.iamsajan.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iamsajan.main.controller.ContactListResponseDto;
import com.iamsajan.main.dto.ContactCreateDto;
import com.iamsajan.main.dto.ContactResponseDto;
import com.iamsajan.main.model.Contact;
import com.iamsajan.main.model.User;
import com.iamsajan.main.repository.ContactRepository;
import com.iamsajan.main.repository.UserRepository;

@Service
public class ContactService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ContactRepository contactRepository;

	private ContactResponseDto getContactResponseDto(Contact savedContact) {
		ContactResponseDto response = new ContactResponseDto();
		response.setId(savedContact.getId());
		response.setEmail(savedContact.getEmail());
		response.setMobileNumber(savedContact.getMobileNumber());

		return response;
	}

	public ContactResponseDto addContacts(Long id, ContactCreateDto request) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			Contact contact = new Contact();
			contact.setUser(optionalUser.get());
			contact.setEmail(request.getEmail());
			contact.setMobileNumber(request.getMobileNumber());
			Contact savedContact = contactRepository.save(contact);

			return getContactResponseDto(savedContact);
		}
		return null;
	}

	public ContactResponseDto updateContacts(Long uId, Long cId, ContactCreateDto requestContact) {
		Optional<User> optionalUser = userRepository.findById(uId);
		if (optionalUser.isPresent()) {
			Optional<Contact> optionalContact = contactRepository.findById(cId);
			if (optionalContact.isPresent()) {
				Contact contact = optionalContact.get();
				contact.setMobileNumber(requestContact.getMobileNumber());
				contact.setEmail(requestContact.getEmail());
				Contact savedContact = contactRepository.save(contact);
				return getContactResponseDto(savedContact);
			}
			return null;
		}
		return null;
	}

	public void deleteByUserIdAndContactId(Long uId, Long cId) {
		contactRepository.deleteByUserIdContactId(uId, cId);
	}

	public void deleteAllContact(Long id) {
		contactRepository.deleteByUserId(id);
	}

	public ContactListResponseDto getAllContactByUsersId(Long id) {
		List<ContactResponseDto> contactResponseList = new ArrayList<>();

		List<Contact> contacts = (List<Contact>) contactRepository.findByUserId(id);

		for (Contact contact : contacts) {
			contactResponseList.add(getContactResponseDto(contact));
		}
		ContactListResponseDto response = new ContactListResponseDto();
		response.setContacts(contactResponseList);

		return response;
	}

}
