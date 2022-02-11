package com.iamsajan.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iamsajan.main.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	void deleteByEmail(String email);
}
