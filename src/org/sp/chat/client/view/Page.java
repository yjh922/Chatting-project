package org.sp.chat.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class Page extends JPanel{
	JPanel p_north;
	JPanel p_center;

	public Page() {
		p_north = new JPanel();
		p_center=new JPanel();
		
		//스타일
		p_north.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		p_north.setPreferredSize(new Dimension(330,50));		
		
		//조립	
		setLayout(new BorderLayout());
		
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		setPreferredSize(new Dimension(330, 600));
		setVisible(true);
		
		
	}
}
