package org.sp.chat.client.domain;

public class Message {
	private int message_idx;
	private Member send;
	private Member receive;
	private String time;
	private String contents;
	private int readyn;
	private Roommate roommate;
	
	public int getMessage_idx() {
		return message_idx;
	}
	public void setMessage_idx(int message_idx) {
		this.message_idx = message_idx;
	}
	public Member getSend() {
		return send;
	}
	public void setSend(Member send) {
		this.send = send;
	}
	public Member getReceive() {
		return receive;
	}
	public void setReceive(Member receive) {
		this.receive = receive;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getReadyn() {
		return readyn;
	}
	public void setReadyn(int readyn) {
		this.readyn = readyn;
	}
	public Roommate getRoommate() {
		return roommate;
	}
	public void setRoommate(Roommate roommate) {
		this.roommate = roommate;
	}
	
	
	
}
