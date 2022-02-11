package com.iamsajan.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iamsajan.main.controller.UserListResponseDto;
import com.iamsajan.main.dto.ContactCreateDto;
import com.iamsajan.main.dto.UserCreateDto;
import com.iamsajan.main.dto.UserResponseDto;
import com.iamsajan.main.dto.UserUpdateDto;
import com.iamsajan.main.model.Contact;
import com.iamsajan.main.model.User;
import com.iamsajan.main.repository.ContactRepository;
import com.iamsajan.main.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ContactRepository contactRepository;

	public UserResponseDto addUser(UserCreateDto request) {
		User user = new User();
		user.setEmail(request.getEmail());
		user.setName(request.getName());
		user.setPassword(request.getPassword());
		user.setMobileNumber(request.getMobileNumber());
		User savedUser = userRepository.save(user);

		List<ContactCreateDto> contactRequests = request.getContacts();

		for (int i = 0; i < contactRequests.size(); i++) {
			Contact contact = new Contact();
			contact.setEmail(contactRequests.get(i).getEmail());
			contact.setMobileNumber(contactRequests.get(i).getMobileNumber());
			contact.setUser(user);
			contactRepository.save(contact);
		}
		return getUserResponseDto(savedUser);
	}

	private UserResponseDto getUserResponseDto(User savedUser) {
		UserResponseDto response = new UserResponseDto();
		response.setEmail(savedUser.getEmail());
		response.setId(savedUser.getId());
		response.setMobileNumber(savedUser.getMobileNumber());
		response.setName(savedUser.getName());
		response.setPassword(savedUser.getPassword());

		List<Contact> contacts = contactRepository.findByUserId(savedUser.getId());

		List<ContactCreateDto> contactDtoList = new ArrayList<>();

		for (Contact contact : contacts) {
			ContactCreateDto dto = new ContactCreateDto();
			dto.setEmail(contact.getEmail());
			dto.setMobileNumber(contact.getMobileNumber());
			contactDtoList.add(dto);
		}
		response.setContacts(contactDtoList);
		return response;
	}

	public UserListResponseDto getAll() {
		List<UserResponseDto> userResponseList = new ArrayList<>();

		List<User> users = (List<User>) userRepository.findAll();

		for (User user : users) {
			userResponseList.add(getUserResponseDto(user));
		}

		UserListResponseDto response = new UserListResponseDto();
		response.setUsers(userResponseList);
		response.setTotal(userResponseList.size());
		return response;
	}

	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	@Transactional
	public void deleteByEmail(String email) {
		contactRepository.deleteByUserEmail(email);
		userRepository.deleteByEmail(email);
	}

	public User update(Long id, UserUpdateDto request) {
		Optional<User> optional = userRepository.findById(id);
		if (optional.isPresent()) {
			User user = optional.get();
			user.setEmail(request.getEmail());
			user.setName(request.getName());
			user.setPassword(request.getPassword());
			user.setMobileNumber(request.getMobileNumber());
			return userRepository.save(user);
		}
		return null;
	}

	public UserResponseDto get(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			return getUserResponseDto(optionalUser.get());
		}
		return null;
	}

}
