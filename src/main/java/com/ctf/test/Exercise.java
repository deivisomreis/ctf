package com.ctf.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ctf.dao.AdminDAO;
import com.ctf.dao.AgendaDAO;
import com.ctf.dao.SessionActiveDAO;
import com.ctf.dao.UserDAO;
import com.ctf.model.Admin;
import com.ctf.model.Agenda;
import com.ctf.model.User;

public class Exercise {
	
	public static void main(String[] args) {	
		SessionActiveDAO sessionDAO = new SessionActiveDAO();
		UserDAO userDAO = new UserDAO();
		
		sessionDAO.lancarNovaSessao(userDAO.users());
		
		
	}
}
