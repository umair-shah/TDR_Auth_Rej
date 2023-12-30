package Utilities;

import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;

public class MaxLengthNumericField extends JTextField {
	
	public MaxLengthNumericField(int maxLength)
	{
		setDocument(new NumericDocument(maxLength));
	}
	
	private static class NumericDocument extends PlainDocument{
		private int maxLength;
		
		public NumericDocument(int maxLength)
		{
			this.maxLength = maxLength;
		}
		
		@Override
		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException{
			if(str == null)
			{
				return;
			}
			StringBuilder sb = new StringBuilder();
			for(char c: str.toCharArray())
			{
				if (Character.isDigit(c) && (sb.length()+ getLength()) < maxLength)
				{
					sb.append(c);
				}
			}
			super.insertString(offs, sb.toString(), a);
		}
		
		@Override
		public void replace(int offset, int length, String text, AttributeSet attrs) throws BadLocationException{
			StringBuilder sb = new StringBuilder();
			for(char c: text.toCharArray())
			{
				if(Character.isDigit(c) && (sb.length()+ getLength() - length) < maxLength)
				{
					sb.append(c);
				}
			}
			super.replace(offset, length, sb.toString(), attrs);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
