package org.sp.chat.client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.sp.chat.client.domain.Member;

import util.ImageUtil;

public class MemberCell extends JPanel{
	ChatMain chatMain;
	JLabel la_icon;
	JLabel la_name;
	Member member;
	
	public MemberCell(ChatMain chatMain, Member member){
		this.chatMain=chatMain;
		la_icon = new JLabel(new ImageIcon(ImageUtil.getImage(member.getImg(), 40, 40)));
		la_icon.setPreferredSize(new Dimension(20, 20));
		
		la_name =new JLabel(member.getName());
		
		setLayout(null);
		add(la_icon);
		add(la_name);
		la_icon.setBounds(10, 12, 40, 40);
		la_name.setBounds(60, 12, 200, 40);
		
		setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		setPreferredSize(new Dimension(300, 60));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("클릭했어?");
			}
			
		});
	}

}
