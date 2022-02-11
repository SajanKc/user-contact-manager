package com.iamsajan.main.controller;

import java.util.List;

import com.iamsajan.main.dto.UserResponseDto;

public class UserListResponseDto {
	private List<UserResponseDto> users;
	private int total;

	public List<UserResponseDto> getUsers() {
		return users;
	}

	public void setUsers(List<UserResponseDto> users) {
		this.users = users;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
