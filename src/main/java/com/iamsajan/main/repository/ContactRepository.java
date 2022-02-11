package com.iamsajan.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iamsajan.main.model.Contact;
import com.iamsajan.main.model.User;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {

	List<Contact> findByUser(User user);

	List<Contact> findByUserId(Long id);

	void deleteByUserEmail(String email);

	Long deleteByUserId(Long id);

	@Modifying
	@Transactional
	@Query("delete from Contact c where c.user.id = ?1 and c.id = ?2")
	void deleteByUserIdContactId(Long uId, Long cId);

}
