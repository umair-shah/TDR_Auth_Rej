package TermDeposit;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

import Utilities.MaxLengthNumericField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchAccount {
	private MaxLengthNumericField accountNoField;
	
	public SearchAccount()
	{
		CreateWindow();
	}
	
	public void CreateWindow()
	{
		final JFrame frame = new JFrame("Search Account");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(368,161);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		accountNoField = new MaxLengthNumericField(17);
		accountNoField.setBounds(120, 30, 197, 20);
		panel.add(accountNoField);
		accountNoField.setColumns(10);
		
		JLabel lblAccountNo = new JLabel("Account No:");
		lblAccountNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAccountNo.setBounds(31, 31, 100, 14);
		panel.add(lblAccountNo);
		
		JButton searchButton = new JButton("Search");
		searchButton.setBounds(28, 76, 89, 23);
		panel.add(searchButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.setBounds(228, 76, 89, 23);
		panel.add(exitButton);
		panel.repaint();
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent searchButtonClicked) {
				String accountNo = accountNoField.getText().toString();
				if(accountNo.isEmpty())
				{
					JOptionPane.showMessageDialog(frame, "Field account No is required!","Validation Error",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					SearchAccountService searchAccountService = new SearchAccountService();
					AccountDTO accountDTO = searchAccountService.ValidateAccount(accountNo);
					if(accountDTO.GetResult() == false)
					{
						JOptionPane.showMessageDialog(frame, "Account Not Exist","Invalid Account No",JOptionPane.ERROR_MESSAGE);
					}
					else if(!(accountDTO.GetAccountStatus().equals("Active")))
					{
						JOptionPane.showMessageDialog(frame, "Account is " + accountDTO.GetAccountStatus() + "!","Account Not Active",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						frame.dispose();
						TermDepositApplication tdr = new TermDepositApplication();
						tdr.NewTermDepositApplication(accountDTO);
					}
				}
			}
		});
		
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exitButtonPressed) {
				frame.dispose();
			}
		});
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		SearchAccount sa = new SearchAccount();
		// TODO Auto-generated method stub

	}
}
