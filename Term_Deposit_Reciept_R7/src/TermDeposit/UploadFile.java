package TermDeposit;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import java.sql.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UploadFile {
	public String path;
	public String fileName;
	private final JFrame frame;
	public UploadFile()
	{
		frame = new JFrame();
		fileName="";
	}
	public String selectFile() {
    	
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
//        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "jpeg"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "jpeg"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        int result = fileChooser.showSaveDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            if(selectedFile != null)
            {
	           	 path=selectedFile.getAbsolutePath().toString();
	           	 fileName=selectedFile.getName();
            }

             //return ;
        }
        return fileName;
    }
    public boolean isValidFileType(File file) {
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".pdf") || fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg");
    }
    public void viewFile(byte[] fileData , String fileName)
    {
    	File tempfile;
		try {
			tempfile = File.createTempFile("tempfile","_"+fileName);
			FileOutputStream fos= new FileOutputStream(tempfile);
			fos.write(fileData);
			fos.close();
			
			if(fileName.endsWith(".pdf"))
			{
				if(Desktop.isDesktopSupported())
				{
					Desktop.getDesktop().open(tempfile);
				}
			}
			else {
				InputStream in =new ByteArrayInputStream(fileData);
				BufferedImage image = ImageIO.read(in);
				  int maxWidth = 800; // Maximum width
	                int maxHeight = 600; // Maximum height
	                Image scaledImage = getScaledImage(image, maxWidth, maxHeight);

	                ImageIcon imageIcon = new ImageIcon(scaledImage);
	                JLabel label = new JLabel(imageIcon);
	                JFrame imageFrame = new JFrame();
	                imageFrame.setLayout(new BorderLayout());
	                imageFrame.add(label, BorderLayout.CENTER);
	                imageFrame.pack(); // Adjusts frame size to fit the label
	                imageFrame.setResizable(true); // Allow resizing
	                imageFrame.setVisible(true);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	private Image getScaledImage(Image srcImg, int maxWidth, int maxHeight){
	    int originalWidth = srcImg.getWidth(null);
	    int originalHeight = srcImg.getHeight(null);
	    int newWidth = originalWidth;
	    int newHeight = originalHeight;

	    // Check if scaling is needed
	    if (originalWidth > maxWidth) {
	        newWidth = maxWidth;
	        newHeight = (newWidth * originalHeight) / originalWidth;
	    }
	    if (newHeight > maxHeight) {
	        newHeight = maxHeight;
	        newWidth = (newHeight * originalWidth) / originalHeight;
	    }

	    // Scale the image
	    Image scaledImg = srcImg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
	    return scaledImg;
	}

    public byte[] readFileData(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] fileData = new byte[(int) file.length()];
        fis.read(fileData);
        fis.close();
        return fileData;
    }
}
