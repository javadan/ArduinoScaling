package com.studyanything.arduino;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

/**
 * My file format is RGB1RGB2RGB3...RGB1080, reading vertically down, since that
 * is what the arduino design prefers.
 * 
 * RGB123 RGB46/47/48 RGB456 RGB49/50/51 RGB789 RGB52/53/54 RGB101112 . . . . .
 * . .
 * */
public class WriteToMyFileFormat {

	public static void main(String args[]) {
		File f = new File("screenshot_resized.bmp");
		File out = new File("screenshot_resized.dat");

		try {

			BufferedImage image = ImageIO.read(f);
			byte[] array = writeImgToByteArray(image);

			FileOutputStream stream = new FileOutputStream(out);
			try {
				stream.write(array);
			} finally {
				stream.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static byte[] writeImgToByteArray(BufferedImage image)
			throws IOException {

		byte[] array = new byte[1080]; // 24*15*3

		int arrayPlace = 0;

		for (int x = 0; x < 24; x++) {
			for (int y = 0; y < 15; y++) {

				int clr = image.getRGB(x, y);
				
				byte[] bytes = intToByteArray(clr);
						//ByteBuffer.allocate(4).putInt(clr).array();

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


	public static final byte[] intToByteArray(int value) {
//		Color c = new Color(value);
//		int red = c.getRed();
//		int green = c.getGreen();
//		int blue = c.getBlue();
		
		return new byte[] { (byte) (value>>> 16), (byte) (value >>> 8), (byte) value};
		
	}

}
