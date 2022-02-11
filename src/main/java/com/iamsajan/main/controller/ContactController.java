package com.iamsajan.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.iamsajan.main.dto.ContactCreateDto;
import com.iamsajan.main.dto.ContactResponseDto;
import com.iamsajan.main.service.ContactService;

@RestController
@RequestMapping("/api/users")
public class ContactController {

	@Autowired
	private ContactService contactService;

	@PostMapping("/{id}/contacts")
	@ResponseStatus(code = HttpStatus.OK)
	public ContactResponseDto addUserContact(@PathVariable Long id, @RequestBody ContactCreateDto contact) {
		return contactService.addContacts(id, contact);
	}

	@PutMapping("/{uId}/contacts/{cId}")
	@ResponseStatus(code = HttpStatus.OK)
	public ContactResponseDto updateUserContact(@PathVariable Long uId, @PathVariable Long cId,
			@RequestBody ContactCreateDto contact) {
		return contactService.updateContacts(uId, cId, contact);
	}

	@DeleteMapping("/{uId}/contacts/{cId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteContactById(@PathVariable Long uId, @PathVariable Long cId) {
		contactService.deleteByUserIdAndContactId(uId, cId);
	}

	@DeleteMapping("{id}/contacts")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Transactional
	public void delete(@PathVariable Long id) {
		contactService.deleteAllContact(id);
	}

	@GetMapping("/{id}/contacts")
	@ResponseStatus(code = HttpStatus.OK)
	public ContactListResponseDto getAllContact(@PathVariable Long id) {
		return contactService.getAllContactByUsersId(id);
	}

}
