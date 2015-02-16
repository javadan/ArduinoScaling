package com.studyanything.arduino;


import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ConvertPOVDatToImg {


	public static void main(String args[]) {
		File f = new File("E:\\POV\\out\\u_21.dat");

		try {

				writeByteArrayToImg(f);
				
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public static void writeByteArrayToImg(File f) throws IOException {
		//1080 bytes
		System.out.println((int)(f.length()/45)); //(3 * 15)
		DataInputStream in = new DataInputStream(new FileInputStream(f));
		
		BufferedImage bufImg = new BufferedImage((int)(f.length()/45), 15, BufferedImage.TYPE_INT_RGB);

//100 times	
			for (int x = 0; x < (int)(f.length()/45); x++) {
				for (int y = 0; y < 15; y++) {
					byte r = in.readByte();
					byte g = in.readByte();
					byte b = in.readByte();
					int rgb =  (r << 16) | (g << 8) | (b << 0);
										
					bufImg.setRGB(x, y, rgb);
				}
			}

			File file = new File("testout.bmp");
			ImageIO.write(bufImg, "bmp", file);

		
		in.close();
		
		
		
	}

}
