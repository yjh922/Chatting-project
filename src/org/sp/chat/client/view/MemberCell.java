package org.sp.chat.client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.sp.chat.client.domain.Friend;
import org.sp.chat.client.domain.Member;

import util.ImageUtil;

public class MemberCell extends JPanel{
	ChatMain chatMain;
	JLabel la_icon;
	JLabel la_name;
	Member member; //셀에 들어간 사람 즉 친구 
	FriendPage friendPage;
	
	public MemberCell(FriendPage friendPage,ChatMain chatMain, Member member){
		this.friendPage=friendPage;
		this.chatMain=chatMain;
		this.member=member;
		
		la_icon = new JLabel(new ImageIcon(ImageUtil.getImage(member.getImg(), 40, 40)));
		la_icon.setPreferredSize(new Dimension(20, 20));
		la_name =new JLabel(member.getName());
		
		setLayout(null);
		//부착
		add(la_icon);
		add(la_name);
		la_icon.setBounds(10, 12, 40, 40);
		la_name.setBounds(60, 12, 200, 40);
		
		setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		setPreferredSize(new Dimension(290, 50));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("클릭했어?");
				
				int result=JOptionPane.showConfirmDialog( MemberCell.this, "친구 추가하시겠습니까?");
				
				if(result==JOptionPane.OK_OPTION) { //ok 누르면..
					Friend friend = new Friend();
					
					friend.setMe(chatMain.member); //로그인 한 사용자 
					friend.setYou(member);//선택한 친구 
					
					Friend dto = chatMain.friendDAO.check(friend);
					
					if(dto!=null) {
						JOptionPane.showMessageDialog(MemberCell.this, "이미 등록된 친구입니다");
					}else {
						//등록된 적이 없다면...
						addFriend() ;//친구로 등록 
						friendPage.showFriendList();
					}
				}
			}
		});
		
	}
	
	public void addFriend() {
		Friend friend = new Friend();
		friend.setMe(ChatMain.member);//ChatMain에서 가져올 예정 나의정보 dto
		friend.setYou(member);//너의정보 dto
		
		int result = chatMain.friendDAO.insert(friend);
		if(result>0) {
			JOptionPane.showMessageDialog(this, member.getName()+" 을 친구로 등록했어요");
		}
	}
	
}

