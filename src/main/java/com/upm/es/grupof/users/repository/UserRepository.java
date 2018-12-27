package com.upm.es.grupof.users.repository;

import com.upm.es.grupof.users.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {

	@Query(value = "SELECT u from User u where u.email = :email")
	User findByMail(@Param(value = "email") String email);
}
