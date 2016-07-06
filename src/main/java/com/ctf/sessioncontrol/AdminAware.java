package com.ctf.sessioncontrol;

import com.ctf.model.Admin;

public class AdminAware {
	
	public static boolean isAdmin(Object obj){
		if(obj instanceof Admin)
			return true;
		
		else
			return false;
	}
}
