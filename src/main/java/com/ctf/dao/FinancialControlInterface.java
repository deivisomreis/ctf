package com.ctf.dao;

import java.util.List;

import com.ctf.model.FinancialControl;
import com.ctf.model.User;

public interface FinancialControlInterface {
	
	void insert(FinancialControl fc);
	void remove(FinancialControl fc);
	void update(FinancialControl fc);
	FinancialControl fc(Integer id);
	List<FinancialControl> fcs(User user);
}
