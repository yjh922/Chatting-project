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
		this.dbManager=dbManager;
	}
	
	//메시지 등록하기
	public int insert(Message message) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		int result=0;
		
		con=dbManager.connect();
		
		StringBuilder sb=new StringBuilder();
		sb.append("insert into Message(message_idx, send,receive,rommate_idx,contents)");
		sb.append(" values(seq_message.nextval, ?,?,?,? )");
		
		try {
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setInt(1,message.getMember().getMember_idx());
			pstmt.setInt(2, result);
			pstmt.setInt(3, message.getRoommate().getRoommate_idx());
			pstmt.setString(4, message.getContents());
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}
}
