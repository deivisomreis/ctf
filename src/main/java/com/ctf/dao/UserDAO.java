package com.ctf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ctf.connection.ConnectionFactory;
import com.ctf.model.User;

public class UserDAO implements UserInterface {

	@Override
	public void insert(User user) {
		if(user != null && !user.getName().isEmpty() && !user.getPassword().isEmpty() && !user.getEmail().isEmpty()){
			
			EntityManager manager = ConnectionFactory.getConnection();
			
			try{				
				manager.getTransaction().begin();
				manager.persist(user);
				manager.getTransaction().commit();
			}
			catch(Exception  ex){
				ex.printStackTrace();

			}
			finally {
				manager.close();
			}
		}
	}
	
	// Método remove sem uso!
	@Override
	public void remove(Integer id) {
		if(id != null && id >0){
			
			EntityManager manager = ConnectionFactory.getConnection();
			User user = manager.find(User.class, id);
			
			try{
				 manager.createNativeQuery("delete User, Tasks from User inner join Tasks on User.id = Tasks.user_id where User.id=23");
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
	public void update(User user) {
		if(user != null && !user.getName().isEmpty() && !user.getCpf().isEmpty() && !user.getEmail().isEmpty() && !user.getPassword().isEmpty()){
			
			EntityManager manager = ConnectionFactory.getConnection();
			
			try{
				User userObj = manager.find(User.class, user.getId());
				userObj.setCpf(user.getCpf());
				userObj.setEmail(user.getEmail());
				userObj.setName(user.getName());
				userObj.setPassword(user.getPassword());				
				
				manager.getTransaction().begin();
				manager.merge(userObj);
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
	public User user(Integer id) {
		if(id != null && id >0){
			
			EntityManager manager = ConnectionFactory.getConnection();
			
			try{
				return manager.find(User.class, id);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally {
				manager.close();
			}
		}
		
		return null; // retorna null caso de um erro no catch
	}

	@Override
	public User login(String email, String password) {
		if(email != null && password != null && !email.isEmpty() && !password.isEmpty()){
			
			EntityManager manager = ConnectionFactory.getConnection();
			
			try{				
				Query query = manager.createQuery("from User where email=:email and password=:password");
				query.setParameter("email", email);
				query.setParameter("password", password);
				
				return (User) query.getSingleResult();
			}
			catch(Exception  ex){
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
	public List<User> users() {
		EntityManager manager = ConnectionFactory.getConnection();
		
		try{
			return manager.createQuery("from User order by id desc").getResultList();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally {
			manager.close();
		}
		
		return null;
	}
	
	public void activeOrDesactiveUser(Integer id){
		if(id != null && id >0){
			EntityManager manager = ConnectionFactory.getConnection();
			
			try{
				User user = manager.find(User.class, id);
				
				if(user != null){
					if(user.isStatus()){
						user.setStatus(false);
						manager.getTransaction().begin();
						manager.merge(user);
						manager.getTransaction().commit();
					}
					else{
						user.setStatus(true);
						manager.getTransaction().begin();
						manager.merge(user);
						manager.getTransaction().commit();
					}
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

}
