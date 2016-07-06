package com.ctf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ctf.connection.ConnectionFactory;
import com.ctf.model.FinancialControl;
import com.ctf.model.User;

public class FinancialControlDAO implements FinancialControlInterface {

	@Override
	public void insert(FinancialControl fc) {
		if(fc != null){
			EntityManager manager = ConnectionFactory.getConnection();
			
			try{
				manager.persist(fc);
			}
			catch(Exception ex){
				manager.getTransaction().rollback();
				ex.printStackTrace();
			}
			finally {
				manager.close();
			}
		}
		

	}

	@Override
	public void remove(FinancialControl fc) {
		if(fc != null){
			EntityManager manager = ConnectionFactory.getConnection();
			
			try{
				manager.remove(fc);
			}
			catch(Exception ex){
				manager.getTransaction().rollback();
				ex.printStackTrace();
			}
			finally {
				manager.close();
			}
		}
	}

	@Override
	public void update(FinancialControl fc) {
		if(fc != null){
			EntityManager manager = ConnectionFactory.getConnection();
			
			try{
				manager.merge(fc);
			}
			catch(Exception ex){
				manager.getTransaction().rollback();
				ex.printStackTrace();
			}
			finally {
				manager.close();
			}
		}
	}

	@Override
	public FinancialControl fc(Integer id) {
		if(id !=  null && id >0){
			EntityManager manager = ConnectionFactory.getConnection();
			
			try{
				return manager.find(FinancialControl.class, id);
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
	public List<FinancialControl> fcs(User user) {
		if(user != null){
			EntityManager manager = ConnectionFactory.getConnection();
			
			try{
				Query query = manager.createQuery("from FinancialControl where user=:user");
				query.setParameter("user", user);
				
				return query.getResultList();
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

}
