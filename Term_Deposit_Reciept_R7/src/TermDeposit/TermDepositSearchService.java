package TermDeposit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utilities.ComboItem;
import Utilities.utility;

public class TermDepositSearchService {

	public Object[][] GetUnauthorizedApplication(String appStatus) throws SQLException
	{
//		List<Object[]> result = new ArrayList<>();
		Connection lcl_conn_dt = utility.db_conn();
		ResultSet TDRapplications=null;
		
		String tdrAppQuery = "Select B.brn_cd||lpad(At.acc_type_cd,4,'0')||C.Customer_no ||lpad(acc.run_no,2,'0') || acc.Check_digit  As AccountNo ,tdr.amount,lpad(tdr.application_id,5,'0')||'/'||C.Customer_No||'/'||Year(tdr.application_date)  As ApplicationNo ,P.Desc  As Tenure ,M.Desc  As ActionatMaturity ,tdr.maturity_date from TDR_Application tdr inner join Account_tl acc on tdr.account_id= acc.account_id inner join Account_Type At on acc.Acc_type_id=At.Acc_type_id inner join Branch_tl B on acc.brn_ID = B.brn_ID inner join Customer C on C.Customer_ID= acc.Customer_ID inner join TDR_product P on tdr.Product_ID = P.ID inner join Maturity_action M on m.ID=tdr.maturity_action "+ 
		"where B.brn_cd='"+Session.GetBranchCode()+"' and tdr.application_date = '"+Session.GetBranchDate()+"'";
		if(appStatus =="Update")
		{
			tdrAppQuery += "and tdr.TDR_APP_STATUS in (1,2)";
		}
		else if(appStatus =="Open")
		{
			tdrAppQuery += "and tdr.TDR_APP_STATUS = 1";
		}
		else if(appStatus =="Authorize")
		{
			tdrAppQuery += "and tdr.TDR_APP_STATUS = 2";
		}
		java.sql.Statement lcl_stmt;
		try {
			 lcl_stmt= lcl_conn_dt.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			 TDRapplications = lcl_stmt.executeQuery(tdrAppQuery);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		TDRapplications.last();
         int rowCount = TDRapplications.getRow();
         TDRapplications.beforeFirst();

         // Assuming two columns: name and age
         int columnCount = 6;

         // Declare and initialize a 2D array
         Object[][] data = new Object[rowCount][columnCount];

         // Populate the array with data from the ResultSet
         int rowIndex = 0;
         while (TDRapplications.next()) {   
        	 data[rowIndex][0] = TDRapplications.getString("AccountNo");
        	 data[rowIndex][1] = TDRapplications.getFloat("Amount");
             data[rowIndex][2] = TDRapplications.getString("ApplicationNo");
             data[rowIndex][3] = TDRapplications.getString("Tenure");
             data[rowIndex][4] = TDRapplications.getString("ActionatMaturity");
             data[rowIndex][5] = TDRapplications.getString("Maturity_date");          
             rowIndex++;
         }
		return data;
	}
	public TermDepositApplicationDTO GetTDRAppDetails(String TDRNo)
	{
		Connection lcl_conn_dt = utility.db_conn();
		ResultSet TDRAppDetail=null;
		
		String tdrAppQuery = "SELECT B.brn_cd||lpad(At.acc_type_cd,4,'0')||C.Customer_no||lpad(acc.run_no,2,'0') || acc.Check_digit As Account_No,acc.account_id,acc.title,B.Brn_cd,B.Brn_Name,Curr.Currency_name,acc.balance,tdr.application_date,tdr.Amount,tdr.TDR_Request_Doc,tdr.TDR_Request_Doc_Name,tdr.mode_of_fund,tdr.maturity_action,tdr.product_id from TDR_Application tdr INNER JOIN Account_tl acc ON tdr.account_id=acc.account_id INNER JOIN Branch_tl B ON B.brn_ID=acc.brn_Id INNER JOIN Customer C ON acc.Customer_id=C.Customer_id INNER JOIN account_type At ON At.acc_type_id=acc.acc_type_id INNER JOIN Currency curr ON acc.curr_cd_id = curr.ID where tdr.application_id= '"+Integer.parseInt(TDRNo.substring(0,5))+"'";

		java.sql.Statement lcl_stmt;
		try {
			//lcl_stmt= lcl_conn_dt.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			 lcl_stmt= lcl_conn_dt.createStatement();
			 TDRAppDetail = lcl_stmt.executeQuery(tdrAppQuery);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		AccountDTO tdrFundAcc= new AccountDTO();
        TermDepositApplicationDTO tdrAppDto= new TermDepositApplicationDTO();
        try{
         if(TDRAppDetail.next()) {   
        	 tdrFundAcc.SetAccountNo(TDRAppDetail.getString("Account_no"));
        	 tdrFundAcc.SetAccountTitle(TDRAppDetail.getString("title"));
        	 tdrFundAcc.SetBranchCode(TDRAppDetail.getString("Brn_CD"));
        	 tdrFundAcc.SetBranchName(TDRAppDetail.getString("Brn_name"));
        	 tdrFundAcc.SetCurrency(TDRAppDetail.getString("Currency_name"));
        	 tdrFundAcc.SetBalance(TDRAppDetail.getFloat("Balance"));
        	 tdrFundAcc.SetAccountID(TDRAppDetail.getString("account_id"));
        	 
        	 tdrAppDto.SetAccountID(TDRAppDetail.getString("account_id"));
        	 tdrAppDto.SetAccountNo(TDRAppDetail.getString("Account_no"));
        	 tdrAppDto.SetApplicationNo(TDRNo);
        	 tdrAppDto.SetAccountTitle(TDRAppDetail.getString("title"));
        	 tdrAppDto.SetApplicationDate (TDRAppDetail.getString("Application_date"));
        	 tdrAppDto.SetAccountTitle(TDRAppDetail.getString("title"));
        	 tdrAppDto.SetTDRAmount(TDRAppDetail.getFloat("Amount"));
        	 tdrAppDto.SetFiledata(TDRAppDetail.getBytes("TDR_Request_Doc"),TDRAppDetail.getString("TDR_Request_Doc_Name") );
        	 tdrAppDto.SetPricipalFundCrAccount(TDRAppDetail.getString("Account_No"));
        	 tdrAppDto.SetProfitNomAccount(TDRAppDetail.getString("Account_No"));
        	 tdrAppDto.SetSelectedMaturityAction(new ComboItem(TDRAppDetail.getInt("Maturity_Action"),"test"));
        	 tdrAppDto.SetSelectedMOF(new ComboItem(TDRAppDetail.getInt("Mode_of_Fund"),"test"));
        	 tdrAppDto.SetSelectedTenure(new ComboItem(TDRAppDetail.getInt("product_id"),"test"));
        	 tdrAppDto.SetTDRAccountID(GetTDRAccount(TDRAppDetail.getString("Account_no")));
        	 tdrAppDto.SetTdrAccount(tdrFundAcc);
         }
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
		return tdrAppDto;
	} 
	public String GetTDRAccount(String accountno)
	{
		String tdrAccQuery="Select account_id from account_tl acc inner join customer c on acc.customer_id=c.customer_id inner join account_type at on at.acc_type_id=acc.acc_type_id where c.customer_no= ? and at.desc like '%TDR Account%' ";
		Connection lcl_conn_dt = utility.db_conn();
		try {
			PreparedStatement preparedStatement = lcl_conn_dt.prepareStatement(tdrAccQuery);
			preparedStatement.setLong(1,Long.parseLong(accountno.substring(8, 14)));
			ResultSet tdrAccId=preparedStatement.executeQuery();
			while(tdrAccId.next())
			{
				return tdrAccId.getString(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}

