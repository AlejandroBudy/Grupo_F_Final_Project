package com.upm.es.grupof.users.entities;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UserTest {

	private static final String NAME = "Alex";
	private static final String SURNAME = "Torres";
	private static final String EMAIL = "a@a.com";
	private static final String PASSWORD = "test";
	private static final String ROLE_USER = "ROLE_USER";
	private User user;

	@Before
	public void setUp(){
		GrantedAuthority[] userRoles = { new SimpleGrantedAuthority(ROLE_USER) };
		this.user =  new User(NAME,
				SURNAME,
				EMAIL,
				PASSWORD,
				Arrays.asList(userRoles));
	}

	@Test
	public void shouldNotBeNull(){
		assertNotNull(this.user);
	}

	@Test
	public void testGetterAndSetter(){
		assertEquals(this.user.getEmail(),EMAIL);
		assertEquals(this.user.getName(), NAME);
		assertEquals(getAuthorityRole(),ROLE_USER);
		assertEquals(this.user.getSurname(),SURNAME);
		assertTrue(isSamePassword());
		}

	private String getAuthorityRole() {
		return this.user.getRoles().iterator().next().getAuthority();
	}

	private boolean isSamePassword() {
		return new BCryptPasswordEncoder().matches(PASSWORD,user.getPassword());
	}
}
