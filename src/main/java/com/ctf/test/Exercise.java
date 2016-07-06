package com.ctf.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ctf.dao.AdminDAO;
import com.ctf.dao.AgendaDAO;
import com.ctf.dao.UserDAO;
import com.ctf.model.Admin;
import com.ctf.model.Agenda;
import com.ctf.model.User;

public class Exercise {
	
	public static void main(String[] args) {	
		AdminDAO dao = new AdminDAO();
		UserDAO usedao = new UserDAO();
		User user = usedao.login("deivisomreis@bol.com.br", "12345");
		
		
		Agenda agenda = new Agenda();
		
		agenda.setUser(user);
		agenda.setCellPhoneNumber("961096210");
		agenda.setEmail("deboradias95@gmail.com");
		agenda.setName("Debora");
		agenda.setNote("Numero da minha esposa");
		agenda.setPhoneNumber("2020-2020");
		
		
		AgendaDAO agendadao = new AgendaDAO();
		
		agendadao.insert(agenda);
		
		List<Agenda>  agendas = agendadao.agendas(user);
		
		for(Agenda agenda2: agendas){
			System.out.println(agenda2.getName());
		}
		
	}
}
