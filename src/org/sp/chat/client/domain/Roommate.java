package org.sp.chat.client.domain;

public class Roommate {
	private int roommate_idx;
	private Room room;
	private Member member;
	
	public int getRoommate_idx() {
		return roommate_idx;
	}
	public void setRoommate_idx(int roommate_idx) {
		this.roommate_idx = roommate_idx;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
	
}
