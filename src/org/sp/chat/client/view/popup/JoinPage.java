package org.sp.chat.client.view.popup;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.sp.chat.client.domain.Member;
import org.sp.chat.client.model.Member2DAO;
import org.sp.chat.client.view.ChatMain;

import util.DBManager;
import util.HashConverter;
import util.MailSender;

public class JoinPage  extends PopupPage{
	ChatMain chatMain;
	JTextField t_id;
	JPasswordField t_pass;
	JTextField t_name;
	JTextField t_nick;
	JTextField t_email;
	JButton bt_join;
	JButton bt_login;
	JLabel la_id;
	JLabel la_pass;
	JLabel la_name;
	JLabel la_nick;
	JLabel la_email;
	
	
	LoginPage loginForm;
	
	Member2DAO member2DAO;
	DBManager dbManager; // DB
	HashConverter hashConverter; // 비번
	MailSender mailSender; // 메일

	public JoinPage(PopWin popWin, ChatMain chatMain) {
		super(popWin);
		this.chatMain = chatMain;
		dbManager = new DBManager();
	
		setPreferredSize(new Dimension(380, 600));
		
		t_id = new JTextField();
		t_pass = new JPasswordField();
		t_name = new JTextField();
		t_nick = new JTextField();
		t_email = new JTextField();
		la_id = new JLabel("아이디");
		la_pass =new JLabel("비밀번호");
		la_name = new JLabel("이름");
		la_nick = new JLabel("닉네임");
		la_email = new JLabel("이메일");
		
		bt_join = new JButton("가입");
		bt_login = new JButton("로그인");
		
		dbManager = new DBManager();
		member2DAO = new Member2DAO(dbManager);
		hashConverter = new HashConverter();
		mailSender = new MailSender();
		
		Dimension d = new Dimension(280, 45);
		Dimension l = new Dimension(70,45);
		Font f= new Font("휴먼모음T", Font.PLAIN, 20); 
		
		la_id.setPreferredSize(l);
		la_pass.setPreferredSize(l);
		la_name.setPreferredSize(l);
		la_nick.setPreferredSize(l);
		la_email.setPreferredSize(l);
		
		la_id.setFont(f);
		la_pass.setFont(f);
		la_name.setFont(f);
		la_nick.setFont(f);
		la_email.setFont(f);
		
		t_id.setPreferredSize(d);
		t_pass.setPreferredSize(d);
		t_name.setPreferredSize(d);
		t_nick.setPreferredSize(d);
		t_email.setPreferredSize(d);
		
		add(la_id);
		add(t_id);
		add(la_pass);
		add(t_pass);
		add(la_name);
		add(t_name);
		add(la_nick);
		add(t_nick);
		add(la_email);
		add(t_email);
		
		add(bt_join);
		add(bt_login);
		

		// 회원가입 들었을때
		bt_join.addActionListener((e) -> {
			regist();
		});
		
		// 로그인 들었을때
		bt_login.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				popWin.showHide(PopWin.LOGINPAGE);
			}
		});
	} 	
	public void regist() {
		// db에 넣기
		// 아이디, 패스워드 등 입력폼의 데이터를 하나의 DTO 담아서 insert 메서드로 전달하자
		Member member = new Member();
		
		// 비어있는 DTO 안에 입력폼의 데이터들을 채워넣기 setter이용
		member.setId(t_id.getText()); // 아이디 채우기
		member.setPass(hashConverter.HashConverter(new String(t_pass.getPassword())));
		member.setName(t_name.getText()); // 이름 채우기
		member.setEmail(t_nick.getText());
		member.setEmail(t_email.getText());
		
		int result = member2DAO.insert(member); // insert 호출
		if(result >= 0) {
			// 이메일발송
			
			boolean flag = mailSender.send(member.getEmail(), "가입축하", member.getName() + " 가입을 진심으로 축하드려요");
			if(flag) {
				JOptionPane.showMessageDialog(this, "가입성공");
		
			}
		} else {
			JOptionPane.showMessageDialog(this, "가입실패");
		}
	}
}
