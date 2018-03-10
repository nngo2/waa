package edu.mum.coffee.service;

import edu.mum.coffee.domain.User;

public interface UserService {
	User findByEmail(String email);
	void saveUser(User user);
}
