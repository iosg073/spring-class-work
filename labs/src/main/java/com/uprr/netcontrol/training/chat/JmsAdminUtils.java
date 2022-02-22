package com.uprr.netcontrol.training.chat;

import com.tibco.tibjms.admin.TibjmsAdmin;

public class JmsAdminUtils {

	private TibjmsAdmin admin;
	
	public JmsAdminUtils(TibjmsAdmin admin) {
		this.admin = admin;
	}
	
	//TODO: implement this, return all queue names staring with CHAT. that don't match your own, remove the CHAT. prefix
	public String[] getQueueNames(String myQueueName) {
		return null;
	}
}
