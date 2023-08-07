package org.sp.chat.client.view.popup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ChattingRoom extends PopUP {
	JPanel p_north;
	JPanel p_center;
	JLabel la_title;
	JLabel la_make;
	
	public ChattingRoom() {
		p_north = new JPanel();
		p_center = new JPanel();
		la_title = new JLabel("대화 상대 선택");
		la_make = new JLabel("확인");
		
		//스타일
		p_north.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		p_north.setPreferredSize(new Dimension(330, 50));
		
		Dimension d = new Dimension(140, 40);
		la_title.setPreferredSize(d);
		la_make.setPreferredSize(d);
		
		Font f = new Font("휴먼모음T", Font.PLAIN, 20); //폰트
		la_title.setFont(f);
		la_make.setFont(f);
		
		la_title.setHorizontalAlignment(JLabel.LEFT);
		la_make.setHorizontalAlignment(JLabel.RIGHT);
		
		//부착
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		p_north.add(la_title);
		p_north.add(la_make);
		
		setBounds(900, 200, 380, 600);
		
	}
}
