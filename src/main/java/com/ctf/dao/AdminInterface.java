package com.ctf.dao;

import com.ctf.model.Admin;

public interface AdminInterface {
	
	void update(Admin admin);
	Admin admin(Integer id);
	Admin login(String email, String password);
}
