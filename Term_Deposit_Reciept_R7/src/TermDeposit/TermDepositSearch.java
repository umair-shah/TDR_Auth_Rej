package TermDeposit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TermDepositSearch {
	private JTable table;
	TermDepositSearchService TDRSS =null;
	public TermDepositSearch(int operation)
	{
		TDRSS= new TermDepositSearchService();
		try {
			CreateWindow(operation);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void CreateWindow(final int operation) throws SQLException 
	{
		final JFrame frame = new JFrame("Term Deposit");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(834,362);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		String[] columnNames = {"Account No", "Amount", "Application No",  "Tenure", "Action at Maturity","Maturity_date"};
		
		Object[][] data=null;
		if(Session.getUserRoleId()==1 && operation == 1)
		{
			 data = TDRSS.GetUnauthorizedApplication("Update");
		}
		else if(Session.getUserRoleId()==1 && operation ==2)
		{
			data = TDRSS.GetUnauthorizedApplication("Open");
		}
		else if (Session.getUserRoleId()==2 )
		{
			data = TDRSS.GetUnauthorizedApplication("Authorize");
		}
		

//		Object[][] dataArray = new Object[data.size()][];
//        data.toArray(dataArray);
		DefaultTableModel model = new DefaultTableModel(data, columnNames){
			 public boolean isCellEditable(int row, int column)
			 {
			     return false;
			 }
			};

		JTable termDepositTable = new JTable(model);
		JScrollPane jScrollPane = new JScrollPane(termDepositTable);
		jScrollPane.setForeground(Color.BLACK);
		jScrollPane.setLocation(31, 44);
		//termDepositTable.setBounds(142, 196, 202, -91);
		panel.add(jScrollPane);
		jScrollPane.setSize(771,173);
		
		termDepositTable.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					//JOptionPane.showMessageDialog(frame, "Amount is required!","row = "+table.getValueAt(row, 0).toString() ,JOptionPane.ERROR_MESSAGE);
		        	String applicationNo= table.getValueAt(row, 2).toString();
		        	TermDepositApplicationDTO TDRAppDto = TDRSS.GetTDRAppDetails(applicationNo);
		        	
		        	TermDepositApplication TDRApplication= new TermDepositApplication();
		        	if(Session.getUserRoleId()==1 && operation==2 )
		        	{
		        		TDRApplication.OpenTDRApplication(TDRAppDto);
		        	}
		        	else if(Session.getUserRoleId()==1 && operation==1)
		        	{
		        		TDRApplication.UpdateTermDepositApplication(TDRAppDto);
		        	}
		        	else if(Session.getUserRoleId()==2 ) {
		        		
		        		TDRApplication.AuthorizeTDRApplication(TDRAppDto);
		        	}
		        	frame.dispose();
		        }
		    }
		});
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnBack.setBounds(690, 268, 89, 23);
		panel.add(btnBack);
		jScrollPane.setVisible(true);
		
		
		panel.repaint();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TermDepositSearch tds = new TermDepositSearch(0);

	}
}
