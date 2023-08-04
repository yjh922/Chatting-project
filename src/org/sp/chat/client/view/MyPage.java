package org.sp.chat.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class MyPage extends Page{
	JLabel la_title;
	JLabel la_logout;
	JButton bt_file;
	
	String imgprofile= "res/default.png";
	JTextField t_name;
	JFileChooser chooser;
	JPanel p_south;
	
	public MyPage() {
		
		p_south=new JPanel();
		la_title =new JLabel("마이페이지");
		la_logout =new JLabel("로그아웃");
		
		bt_file = new JButton("프로필 편집");
		t_name = new JTextField("닉네임이름");
		
		chooser = new JFileChooser("D:/morning/html_workspace/images");

		
		//스타일
		Dimension d = new Dimension(140,40);
		la_title.setPreferredSize(d);
		la_logout.setPreferredSize(d);
		bt_file.setPreferredSize(new Dimension(20,60));
		

		Font f =new Font("휴먼모음T", Font.PLAIN, 20);
		la_title.setFont(f);
		la_logout.setFont(f);
		bt_file.setFont(f);

		
		la_title.setHorizontalAlignment(JLabel.LEFT);
		la_logout.setHorizontalAlignment(JLabel.RIGHT);
		
		//조립		
		p_north.add(la_title);
		p_north.add(la_logout);
		
		p_center.setLayout(new BorderLayout());
		
		p_center.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		imgprofile();
		p_center.add(p_south, BorderLayout.CENTER);
		p_south.setPreferredSize(new Dimension(300,20));
		p_center.add(bt_file, BorderLayout.SOUTH);
		
		
		la_logout.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "로그아웃 하시겠습니까?");
			}
		});
		
		bt_file.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 프로필 편집 버튼 클릭 시 profile 연결
				Profile profile = new Profile();
				profile.setVisible(true);
			
			}
		});
		
	}
	
	public void imgprofile() {
		
			URL url=ClassLoader.getSystemResource(imgprofile);
			
			try {
				BufferedImage buffImg = ImageIO.read(url);
				Image image=buffImg;
				image=image.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
				JLabel la_icon = new JLabel(new ImageIcon(image));
				p_south.add(la_icon);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
}

