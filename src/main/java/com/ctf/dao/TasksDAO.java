package com.ctf.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ctf.connection.ConnectionFactory;
import com.ctf.model.Tasks;
import com.ctf.model.User;

public class TasksDAO implements TasksInterface {
	
	@Override
	public void insert(Tasks tasks) {		
		if(tasks != null){
			EntityManager manager =  ConnectionFactory.getConnection();
			
			Date date = new Date();
			tasks.setRegistered(date);
			
			try{
				manager.getTransaction().begin();
				manager.persist(tasks);			
				manager.getTransaction().commit();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally {
				manager.close();
			}
		}

	}

	@Override
	public void remove(Integer id) {
		if(id  != null && id > 0){
			EntityManager manager = ConnectionFactory.getConnection();
			
			try{
				Tasks task = manager.find(Tasks.class, id);
				
				manager.getTransaction().begin();
				manager.remove(task);
				manager.getTransaction().commit();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally {
				manager.close();
			}
		}

	}

	@Override
	public void update(Tasks tasks) {
		if(tasks != null){
			EntityManager manager = ConnectionFactory.getConnection();
			
			try{
				manager.merge(tasks);
			}
			catch(Exception  ex){
				ex.printStackTrace();
			}
			finally{
				manager.close();
			}
		}
	}

	@Override
	public Tasks tasks(Integer id) {
		if(id != null && id > 0){
			EntityManager manager = ConnectionFactory.getConnection();			
			try{
				return manager.find(Tasks.class, id);
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
	public List<Tasks> tasks(User user) {
		if(user != null){
			EntityManager manager = ConnectionFactory.getConnection();
			
			try{
				Query query = manager.createQuery("from Tasks where user=:user order by id desc");
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
	public List<Tasks> tasks() {
		EntityManager manager = ConnectionFactory.getConnection();
		
		try{
			return manager.createQuery("from Tasks").getResultList();
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
