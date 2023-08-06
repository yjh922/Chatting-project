package org.sp.chat.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sp.chat.client.domain.Friend;
import org.sp.chat.client.domain.Member;

import util.DBManager;

public class FriendDAO {
	DBManager dbManager;

	public FriendDAO(DBManager dbManager) {
		this.dbManager=dbManager;
	}
	
	public List friendAll(int me) {
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> list = new ArrayList<Member>();
		
		con = dbManager.connect();
		
		StringBuilder sb = new StringBuilder();

		sb.append("select member_idx, id, pass,name, nick, email, img");
		sb.append(" from friend f, member m");
		sb.append(" where f.you=m.member_idx");
		sb.append(" and me=? order by name asc");
		
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, me);
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
				
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			 dbManager.release(con, pstmt, rs);
		}
		return list;
	}
}
