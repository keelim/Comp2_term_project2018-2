package MainTest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class LoginGui extends JFrame implements ActionListener {
	private JTextField loginField;
	private JPasswordField passwordField;
	private User user = User.getInstance();
	private Socket socket;
	private OutputStream alive;
    private PrintWriter alive_writer;

	public void checkServer(){
		JOptionPane.showMessageDialog(null, "서버 확인 중 입니다. ");
		try {
			socket = new Socket("127.0.0.1", 1807); // 서버 확인 포트
			JOptionPane.showMessageDialog(null, "서버가 확인 되었습니다. ");
            alive = socket.getOutputStream();
            alive_writer = new PrintWriter(alive, true);
            new Thread(() ->{
                alive_writer.println(9999);
            }).start();


		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "서버의 문제가 있습니다. ");
			JOptionPane.showMessageDialog(null, "클라이언트를 종료 합니다. ");
			System.exit(1);
		}
	}

//	서버 체크하기
	public LoginGui() throws IOException {

		setTitle("로그인 화면");
		setSize(450, 200);
		getContentPane().setLayout(null);

		loginField = new JTextField();
		loginField.setBounds(188, 53, 116, 21);
		getContentPane().add(loginField);
		loginField.setColumns(10);
		passwordField = new JPasswordField();
		passwordField.setBounds(188, 84, 116, 21);
		passwordField.addActionListener(this);
		getContentPane().add(passwordField);

		JLabel labelID = new JLabel("ID");
		labelID.setBounds(68, 56, 57, 15);
		getContentPane().add(labelID);

		JLabel labelPassword = new JLabel("Password");
		labelPassword.setBounds(68, 87, 57, 15);
		getContentPane().add(labelPassword);

		JButton SigninButton = new JButton("Sign in");
		SigninButton.setBounds(316, 53, 85, 21);
		SigninButton.addActionListener(this);
		getContentPane().add(SigninButton);
		
		JButton Signupbutton = new JButton("Sign up");
		Signupbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MemberGui();
			}
		});
		Signupbutton.setBounds(316, 83, 85, 21);
		getContentPane().add(Signupbutton);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String id = loginField.getText();
		char[] pass = passwordField.getPassword();
		String password = new String(pass);

		if (id.equals("") || password.equals("")) {
			JOptionPane.showMessageDialog(null, "비어있습니다.");
			
		} else {
			
			boolean existLogin = Login.loginTest(id, password);
			if (existLogin) {
				// 로그인 성공일 경우
				user.setID(id);
				user.setGameMoney(Login.getMoney(id));
				JOptionPane.showMessageDialog(null, "로그인 성공");
				System.out.println(id);
				if(id.equals("GM")){
					setVisible(false);
					new Member_List();
				} else{
					setVisible(false);
					new MainGui();
					new Staus();
				}
			} else {
				// 로그인 실패일 경우
				JOptionPane.showMessageDialog(null, "로그인 실패");
				loginField.setText(""); // 로그인 실패시 --> 모든 칸 빈칸으로
				passwordField.setText("");
			}
		}
	}

	public static void main(String[] args) throws IOException {
		LoginGui m = new LoginGui();
	}

}
