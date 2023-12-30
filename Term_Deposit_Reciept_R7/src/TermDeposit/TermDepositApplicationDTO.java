package TermDeposit;

import Utilities.ComboItem;

public class TermDepositApplicationDTO {
	private String applicationID;
	private String accountNo;
	private String applicationDate;
	private ComboItem selectedTenure;
	private ComboItem selectedMOF;
	private ComboItem selectedMaturityAction;
	private String accountTitle;
	private float tdrAmount;
	private String principalFundCrAccount;
	private String profitNomAccount;
	private String fileName;
	private byte[] fileData;
	private String accountID;
	private String tdrAccountID;
	
	private AccountDTO tdrAccount;
	
	public void SetApplicationDate(String applicationDate)
	{
		this.applicationDate = applicationDate;
	}
	public void SetAccountID(String accountID)
	{
		this.accountID = accountID;
	}
	public void SetTDRAccountID(String tdrAccountID)
	{
		this.tdrAccountID = tdrAccountID;
	}
	public void SetSelectedTenure(ComboItem selectedTenure)
	{
		this.selectedTenure = selectedTenure;
	}
	
	public void SetSelectedMOF(ComboItem selectedMOF)
	{
		this.selectedMOF = selectedMOF;
	}
	
	public void SetSelectedMaturityAction(ComboItem selectedMaturityAction)
	{
		this.selectedMaturityAction = selectedMaturityAction;
	}
	
	public void SetAccountTitle(String accountTitle)
	{
		this.accountTitle = accountTitle;
	}
	
	public void SetTDRAmount(float tdrAmount)
	{
		this.tdrAmount = tdrAmount;
	}
	
	public void SetPricipalFundCrAccount(String principalFundCrAccount)
	{
		this.principalFundCrAccount = principalFundCrAccount;
	}
	
	public void SetProfitNomAccount(String profitNomAccount)
	{
		this.profitNomAccount = profitNomAccount;
	}
	
	public void SetFiledata(byte[] fileData, String fileName)
	{
		this.fileData=fileData;
		this.fileName=fileName;
	}
	public void SetAccountNo(String accountNo)
	{
		this.accountNo = accountNo;
	}
	public void SetTdrAccount(AccountDTO accdto)
	{
		this.tdrAccount=accdto;
	}
	public void SetApplicationNo(String applicationNo)
	{
		this.applicationID=applicationNo;
	}
	public String GetApplicationDate()
	{
		return applicationDate;
	}
	
	public ComboItem GetSelectedTenure()
	{
		return selectedTenure;
	}
	
	public ComboItem GetSelectedMOF()
	{
		return selectedMOF;
	}
	
	public ComboItem GetSelectedMaturityAction()
	{
		return selectedMaturityAction;
	}
	public String GetAccountID()
	{
		return this.accountID ;
	}
	public String GetTDRAccountID()
	{
		return this.tdrAccountID ;
	}
	public String GetAccountTitle()
	{
		return accountTitle;
	}
	
	public float GetTDRAmount()
	{
		return tdrAmount;
	}
	
	public String GetPrincipalFundCrAccount()
	{
		return principalFundCrAccount;
	}
	
	public String GetProfitNomAccount()
	{
		return profitNomAccount;
	}
	public byte[] GetFileData()
	{
		return fileData;
	}
	 public String GetFileName()
	 {
		 return fileName;
	 }
	 public String GetAccountNo()
	 {
		 return this.accountNo;
	 }
	 public AccountDTO GetAccountDTO()
	 {
		 return this.tdrAccount;
	 }
	public String GetApplicationNo()
	{
		return this.applicationID;
	}
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
