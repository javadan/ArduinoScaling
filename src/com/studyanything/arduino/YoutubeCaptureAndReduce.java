package com.studyanything.arduino;

import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class YoutubeCaptureAndReduce {

	static int bufferSize = 1000;
	static byte[] growingBuffer = new byte[bufferSize * 1080];
	static int growingBufferLocation = 0;
	
        static AppProperties properties = new AppProperties();
                

	
	public static int highestNumber = -1;
	
	public static int getLastVideoNumberInDir(File folder)
	{
            if (folder.listFiles() == null)
                return 0;
            
		File[] listOfFiles = folder.listFiles();

                
	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile() && listOfFiles[i].getName().startsWith("u_")) {
	    
                System.out.println("Looking for last file: " + listOfFiles[i].getName());
                  
                  String name = listOfFiles[i].getName();
	    	  String num = name.substring(2, name.length() - 4);
	    	  Integer intVal = Integer.parseInt(num);
	    	  if (intVal > highestNumber)
	    	  {
	    		  highestNumber = intVal;
	    	  }
	      }
	    }
	    
	    highestNumber++;
            
            System.out.println("Highest number found: " + highestNumber);
	    return highestNumber + 1;
	}

	public static void main(String[] args) {

            
            bufferSize = properties.getBufferSize();
            System.out.println("Number of frames per file: " + bufferSize);
            
            String OUTPUTBMPFOLDER = properties.getOutputBmpDir();
            String OUTPUTFOLDER = properties.getOutputDataDir();
            System.out.println("Output DAT folder: " + OUTPUTFOLDER);
            System.out.println("Output BMP folder: " + OUTPUTBMPFOLDER);
           
		
		try {
			
            File bmpfolder = new File(OUTPUTBMPFOLDER);
            bmpfolder.mkdirs();
            File folder = new File(OUTPUTFOLDER);
            folder.mkdirs();
            
            
		getLastVideoNumberInDir(folder);
			
			
			robot = new Robot();

			for (int i = highestNumber; i < highestNumber + 1000; i++) {  //take 20 videos

				for (int bufNdx = 0; bufNdx < bufferSize; bufNdx++) //
				{
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					takeBmp(bufNdx, OUTPUTBMPFOLDER); // adds to growing buffer
					growingBufferLocation += 1080; //size of pic
				}

				FileOutputStream stream = null;
				try {
                                        File newFile = new File(OUTPUTFOLDER + "u_" + i + ".dat");
					stream = new FileOutputStream(newFile);
					stream.write(growingBuffer);
                                        System.out.println("Wrote to Output folder: " + newFile.getAbsolutePath());
                                        
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
						
						//reinit values
						growingBuffer = new byte[bufferSize * 1080];
						growingBufferLocation = 0;
					}
				}
				

			}
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	static Robot robot;

	public static void takeBmp(int index, String outputFolder) {

		// Capture a particular area on the screen
		int x = properties.getX();
		int y = properties.getY();
		int width = properties.getW();
		int height = properties.getH();

		int scaledWidth = properties.getScaledW();
		int scaledHeight = properties.getScaledH();

		Rectangle area = new Rectangle(x, y, width, height);
		BufferedImage bufferedImage = robot.createScreenCapture(area);
		// Write generated image to a file

		try {
			// scaledWidth and scaledHeight are the new smaller image size
			Image scaledImg = bufferedImage.getScaledInstance(scaledWidth,
					scaledHeight, BufferedImage.SCALE_AREA_AVERAGING);

			BufferedImage scaledBufferedImg = new BufferedImage(scaledWidth,
					scaledHeight, BufferedImage.TYPE_INT_RGB);
			Graphics g = scaledBufferedImg.getGraphics();
			g.drawImage(scaledImg, 0, 0, null);
			g.dispose();

			 File file = new File(outputFolder + "s_" + index++ + ".bmp");
			 ImageIO.write(scaledBufferedImg, "bmp", file);

			byte[] array = WriteToMyFileFormat
					.writeImgToByteArray(scaledBufferedImg);
			
			for (int i = 0; i < scaledWidth*scaledHeight; i++)
			{
				growingBuffer[growingBufferLocation + i] = array[i];
			}
			
		} catch (IOException e) {

			System.out.println("Could not save small screenshot "
					+ e.getMessage());
		}


	}
}