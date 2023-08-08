package org.sp.chat.client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.sp.chat.client.domain.Friend;
import org.sp.chat.client.domain.Member;
import org.sp.chat.network.ClientMain;

import util.ImageUtil;

public class FriendCell extends JPanel{
	JLabel la_icon;
	JLabel la_name;
	Friend friend;
	ClientMain clientMain;
	
	public FriendCell(Member friend) {
		//System.out.println("이미지는"+friend.getImg());
		la_icon = new JLabel(new ImageIcon(ImageUtil.getImage(friend.getImg(), 50, 50)));
		la_icon.setPreferredSize(new Dimension(50, 50));
		
		//System.out.println("친구 이름은 "+friend.getName());
		la_name = new JLabel(friend.getName());
		
		la_icon.setBounds(10,5,50,50);
		la_name.setBounds(75,5,100,50);		
		
		setLayout(null);
		//부착
		add(la_icon);
		add(la_name);
	
		//setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		setPreferredSize(new Dimension(290, 60));
		//setBackground(Color.white);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					System.out.println("친구 이름은 "+friend.getName()+friend.getMember_idx());
					
				}
			}
		});
	}
}
