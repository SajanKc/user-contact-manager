package com.iamsajan.main.dto;

public class UserUpdateDto extends UserCreateDto {
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
