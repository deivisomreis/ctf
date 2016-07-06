package com.ctf.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.ctf.connection.ConnectionFactory;

@WebListener
public class ConnectionListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce)  { 
         ConnectionFactory.stop();
    }

    public void contextInitialized(ServletContextEvent sce)  { 
         ConnectionFactory.start();
    }
	
}
