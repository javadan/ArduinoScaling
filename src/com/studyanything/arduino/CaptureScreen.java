package com.studyanything.arduino;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CaptureScreen {

	public static void main(String[] args) {

		try {

			Robot robot = new Robot();

			// Capture a particular area on the screen
//			int x = 50;
//			int y = 50;
//			int width = 240;//24;//change to size
//			int height = 180;//15;//change to size
			int x = 360;
			int y = 159;
			int width = 640;
			int height = 470;
			Rectangle area = new Rectangle(x, y, width, height);
			BufferedImage bufferedImage = robot.createScreenCapture(area);
			// Write generated image to a file

			try {
				// Save as PNG
				File file = new File("screenshot_small.bmp");
				ImageIO.write(bufferedImage, "bmp", file);
			} catch (IOException e) {

				System.out.println("Could not save small screenshot "+ e.getMessage());
			}

//			// Capture the whole screen
//
//			area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
//
//			bufferedImage = robot.createScreenCapture(area);
//
//			// Write generated image to a file
//
//			try {
//
//				// Save as PNG
//
//				File file = new File("screenshot_full.png");
//
//				ImageIO.write(bufferedImage, "png", file);
//
//			} catch (IOException e) {
//
//				System.out.println("Could not save full screenshot "
//						+ e.getMessage());
//
//			}

		} catch (AWTException e) {

			System.out.println("Could not capture screen " + e.getMessage());

		}

	}
}