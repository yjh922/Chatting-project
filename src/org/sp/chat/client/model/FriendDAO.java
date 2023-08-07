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
	
	//이미 로그인한 회원의 친구인지 아닌지 조회
	public Friend check(Friend friend) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Friend dto = null;
		
		con=dbManager.connect();
		//현재 로그인한 사람의 친구들을 가져온다 
		//String sql="select * from friend where me=로그인한 사람의idx and you=선택된 idx";
		
		String sql="select * from friend where me=? and you=?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, friend.getMe().getMember_idx()); //나의 dix 
			pstmt.setInt(2, friend.getYou().getMember_idx());//선택한 친구 idx
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				dto = new Friend();
				
				dto.setFriend_idx(rs.getInt("friend_idx"));
				
				//로그인한 나에 대한 정보 
				Member me = new Member();
				me.setMember_idx(rs.getInt("me"));
				dto.setMe(me);
				
				//친구의 정보
				Member you = new Member();
				you.setMember_idx(rs.getInt("you"));
				dto.setYou(you);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}
		return dto;
	}
	
	//친구 추가 
	public int insert(Friend friend) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		con=dbManager.connect();
		String sql="insert into friend(friend_idx, me, you) values(seq_friend.nextval, ?,?)";

		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, friend.getMe().getMember_idx()); //나의 idx 
			pstmt.setInt(2, friend.getYou().getMember_idx()); //너의 즉 친구의 idx 
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}
}



















