package com.upm.es.grupof.users.services;

import com.upm.es.grupof.database.DataBaseLoader;
import com.upm.es.grupof.users.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceTest {

	@InjectMocks
	private UserService service;

	@Mock
	private DataBaseLoader dataBaseLoader;

	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		when(this.dataBaseLoader.getUserByMail("atorresato@gmail.com")).
				thenReturn(this.buildCorrectUser());
		when(this.dataBaseLoader.getUserByMail("a@a.com")).
				thenReturn(null);
	}

	@Test
	public void shouldReturnUser(){
		User user = this.buildCorrectUser();
		user.setPassword("test");
		this.service.logUserIn(user);
	}
	@Test
	public void shouldThrowExceptionUserNotExists(){
		try{
			this.service.logUserIn(this.buildInCorrectUser());
		}catch (BadCredentialsException e){
			assertEquals(e.getMessage(),"User not exits");
		}
	}
	@Test
	public void shouldThrowExceptionBadPassword(){
		try{
			this.service.logUserIn(this.buildInCorrectPassword());
		}catch (BadCredentialsException e){
			assertEquals(e.getMessage(),"Password doesn't match");
		}
	}

	private User buildInCorrectUser(){
		GrantedAuthority[] userRoles = { new SimpleGrantedAuthority("ROLE_USER") };
		return new User("Alex",
				"Torres",
				"a@a.com",
				"test", Arrays.asList(userRoles));
	}
	private User buildCorrectUser(){
		GrantedAuthority[] userRoles = { new SimpleGrantedAuthority("ROLE_USER") };
		return new User("Alex",
				"Torres",
				"atorresato@gmail.com",
				"test", Arrays.asList(userRoles));
	}
	private User buildInCorrectPassword(){
		GrantedAuthority[] userRoles = { new SimpleGrantedAuthority("ROLE_USER") };
		return new User("Alex",
				"Torres",
				"atorresato@gmail.com",
				"test2", Arrays.asList(userRoles));
	}
}
