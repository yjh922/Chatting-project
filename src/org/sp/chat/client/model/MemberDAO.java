package org.sp.chat.client.model;

import util.DBManager;

public class MemberDAO {
	DBManager dbManager;
	
	public MemberDAO(DBManager dbManager) {
		this.dbManager=dbManager;
	}
}
