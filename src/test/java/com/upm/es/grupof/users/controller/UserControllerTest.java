package com.upm.es.grupof.users.controller;

import com.upm.es.grupof.users.entities.User;
import com.upm.es.grupof.users.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

public class UserControllerTest {

	@InjectMocks
	private UserController controller;

	@Mock
	private UserService service;
	private User existing_user;
	private User non_existing_user;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		GrantedAuthority[] userRoles = { new SimpleGrantedAuthority("ROLE_USER") };
		this.existing_user = new User("Alex",
				"Torres",
				"a@a.com",
				"test", Arrays.asList(userRoles));
		this.non_existing_user = new User("John",
				"Doe",
				"e@e.com",
				"test", Arrays.asList(userRoles));
		doNothing().when(this.service).logUserIn(this.existing_user);
		doThrow(new BadCredentialsException("Bad credentials")).
				when(this.service).logUserIn(this.non_existing_user);
	}

	@Test
	public void shouldLogInWhenUserExists(){
		this.controller.login(this.existing_user);
	}

	@Test(expected = BadCredentialsException.class)
	public void shouldThrowBadCredentialsExceptionWhenUserNotExists(){
		this.controller.login(this.non_existing_user);
	}
}
