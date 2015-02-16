package com.studyanything.arduino;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ConvertDatBackToBmp {


        static AppProperties properties = new AppProperties();
        
	public static void main(String args[]) {
                String dataDir = properties.getOutputDataDir();

		try {

			for (int i = 0; i < 50; i++)
			{
				String name = dataDir + "s_" + (100+i) + ".dat";
				File newFile = new File(name);
				writeByteArrayToImg(newFile);
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public static void writeByteArrayToImg(File f) throws IOException {
		//1080 bytes
		
		DataInputStream in = new DataInputStream(new FileInputStream(f));
		
		BufferedImage bufImg = new BufferedImage(24, 15, BufferedImage.TYPE_INT_RGB);

//100 times	
		for (int out = 0; out < 100; out++) {
			for (int x = 0; x < 24; x++) {
				for (int y = 0; y < 15; y++) {
					byte r = in.readByte();
					byte g = in.readByte();
					byte b = in.readByte();
					int rgb =  (r << 16) | (g << 8) | (b << 0);
										
					bufImg.setRGB(x, y, rgb);
				}
			}

			File file = new File("testout" + out + ".bmp");
			ImageIO.write(bufImg, "bmp", file);
		}
		
		in.close();
		
		
		
	}

}
