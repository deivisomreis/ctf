package com.ctf.dao;

import java.util.List;

import com.ctf.model.Tasks;
import com.ctf.model.User;

public interface TasksInterface {
	
	void insert(Tasks tasks);
	void remove(Integer id);
	void update(Tasks tasks);
	Tasks tasks(Integer id);
	List<Tasks> tasks(User user);
	List<Tasks> tasks();
}
