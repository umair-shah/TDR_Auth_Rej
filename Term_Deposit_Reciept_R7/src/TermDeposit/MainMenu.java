package TermDeposit;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import java.awt.Dimension;

public class MainMenu {
//	public MainMenu() {
//		
//	}
	
	public MainMenu()
	{
		createWindow();
	}
	
	public void createWindow(){
		final JFrame frame = new JFrame("Bank Al Habib");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		final JButton termDepositButton = new JButton("Term Deposit");
		termDepositButton.setForeground(new Color(255, 255, 255));
		termDepositButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		termDepositButton.setBackground(new Color(0, 128, 0));
		termDepositButton.setBounds(285, 255, 213, 50);
		panel.add(termDepositButton);
		
		JPopupMenu termDepositPopup = new JPopupMenu();
		addPopup(termDepositButton, termDepositPopup);
		termDepositPopup.setForeground(new Color(255, 255, 255));
		termDepositPopup.setBackground(new Color(0, 128, 0));
		
		
		JButton newApplicationButton = new JButton("New Application");
		newApplicationButton.setVisible(false);
		newApplicationButton.setMaximumSize(new Dimension(200, 23));
		newApplicationButton.setPreferredSize(new Dimension(200, 23));
		newApplicationButton.setMinimumSize(new Dimension(200, 23));
		newApplicationButton.setForeground(new Color(255, 255, 255));
		newApplicationButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		newApplicationButton.setBackground(new Color(0, 128, 0));
		newApplicationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SearchAccount sa = new SearchAccount();
			}
		});
		termDepositPopup.add(newApplicationButton);
		
		JButton modifyApplicationButton = new JButton("Modify Application");
		modifyApplicationButton.setVisible(false);
		modifyApplicationButton.setMaximumSize(new Dimension(200, 23));
		modifyApplicationButton.setPreferredSize(new Dimension(200, 23));
		modifyApplicationButton.setMinimumSize(new Dimension(200, 23));
		modifyApplicationButton.setForeground(new Color(255, 255, 255));
		modifyApplicationButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		modifyApplicationButton.setBackground(new Color(0, 128, 0));
		modifyApplicationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TermDepositSearch tds = new TermDepositSearch(1);
				
			}
		});
		termDepositPopup.add(modifyApplicationButton);
		
		JButton tdrOpenning = new JButton("TDR Opening");
		tdrOpenning.setVisible(false);
		tdrOpenning.setMaximumSize(new Dimension(200, 23));
		tdrOpenning.setPreferredSize(new Dimension(200, 23));
		tdrOpenning.setMinimumSize(new Dimension(200, 23));
		tdrOpenning.setForeground(new Color(255, 255, 255));
		tdrOpenning.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tdrOpenning.setBackground(new Color(0, 128, 0));
		tdrOpenning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TermDepositSearch tds = new TermDepositSearch(2);
				
			}
		});
		termDepositPopup.add(tdrOpenning);

		JButton tdrPreMatureEncashment = new JButton("TDR Pre-mature encashment");
		tdrPreMatureEncashment.setVisible(false);
		tdrPreMatureEncashment.setMaximumSize(new Dimension(200, 23));
		tdrPreMatureEncashment.setPreferredSize(new Dimension(200, 23));
		tdrPreMatureEncashment.setMinimumSize(new Dimension(200, 23));
		tdrPreMatureEncashment.setForeground(new Color(255, 255, 255));
		tdrPreMatureEncashment.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tdrPreMatureEncashment.setBackground(new Color(0, 128, 0));
		tdrPreMatureEncashment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TermDepositSearch tds = new TermDepositSearch(2);
				
			}
		});
		termDepositPopup.add(tdrPreMatureEncashment);
		JButton authorizeApplicationButton = new JButton("Authorize Application");
		authorizeApplicationButton.setVisible(false);
		authorizeApplicationButton.setForeground(Color.WHITE);
		authorizeApplicationButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		authorizeApplicationButton.setBackground(new Color(0, 128, 0));
		authorizeApplicationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			TermDepositSearch tds = new TermDepositSearch(0);
		}
	});
		termDepositPopup.add(authorizeApplicationButton);
		
		JButton signoutButton = new JButton("Sign Out");
		signoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Session.clearSession();
				frame.dispose();
				LoginUser lu = new LoginUser();
			}
		});
		signoutButton.setForeground(new Color(255, 255, 255));
		signoutButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		signoutButton.setBackground(new Color(0, 128, 0));
		signoutButton.setBounds(599, 483, 117, 32);
		panel.add(signoutButton);
		
		if(Session.getUserRoleId() == 1)
		{
			newApplicationButton.setVisible(true);
			modifyApplicationButton.setVisible(true);
			tdrOpenning.setVisible(true);
			tdrPreMatureEncashment.setVisible(true);
		}
		if(Session.getUserRoleId() == 2)
		{
			authorizeApplicationButton.setVisible(true);
		}
		
		panel.repaint();
		
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainMenu td = new MainMenu();
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
//			public void mousePressed(MouseEvent e) {
//				if (e.isPopupTrigger()) {
//					showMenu(e);
//				}
//			}
//			public void mouseReleased(MouseEvent e) {
//				if (e.isPopupTrigger()) {
//					showMenu(e);
//				}
//			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(MouseEvent.BUTTON1 == arg0.getButton())
				{
					showMenu(arg0);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
