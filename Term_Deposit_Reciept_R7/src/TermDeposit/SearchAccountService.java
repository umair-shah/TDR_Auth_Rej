package TermDeposit;
import Utilities.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class SearchAccountService {
	
	public AccountDTO ValidateAccount(String accountNo)
	{
		String brnCd=accountNo.substring(0,4);
		String accType=accountNo.substring(4,8);
		String customerNo=accountNo.substring(8,14);
		String runNo=accountNo.substring(14,16);
		String chkDigit=accountNo.substring(16,17);
		AccountDTO accountDTO = new AccountDTO();
		//SELECT A.Account_No, A.Title, A.Brn_Cd, B.BRN_NAME, B.TODAY_DATE, C.CURRENCY_NAME FROM BRANCH_TL B INNER JOIN ACCOUNT_TL A ON A.Brn_Cd = B.Brn_Cd INNER JOIN Currency C ON A.Curr_Cd_Id = C.Curr_Code WHERE A.Account_No = '123456789';
		String accountSearchQuery = "Select A.Account_ID,A.Title,B.Brn_cd,B.BRN_Name,B.today_date,C.Currency_Name,A.Balance,S.DESC,A.Account_ID from Account_tl A inner join Branch_tl B on A.brn_ID= B.brn_ID inner join Customer Cus on A.Customer_ID= Cus.Customer_ID inner join Currency C on A.Curr_CD_ID= C.ID Inner Join Account_Status S on S.ID=A.Status_ID inner join Account_type At on A.Acc_type_ID = At.Acc_type_ID " +
				" WHERE B.Brn_cd = '"+ Session.GetBranchCode() +"' and lpad(At.Acc_Type_CD,4,'0')= '"+accType+"' and lpad(Cus.Customer_No,6,'0')='"+customerNo+"' and A.Check_Digit='"+chkDigit+"' and lpad(A.run_no,2,'0')='"+runNo+"' -- and B.Brn_cd='"+Session.GetBranchCode()+"'";
		Connection lcl_conn_dt = utility.db_conn();
		ResultSet accountRs =null;
		java.sql.Statement lcl_stmt;
		try {
			 lcl_stmt= lcl_conn_dt.createStatement();
			 accountRs = lcl_stmt.executeQuery(accountSearchQuery);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		try 
		{
			if(accountRs.next())
			{
					accountDTO.SetResult(true);
					accountDTO.SetAccountID(accountRs.getString("Account_ID"));
					accountDTO.SetAccountNo(accountNo);
					accountDTO.SetAccountTitle(accountRs.getString("TITLE"));
					accountDTO.SetBranchCode(accountRs.getString("Brn_cd"));
					accountDTO.SetBranchName(accountRs.getString("Brn_Name"));
					accountDTO.SetBranchDate(accountRs.getString("Today_Date"));
					accountDTO.SetCurrency(accountRs.getString("Currency_Name"));
					accountDTO.SetBalance(accountRs.getFloat("Balance"));
					accountDTO.SetAccountStatus(accountRs.getString("Desc"));
			}	
			else
			{
				accountDTO.SetResult(false);
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accountDTO;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
