package TermDeposit;

public class LoginUserDTO {
	private boolean result;
	private String lastSignon;
	
	public void SetResult(boolean result)
	{
		this.result = result;
	}
	
	public void SetLastSignOn(String lastSignOn)
	{
		this.lastSignon = lastSignOn;
	}
	
	public boolean GetResult()
	{
		return result;
	}
	
	public String GetLastSignon()
	{
		return lastSignon;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
