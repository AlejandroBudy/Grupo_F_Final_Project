package com.upm.es.grupof.users.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	private String surname;
	private String email;



	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<GrantedAuthority> roles;

	public User(){}

	public User(String name, String surname, String email, String password,List<GrantedAuthority> roles){
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = new BCryptPasswordEncoder().encode(password);
		this.roles = roles;
	}

	public String getName() {
		return name;
	}


	public String getSurname() {
		return surname;
	}


	public String getEmail() {
		return email;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public List<GrantedAuthority> getRoles() {
		return roles;
	}

}
