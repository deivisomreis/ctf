package com.ctf.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Automatization {
	
	private static int delay = 1000; //5s
	private static int interval = 1000 * 1;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM HH:mm:ss");
	
	private static Timer timer = new Timer();
	
	public static void startAutomatization(){
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println(sdf.format(new Date()));
				
			}},delay,interval); 
	}
}
