package com.ctf.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
	
	private static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<EntityManager>();
	private static EntityManagerFactory instance;
	
	
	public static void start() {
		if (instance == null) {
			synchronized (ConnectionFactory.class) {
				if (instance == null) {
					EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ctf");
					instance = entityManagerFactory; 
				}
			}
		}
	}
	
	public static void stop() {
		if (instance != null && instance.isOpen()) {
			instance.close();
			instance = null;
		}
	}
	
	public static EntityManager getConnection() {
		EntityManager entityManager = threadLocal.get();
		if (entityManager == null || !entityManager.isOpen()) {			
			if (instance == null) {
				start();
			}
			synchronized (ConnectionFactory.class) {
				entityManager = instance.createEntityManager();
				threadLocal.set(entityManager);	
			}
		}
		return entityManager;
	}
}
