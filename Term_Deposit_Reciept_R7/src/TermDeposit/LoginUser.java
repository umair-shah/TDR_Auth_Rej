package TermDeposit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import Utilities.MaxLengthNumericField;
import Utilities.MaxLengthPwdField;
import Utilities.MaxLengthTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Font;

public class LoginUser {
	private static MaxLengthNumericField branchCodeField;
	private static MaxLengthTextField userIDField;
	private static MaxLengthPwdField passwordField;
	private static JLabel logoLabel;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	
	public LoginUser()
	{
		createWindow();
	}
	private static void createWindow(){
		final JFrame frame = new JFrame("Login");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,600);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		branchCodeField = new MaxLengthNumericField(4);
		branchCodeField.setToolTipText("");
		branchCodeField.setBounds(458, 223, 86, 20);
		panel.add(branchCodeField);
		branchCodeField.setColumns(10);
		
		userIDField = new MaxLengthTextField(10);
		userIDField.setBounds(565, 223, 166, 20);
		panel.add(userIDField);
		userIDField.setColumns(10);
		
		passwordField = new MaxLengthPwdField(10);
		passwordField.setBounds(458, 278, 273, 20);
		panel.add(passwordField);
		
		logoLabel = new JLabel("");
		ImageIcon logoIcon = new ImageIcon("C:/Users/ushah.27501/IBM/rationalsdp/workspace/Term_Deposite_Reciept/Resources/Logo.png");
		Image scaledLogoImage = logoIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		ImageIcon scaledLogoIcon = new ImageIcon(scaledLogoImage);
		logoLabel.setIcon(scaledLogoIcon);
		logoLabel.setBounds(70, 60, 311, 356);
		panel.add(logoLabel);
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(458, 333, 89, 23);
		panel.add(loginButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.setBounds(642, 333, 89, 23);
		panel.add(exitButton);
		
		JLabel loginTitle = new JLabel("Login");
		loginTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		loginTitle.setBounds(458, 135, 273, 59);
		panel.add(loginTitle);
		frame.setVisible(true);
		
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent Login) {
				String branchCode = branchCodeField.getText().toString();
				String userId = userIDField.getText().toString();
				String password = passwordField.getText().toString();
				if(branchCode.isEmpty())
				{
					JOptionPane.showMessageDialog(frame, "Field branch code is required!","Validation Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(userId.isEmpty())
				{
					JOptionPane.showMessageDialog(frame, "Field user id is required!","Validation Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(password.isEmpty())
				{
					JOptionPane.showMessageDialog(frame, "Field password is required!","Validation Error",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					LoginUserService loginUserService = new LoginUserService();
					LoginUserDTO loginUserDTO = loginUserService.ValidateUser(branchCode, userId, password);
					if (loginUserDTO.GetResult() == true)
					{
						JOptionPane.showMessageDialog(frame, "Login Success! Last login was on "+ loginUserDTO.GetLastSignon(),"Success",JOptionPane.INFORMATION_MESSAGE);
						loginUserService.SetLastSignOn(userId, branchCode);
						frame.dispose();
						MainMenu mm = new MainMenu();
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Invalid User ID or Password","Failure",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
	
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		createWindow();

	}
}
