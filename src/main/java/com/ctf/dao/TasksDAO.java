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
				Tasks taskObj = manager.find(Tasks.class, tasks.getId());
				
				if(taskObj != null){
					taskObj.setName(tasks.getName());
					taskObj.setNote(tasks.getNote());
					taskObj.setComplete(tasks.getComplete());
					taskObj.setStatus(tasks.isStatus());
					
					manager.getTransaction().begin();
					manager.merge(taskObj);
					manager.getTransaction().commit();
				}
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
				Query query = manager.createQuery("from Tasks where user=:user order by id desc, status");
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
	
	public void finalizeTask(Integer id) {
		if(id != null && id > 0){
			Tasks task = tasks(id);
			
			if(task != null){
				Date dateFinalize = new Date();
				
				task.setComplete(dateFinalize);
				task.setStatus(true);
				
				update(task);
			}
		}
		
	}

}
