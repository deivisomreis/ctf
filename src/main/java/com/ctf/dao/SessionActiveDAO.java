package com.ctf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ctf.connection.ConnectionFactory;
import com.ctf.model.SessionActive;
import com.ctf.model.User;

public class SessionActiveDAO implements SessionActiveInterface {

	@SuppressWarnings("unchecked")
	@Override
	public List<SessionActive> sessoes() {
		EntityManager manager = ConnectionFactory.getConnection();
		
		try{
			Query query = manager.createQuery("from SessionActive where status=:status");
			
			query.setParameter("status", true);
			
			return query.getResultList();
		}
		catch(Exception  ex){
			ex.printStackTrace();
			return null;
		}
		finally {
			manager.close();
		}
	}
	
	public void lancarNovaSessao(List<User> usuarios){
		if(usuarios != null && usuarios.size() >0){
			EntityManager manager = ConnectionFactory.getConnection();
			
			try{
				for(User user : usuarios){					
					SessionActive session = new SessionActive();
					session.setUser(user);	
					
					Query query = manager.createQuery("from SessionActive where user=:user");
					query.setParameter("user", user);
					
					if(query.getResultList().size() > 0)
						System.out.println("Existe");
					
					else{
						manager.getTransaction().begin();
						manager.persist(session);
						manager.getTransaction().commit();
						
						System.out.println("Gravado");
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally {
				manager.close();
			}
		}
	}

}
