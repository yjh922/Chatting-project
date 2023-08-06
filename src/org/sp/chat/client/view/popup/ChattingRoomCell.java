package org.sp.chat.client.view.popup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.sp.chat.client.domain.Friend;
import org.sp.chat.client.domain.Member;

import util.ImageUtil;

public class ChattingRoomCell extends JPanel{
	
	JLabel la_icon;
	JLabel la_name;
	JCheckBox box;
	Member friend;
	
	
	public ChattingRoomCell(Member friend) {
		this.friend=friend;
				
		//System.out.println("이미지는"+friend.getImg());
		la_icon = new JLabel(new ImageIcon(ImageUtil.getImage(friend.getImg(), 50, 50)));
		la_icon.setPreferredSize(new Dimension(50, 50));
		
		System.out.println("친구 이름은 "+friend.getName());
		
		la_name = new JLabel(friend.getName());
		
		box = new JCheckBox();
		
		
		setLayout(null);
		add(la_icon);
		add(la_name);
		add(box);
		la_icon.setBounds(10,10,50,50);
		la_name.setBounds(80,5,100,50);		
		box.setBounds(300, 10, 40, 40);
		
		setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		setPreferredSize(new Dimension(350, 60));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}
		});
	
	
	}
}
