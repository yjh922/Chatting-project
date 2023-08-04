package org.sp.chat.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import org.sp.chat.client.domain.Member;
import org.sp.chat.client.domain.Message;

import util.DBManager;

public class MessageDAO {
	DBManager dbManager;

	public MessageDAO(DBManager dbManager) {
		this.dbManager = dbManager;
	}

}
	
