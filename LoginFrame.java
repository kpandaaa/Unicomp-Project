import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginFrame extends JFrame implements ActionListener {


		Container container = getContentPane();
		JLabel userLabel = new JLabel("USERNAME");
    	JLabel passwordLabel = new JLabel("PASSWORD");
    	JTextField userTextField = new JTextField();
    	JPasswordField passwordField = new JPasswordField();
    	JButton loginButton = new JButton("LOGIN");
    	JButton resetButton = new JButton("RESET");
    	JCheckBox showPassword = new JCheckBox("Show Password");

		LoginFrame() {

        	setLayoutManager();
        	setLocationAndSize();
        	addComponentsToContainer();
        	addActionEvent();

    	}

   		public void setLayoutManager() {

    	    container.setLayout(null);
    	}

    	public void setLocationAndSize() {

        	userLabel.setBounds(35,50,200,25);
    		userLabel.setForeground(Color.WHITE);

        	passwordLabel.setBounds(35,85,200,25);
        	passwordLabel.setForeground(Color.WHITE);

        	userTextField.setBounds(120,50,250,25);

        	passwordField.setBounds(120,85,250,25);

        	showPassword.setBounds(120,135,118,25);
        	showPassword.setBackground(Color.RED);
        	showPassword.setForeground(Color.WHITE);

        	loginButton.setBounds(99,185,90,25);

        	resetButton.setBounds(198,185,90,25);

    	}

    	public void addComponentsToContainer() {

        	container.add(userLabel);
        	container.add(passwordLabel);
        	container.add(userTextField);
        	container.add(passwordField);
        	container.add(showPassword);
        	container.add(loginButton);
        	container.add(resetButton);

    	}

    	public static void main(String[] a) {

        	LoginFrame frame = new LoginFrame();

        	ImageIcon img = new ImageIcon("C://KarishmaJ//UnicompProject//res//IconImage.png");
        	
			frame.setDefaultLookAndFeelDecorated(true);
        	frame.setTitle("USER LOGIN");
        	frame.setLocationRelativeTo(null);
        	frame.setVisible(true);
        	frame.setBounds(50, 50, 425, 300);
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	frame.setResizable(false);
        	frame.getContentPane().setBackground(Color.BLACK);
        	frame.setIconImage(img.getImage());
        }


   		public void addActionEvent() {

        	loginButton.addActionListener(this);
        	resetButton.addActionListener(this);
        	showPassword.addActionListener(this);
    	}


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            if (userText.equalsIgnoreCase("Kiara") && pwdText.equalsIgnoreCase("327")) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                MyNotes mn = new MyNotes();
                mn.main(null);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }

        }
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }


        }
    }

}