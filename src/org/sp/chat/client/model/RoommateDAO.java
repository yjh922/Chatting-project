package org.sp.chat.client.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sp.chat.client.domain.Member;
import org.sp.chat.client.domain.Room;
import org.sp.chat.client.domain.Roommate;

import util.DBManager;

public class RoommateDAO {
	DBManager dbManager;
	public RoommateDAO(DBManager dbManager) {
		this.dbManager = dbManager;
	}
	
	public int insert(Roommate roommate) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		con=dbManager.connect();
		
		String sql="insert into roommate(roommate_idx, room_idx, member_idx)";
		sql+=" values(seq_roommate.nextval, ?,?)";
		
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, roommate.getRoom().getRoom_idx()); //어느방에 
			pstmt.setInt(2, roommate.getMember().getMember_idx());//누가 
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt);
		}
		
		return result;
	}
	
	public List selectChat(int room_idx) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Roommate> list = new ArrayList<Roommate>();
		
		con = dbManager.connect();
		
		StringBuilder sb = new StringBuilder();
		
		String sql="select member_idx from roommate where room_idx=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, room_idx);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Roommate roommate=new Roommate();
				
				roommate.setRoommate_idx(rs.getInt("roommate_idx"));
				
				Room room=new Room();
				room.setRoom_idx(rs.getInt("room_idx"));
				roommate.setRoom(room);
				
				Member member = new Member();
				member.setMember_idx(rs.getInt("member_idx"));
				roommate.setMember(member);

				list.add(roommate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			 dbManager.release(con, pstmt, rs);
		}
		return list;
	}

	
}
