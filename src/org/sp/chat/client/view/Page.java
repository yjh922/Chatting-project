package org.sp.chat.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import org.sp.chat.client.view.popup.PopUp;

public class Page extends JPanel{
	JPanel p_north;
	JPanel p_center;
	JLabel la_title;
	JLabel la_plus;

	public Page() {
		p_north = new JPanel();
		p_center=new JPanel();
		la_title = new JLabel("");
		la_plus = new JLabel("");
		
		
		//스타일
		p_north.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		p_north.setBackground(Color.yellow);
		//p_north.setPreferredSize(new Dimension(330,50));
		Dimension d = new Dimension(50,40);
		la_title.setPreferredSize(d);
		la_plus.setPreferredSize(d);
		Font f =new Font("휴먼모음T", Font.PLAIN, 20);
		la_title.setFont(f);
		la_plus.setFont(f);
		
		la_title.setHorizontalAlignment(JLabel.LEFT);
		la_plus.setHorizontalAlignment(JLabel.RIGHT);
		
		//조립
		p_north.add(la_title);
		p_north.add(la_plus);
		
		setLayout(new BorderLayout());
		
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		setPreferredSize(new Dimension(330, 600));
		setVisible(true);
		
	}
}
