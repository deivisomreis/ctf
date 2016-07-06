package com.ctf.dao;

import java.util.List;

import com.ctf.model.Agenda;
import com.ctf.model.User;

public interface AgendaInterface {
	
	void insert(Agenda agenda);
	void remove(Agenda agenda);
	void update(Agenda agenda);
	Agenda agenda(Integer id);
	List<Agenda> agendas(User user);
	List<Agenda> agendas();
}
