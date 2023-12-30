package Utilities;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MaxLengthTextField extends JTextField {
	private int maxLength;
	
	public MaxLengthTextField(int maxLength)
	{
		this.maxLength = maxLength;
	}
	
	@Override
	protected Document createDefaultModel()
	{
		return new PlainDocument(){
			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if(str != null && (getLength() + str.length()) <= maxLength) {
					super.insertString(offs, str, a);
				}
			}
		};
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
