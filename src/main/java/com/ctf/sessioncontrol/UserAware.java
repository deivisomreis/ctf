package com.ctf.sessioncontrol;

import com.ctf.model.User;

public class UserAware {
	
	public static boolean isUser(Object obj){
		if(obj instanceof User)
			return true;
		
		else
			return false;
	}

}
