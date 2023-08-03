package org.sp.chat.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Page extends JPanel{
	JPanel p_north;
	JPanel p_center;

	public Page() {
		p_north = new JPanel();
		p_center=new JPanel();
		
		p_north.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		p_north.setPreferredSize(new Dimension(330,50));
		
		setLayout(new BorderLayout());
		
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		setPreferredSize(new Dimension(330, 600));
		setVisible(true);
	}
}
