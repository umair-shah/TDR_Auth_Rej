package Utilities;

public class ComboItem {
	private String value;
	private int id;
	
	public ComboItem(int id,String value)
	{
		this.value=value;
		this.id=id;
	}
	
	@Override 
	public String toString()
	{
		return value;
	}
	public int getId()
	{
		return id;
	}
}
