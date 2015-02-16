package com.studyanything.arduino;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ChangeNumFramesPerFile {

	public static void main(String args[]) {

		try {

			for (int i = 0; i < 670; i += 5) {
				String g = "E:\\BEACONVIDEO\\u_" + (i/5) + ".dat";

				FileOutputStream fos = new FileOutputStream(new File(g));

				for (int combine = 0; combine < 5; combine++) {
					String f = "E:\\BEACONVIDEO\\t_" + (i + combine) + ".dat";
					File file = new File(f);

					byte[] fileData = new byte[(int) file.length()];
					DataInputStream dis = new DataInputStream(
							new FileInputStream(file));
					dis.readFully(fileData);
					dis.close();

					fos.write(fileData);
				}

				fos.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
