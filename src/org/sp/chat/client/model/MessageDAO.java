package org.sp.chat.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sp.chat.client.domain.Member;
import org.sp.chat.client.domain.Message;

import util.DBManager;

public class MessageDAO {
	DBManager dbManager;

	public MessageDAO(DBManager dbManager) {
		this.dbManager = dbManager;
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
	
	public List selectAll() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Message> list = new ArrayList<Message>();
		
		con = dbManager.connect();
		
		StringBuilder sb = new StringBuilder();

		sb.append("select member_idx, id, pass,name, nick, email, img");
		sb.append(" from friend f, member m");
		sb.append(" where f.you=m.member_idx");
		sb.append(" and me=? order by name asc");
		
		String sql="select * from message m, roommate r where r.room_idx=";
		
		try {
			pstmt = con.prepareStatement(sb.toString());
			//pstmt.setInt(1, me);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Member dto=new Member();
				
				dto.setMember_idx(rs.getInt("member_idx"));
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setNick(rs.getString("nick"));
				dto.setEmail(rs.getString("email"));
				dto.setImg(rs.getString("img"));
				
				
				//list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			 dbManager.release(con, pstmt, rs);
		}
		return list;
	}

}
	
