package org.sp.chat.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.sp.chat.client.domain.Message;

import util.DBManager;

public class MessageDAO {
	DBManager dbManager;
	
	public MessageDAO(DBManager dbManager) {
		this.dbManager=dbManager;
	}
	
	public int insert(Message message) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		con=dbManager.connect();
		
		String sql="insert into message(message_idx, send, receive, contents, roommate_idx)";
		sql+=" values(seq_message.nextval, ?,?,?,?)";
		
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, message.getSend().getMember_idx());
			pstmt.setInt(2, message.getReceive().getMember_idx());
			pstmt.setString(3, message.getContents());
			pstmt.setInt(4, message.getRoommate().getRoommate_idx());
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt);
		}
		
		return result;
	}
}
