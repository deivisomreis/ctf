package com.ctf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ctf.connection.ConnectionFactory;
import com.ctf.model.Agenda;
import com.ctf.model.User;

public class AgendaDAO implements AgendaInterface {

	@Override
	public void insert(Agenda agenda) {
		if(agenda != null && agenda.getUser() != null){
			
			EntityManager manager = ConnectionFactory.getConnection();
			
			try{				
				manager.getTransaction().begin();
				manager.persist(agenda);
				manager.getTransaction().commit();
			}
			catch(Exception ex){
				ex.printStackTrace();
				manager.getTransaction().rollback();
			}
			finally {
				manager.close();
			}
		}
	}

	@Override
	public void remove(Integer id) {
		if(id != null && id > 0){
			
			EntityManager manager = ConnectionFactory.getConnection();
			
			try{
				Agenda agendaObj = manager.find(Agenda.class, id);
				
				if(agendaObj != null){
					manager.getTransaction().begin();
					manager.remove(agendaObj);
					manager.getTransaction().commit();
				}
			}
			catch(Exception  ex){
				ex.printStackTrace();
			}
			finally {
				manager.close();
			}
		}
	}

	@Override
	public void update(Agenda agenda) {
		if(agenda != null){
			
			EntityManager manager = ConnectionFactory.getConnection();
			
			try{
				manager = ConnectionFactory.getConnection();
				manager.merge(agenda);
			}
			catch(Exception ex){
				ex.printStackTrace();
				manager.getTransaction().rollback();
			}
			finally {
				manager.close();
			}
		}
	}

	@Override
	public Agenda agenda(Integer id) {
		if(id != null && id >0){
			
			EntityManager manager = ConnectionFactory.getConnection();
			
			try{
				manager = ConnectionFactory.getConnection();
				return manager.find(Agenda.class, id);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally {
				manager.close();
			}
		}
		return null; // caso de um erro no exception ou nao atendas a validacao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Agenda> agendas(User user) {
		if(user != null){
			
			EntityManager manager = ConnectionFactory.getConnection();
			
			try{
				manager = ConnectionFactory.getConnection();
				
				Query  query = manager.createQuery("from Agenda where user=:user");
				query.setParameter("user", user);
				
				return query.getResultList();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally {
				manager.close();
			}
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Agenda> agendas() {
		EntityManager manager = ConnectionFactory.getConnection();
		
		try{
			manager = ConnectionFactory.getConnection();
			
			return manager.createQuery("from Agenda").getResultList();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally {
			manager.close();
		}
		
		return null;
	}

}
