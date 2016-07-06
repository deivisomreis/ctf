package com.ctf.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ctf.connection.ConnectionFactory;
import com.ctf.model.Admin;

public class AdminDAO implements AdminInterface {

	@Override
	public void update(Admin admin) {
		if(admin != null && !admin.getEmail().isEmpty() && !admin.getPassword().isEmpty() && !admin.getName().isEmpty()){
			
			EntityManager manager = ConnectionFactory.getConnection();
			
			try{
				manager.getTransaction().begin();
				manager.merge(admin);
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
	public Admin admin(Integer id) {
		if(id != null && id > 0){
			
			EntityManager manager = ConnectionFactory.getConnection();
			
			try{				
				return manager.find(Admin.class, id);
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

	@Override
	public Admin login(String email, String password) {
		if(email != null && password != null && !email.isEmpty() && !password.isEmpty()){
			
			EntityManager manager = ConnectionFactory.getConnection();
			
			try{
				manager = ConnectionFactory.getConnection();
				Query query = manager.createQuery("from Admin where email=:email and password=:password");
				
				query.setParameter("email", email);
				query.setParameter("password", password);
				
				return (Admin) query.getSingleResult();
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

}
