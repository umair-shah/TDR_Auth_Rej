package TermDeposit;

public class AccountDTO {
	private boolean result;
	private String accountNo;
	private String accountID;
	private String accountTitle;
	private String branchCode;
	private String branchName;
	private String currency;
	private String branchDate;
	private float balance;
	private String accountStatus;
	
	public void SetResult(boolean result)
	{
		this.result = result;
	}
	public void SetAccountID(String accountID)
	{
		this.accountID = accountID;
	}
	public void SetAccountNo(String accountNo)
	{
		this.accountNo = accountNo;
	}
	
	public void SetAccountTitle(String accountTitle)
	{
		this.accountTitle = accountTitle;
	}
	
	public void SetBranchCode(String branchCode)
	{
		this.branchCode = branchCode;
	}
	
	public void SetBranchName(String branchName)
	{
		this.branchName = branchName;
	}
	
	public void SetCurrency(String currency)
	{
		this.currency = currency;
	}
	
	public void SetBranchDate(String branchDate)
	{
		this.branchDate = branchDate;
	}
	
	public void SetBalance(float balance)
	{
		this.balance = balance;
	}
	
	public void SetAccountStatus(String accountStatus)
	{
		this.accountStatus = accountStatus;
	}
	public String GetAccountID()
	{
		return this.accountID;
	}
	public boolean GetResult()
	{
		return result;
	}
	
	public String GetAccountNo()
	{
		return accountNo;
	}
	
	public String GetAccountTitle()
	{
		return accountTitle;
	}
	
	public String GetBranchCode()
	{
		return branchCode;
	}
	
	public String GetBranchName()
	{
		return branchName;
	}
	
	public String GetCurrency()
	{
		return currency;
	}
	
	public String GetBranchDate()
	{
		return branchDate;
	}
	
	public float GetBalance()
	{
		return balance;
	}
	
	public String GetAccountStatus()
	{
		return accountStatus;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
