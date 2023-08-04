package org.sp.chat.client.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Profile extends JFrame{
	JPanel p_center; 
	JPanel p_south;
	JButton bt_con;
	JButton bt_cancel;
	JButton bt_photo;
	JTextField t_name;
	JButton bt_regist; //등록버튼
	String imgprofile= "res/default.png";
	
	public Profile() {
		p_center = new JPanel();
		p_south = new JPanel();
		bt_con = new JButton("확인");
		bt_regist =new JButton();
		bt_cancel = new JButton("취소");
		bt_photo=new JButton("사진찾기");
		t_name= new JTextField(20);
		
		//p_center.setLayout(null);
		//setLayout(new FlowLayout());
		
		//조립
		imgprofile();
		add(p_south, BorderLayout.SOUTH);
		add(p_center, BorderLayout.CENTER);
		p_south.add(bt_con);
		p_south.add(bt_cancel);
		p_center.add(t_name);
		p_center.add(bt_photo);

		
		p_center.setPreferredSize(new Dimension(50,600));
	
		setSize(300,380);
		setVisible(true);		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		bt_con.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 팝업 창 띄우기
				JOptionPane.showMessageDialog(null, "수정 하시겠습니까?");
			}
		});
		
		bt_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 창 닫기
				dispose();
			}
		});	
		
	}
	
	
	public void imgprofile() {
		
		URL url=ClassLoader.getSystemResource(imgprofile);
		
		try {
			BufferedImage buffImg = ImageIO.read(url);
			Image image=buffImg;
			image=image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			JLabel la_icon = new JLabel(new ImageIcon(image));
			p_center.add(la_icon);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
}


	
}