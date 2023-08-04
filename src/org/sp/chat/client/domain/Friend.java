package org.sp.chat.client.domain;

public class Friend {
	private int friend_idx;

	private Member me;
	private Member you;
	private Member member;
	

	public int getFriend_idx() {
		return friend_idx;
	}
	public void setFriend_idx(int friend_idx) {
		this.friend_idx = friend_idx;
	}

	public Member getMe() {
		return me;
	}
	public void setMe(Member me) {
		this.me = me;
	}
	public Member getYou() {
		return you;
	}
	public void setYou(Member you) {
		this.you = you;
	}
	

	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}

}
