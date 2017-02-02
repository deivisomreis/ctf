package com.ctf.criptografia;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CriptografarSenha {
	
	public static String criptografarSenha(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		if(senha != null && !senha.isEmpty()){
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] algoritmo = md.digest(senha.getBytes());
			
			String dadoCriptografado = 	new String(algoritmo, "UTF-8");
			
			return dadoCriptografado;			
		}
		else
			return null;
	}

}
