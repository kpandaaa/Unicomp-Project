import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.*;

public class About extends JFrame
{

	public static void main(String[] args)
	{
		About abo = new About();

		abo.setTitle("About");

		ImageIcon img = new ImageIcon("C://KarishmaJ//UnicompProject//res//IconImage.png");

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.BLACK);

		JLabel label = new JLabel("<html><br/>MyNotes<br/><br/>jdk-8u162-windows-x64<br/>Karishma J..All rights reserved<br/>1st September 2018<html>");
		label.setBackground(Color.WHITE);

		panel.add(label,BorderLayout.CENTER);

		abo.setIconImage(img.getImage());

		abo.add(panel);

        abo.setSize(255, 190);

        abo.setLocationRelativeTo(null);

        abo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        abo.setVisible(true);

	}

}