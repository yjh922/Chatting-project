package org.sp.chat.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.sp.chat.client.domain.Room;

import util.DBManager;

public class RoomDAO {
	DBManager dbManager;

	
	public RoomDAO(DBManager dbManager) {
		this.dbManager=dbManager;

	}
	
	public List selectAll() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Room> list = new ArrayList<Room>();
		
		StringBuilder sb = new StringBuilder();
		sb.append("select * from room order by room_idx asc");
		
		con = dbManager.connect();
		try {
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Room room = new Room();
				room.setRoom_idx(rs.getInt("room_idx"));
				room.setRoomname(rs.getString("roomname"));
				
				
				list.add(room);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			 dbManager.release(con, pstmt, rs);
		}
		return list;
	}
	
	public int insert(Room room) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;// insert에 의해 발생되는 pk값을 가져오기 위해
		int result=0;
		
		con=dbManager.connect();
		
		String sql="insert into room(room_idx, roomname) values(seq_room.nextval, ?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, room.getRoomname());
			
			result=pstmt.executeUpdate();
			
			//insert가 완료되면 room dto안의 room_idx값을 방금 들어간 시퀀스값으로 대입
			if(result>0) {
				sql="select seq_room.currval as room_idx from dual";
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					room.setRoom_idx(rs.getInt("room_idx"));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}
		
		return result;
	}
}












