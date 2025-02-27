/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.essentials.chapter8;

import org.zkoss.essentials.chapter5.AuthenticationServiceChapter5Impl;

import org.zkoss.essentials.entity.User;
import org.zkoss.essentials.services.UserCredential;

import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

public class AuthenticationServiceChapter8Impl extends AuthenticationServiceChapter5Impl{
	private static final long serialVersionUID = 1L;
	

	
	@Override
	public boolean login(String nm, String pd) {
		//buscar al usuario y devolverlo
		User user = new clsVerificarDatos().getUsuario(nm, pd);
		//a simple plan text password verification
		if(user==null || !user.getPassword().equals(pd)){
			return false;
		}
		
		Session sess = Sessions.getCurrent();
		UserCredential cre = new UserCredential(user.getAccount(),user.getFullName());
		//just in case for this demo.
		if(cre.isAnonymous()){
			return false;
		}
		sess.setAttribute("userCredential",cre);
		sess.setAttribute("usuario",user.getAccount());
		//TODO handle the role here for authorization
		return true;
	}
	
	@Override
	public void logout() {
		Session sess = Sessions.getCurrent();
		sess.removeAttribute("userCredential");
	}
}
