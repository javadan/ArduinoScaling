package com.studyanything.arduino;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class POVPictureToMyFileFormat {

	
	public static final String OUTPUTFOLDER = "E:\\POV\\out\\";
	
	
	public static void convert(File folder)
	{
		File[] listOfFiles = folder.listFiles();

	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) {
	    	  try {
				BufferedImage img = ImageIO.read(listOfFiles[i]);

				

				FileOutputStream stream = null;
				try {
					stream = new FileOutputStream(new File(OUTPUTFOLDER + "u_" + i + ".dat"));
					

					byte[] array = writePOVImgToByteArray(img);
					
					stream.write(array);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (stream != null)
					{
						try {
							stream.close();
						} catch (IOException e) {
							e.printStackTrace();
							System.exit(0);
						}
					}
				}
				
				
				
				
	    	  
	    	  } catch (IOException e) {
				e.printStackTrace();
	    	  }
	    	  
	      }
	    }
	    
	}


	public static byte[] writePOVImgToByteArray(BufferedImage image)
			throws IOException {

		int w = image.getWidth();
		int h = image.getHeight();
		byte[] array = new byte[w*h*3]; 

		int arrayPlace = 0;

		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {

				int clr = image.getRGB(x, y);
				
				byte[] bytes = WriteToMyFileFormat.intToByteArray(clr);

				try {
					for (byte b : bytes) {
						array[arrayPlace++] = b;
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

		return array;

	}

	
	
	
	
	
	public static void main(String[] args) {
		convert(new File("E:\\POV\\pics"));
		
		
	
	}
}