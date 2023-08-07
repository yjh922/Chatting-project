package org.sp.chat.client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyPage extends Page{
	JLabel la_title;
	JLabel la_logout;
	JButton bt_file;
	JPanel p_preview;
	JFileChooser chooser;
	JButton bt_regist; //수정버튼(등록)
	JPanel p_content;
	
	Image img;

	
	
	public MyPage() {
		la_title =new JLabel("마이페이지");
		la_logout =new JLabel("로그아웃");
		bt_file = new JButton("프로필 편집");
		img = Toolkit.getDefaultToolkit().getImage("default.png");
		p_preview = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, 130,130, p_preview);
			}
		};//추후 그림을 직접 그리겠슴..
		
		chooser = new JFileChooser("D:/morning/html_workspace/images");
		bt_regist  = new JButton("수정");
		p_content = new JPanel();

		
		//스타일
		p_content.setPreferredSize(new Dimension(350, 100));
		p_preview.setPreferredSize(new Dimension(130,130));
		p_preview.setBackground(Color.YELLOW);
		Dimension d = new Dimension(140,40);
		la_title.setPreferredSize(d);
		la_logout.setPreferredSize(d);
		bt_file.setPreferredSize(d);
		
		Font f =new Font("휴먼모음T", Font.PLAIN, 20);
		la_title.setFont(f);
		la_logout.setFont(f);
		bt_file.setFont(f);
		
		la_title.setHorizontalAlignment(JLabel.LEFT);
		la_logout.setHorizontalAlignment(JLabel.RIGHT);
		
		//조립
		p_north.add(la_title);
		p_north.add(la_logout);
		
		p_content.add(bt_file);
		p_content.add(bt_regist);
		
		add(p_content);
		
		//이미지 추가
		JLabel la_image = new JLabel(new ImageIcon(img));
		la_image.setBounds(0,0, img.getWidth(null), img.getHeight(null));
		p_center.add(la_image);
		
		la_logout.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//System.out.println("클릭했다");
			}
		});
		
	}
	
}



