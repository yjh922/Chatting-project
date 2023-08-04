package org.sp.chat.client.model;


import util.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sp.chat.client.domain.Member;

import util.DBManager;

public class MemberDAO {

	DBManager dbManager;
	
	public MemberDAO(DBManager dbManager) {
		this.dbManager=dbManager;
	}

	
	public List selectAll() {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> list = new ArrayList<Member>();
		
		StringBuilder sb = new StringBuilder();
		sb.append("select * from member order by member_idx asc");
		
		con = dbManager.connect();
		
		try {
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Member member = new Member();
				member.setMember_idx(rs.getInt("member_idx"));
				member.setId(rs.getString("id"));
				member.setPass(rs.getString("pass"));
				member.setName(rs.getString("name"));
				member.setNick(rs.getString("nick"));
				member.setEmail(rs.getString("email"));
				member.setImg(rs.getString("img"));
				
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}
		return list;
	}


}
