package com.ctf.dao;

import java.util.List;

import com.ctf.model.User;

public interface UserInterface {
	
	void insert(User user);
	void remove(Integer id);
	void update(User user);
	User user(Integer id);
	User login(String email, String password);
	List<User> users();
}
