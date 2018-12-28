package com.upm.es.grupof.users.services;

import com.upm.es.grupof.database.DataBaseLoader;
import com.upm.es.grupof.users.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserService {

	@Autowired
	DataBaseLoader dataBaseLoader;
	private Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");


	public void createUser(User user) {
	}

	public void logUserIn(User user) {
		User userInDataBase = dataBaseLoader.getUserByMail(user.getEmail());
		this.verifyMailExists(userInDataBase);
		this.verifyCorrectPassword(user, userInDataBase);
	}

	private void verifyCorrectPassword(User user, User userInDataBase) {
		if(!isEncrypted(user.getPassword()) && !new BCryptPasswordEncoder().matches(user.getPassword(),userInDataBase.getPassword()))
			throw new BadCredentialsException("Password doesn't match");
		else if (isEncrypted(user.getPassword()) && !user.getPassword().equals(userInDataBase.getPassword()))
			throw new BadCredentialsException("Password doesn't match");
	}

	private void verifyMailExists(User userInDataBase) {
		if(userInDataBase == null)throw new BadCredentialsException("User not exits");
	}

	private boolean isEncrypted(String password){
		return this.BCRYPT_PATTERN.matcher(password).matches();
	}
}
