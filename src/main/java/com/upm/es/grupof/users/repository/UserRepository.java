package com.upm.es.grupof.users.repository;

import com.upm.es.grupof.users.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByMail(String mail);
}
