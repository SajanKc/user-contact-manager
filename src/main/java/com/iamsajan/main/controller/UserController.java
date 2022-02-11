package com.iamsajan.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.iamsajan.main.dto.UserCreateDto;
import com.iamsajan.main.dto.UserResponseDto;
import com.iamsajan.main.dto.UserUpdateDto;
import com.iamsajan.main.model.User;
import com.iamsajan.main.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public UserResponseDto addUser(@RequestBody UserCreateDto request) {
		return userService.addUser(request);
	}

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public UserListResponseDto getAll() {
		return userService.getAll();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}

	@DeleteMapping("/email/{email}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteByEmail(@PathVariable String email) {
		userService.deleteByEmail(email);
	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public User update(@PathVariable Long id, @RequestBody UserUpdateDto request) {
		return userService.update(id, request);
	}

	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public UserResponseDto get(@PathVariable Long id) {
		return userService.get(id);
	}

}
