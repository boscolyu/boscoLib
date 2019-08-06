package org.bosco.lib.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

public class WritePerformanceTestForRaidIOBasic {
	
	public final static int MAX_FILE_COUNT = 8;

	public static void main(String args[]) {
		if (args.length != 1) {
			System.err.println("usage : " + WritePerformanceTestForRaidIOBasic.class.getName() + " [output file name]");
			System.exit(-1);
		}
		File []fileList = new File[MAX_FILE_COUNT];
		
		for (int filepos = 0; filepos < MAX_FILE_COUNT; filepos++) {
			String filename = args[0] + new Integer(filepos).toString(); 
			System.out.println("parameter : " + filename);
			fileList[filepos] = new File(filename);
			if (fileList[filepos].exists()) {
				System.err.println("invalid file name");
				System.exit(-2);
			}
			try {
				if (fileList[filepos].createNewFile() == false) {
					System.err.println("fail to create file" + fileList[filepos].getAbsolutePath());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		new WritePerformanceTestForRaidIOBasic().run(fileList);	
	}
	
	public void run(File[] fileList) {
		try {
			FileOutputStream osList[] = new FileOutputStream[MAX_FILE_COUNT];
			FileChannel 	 fcList[] = new FileChannel[MAX_FILE_COUNT];
			for (int filepos = 0; filepos < MAX_FILE_COUNT;filepos++) {
				osList[filepos] = new FileOutputStream(fileList[filepos]);
				fcList[filepos] = osList[filepos].getChannel();
			}
			
			Random rd = new Random();
			int totalCount = 0;
			byte data[] = new byte[1024*1024];
			while (totalCount < 30)
			{
				for (int pos = 0; pos < 1024 * 1024; pos++) {
					int value = rd.nextInt();
					Integer iValue = new Integer(value);
					data[pos] = iValue.byteValue();
				}
				for (int pos1 = 0; pos1 < 1024; pos1++){
					for (int filepos = 0; filepos < MAX_FILE_COUNT;filepos++) {
						ByteBuffer buffer = ByteBuffer.wrap(data);						
						fcList[filepos].write(buffer);
					}
				}
				totalCount ++;
			}
			for (int filepos = 0; filepos < MAX_FILE_COUNT; filepos++){
				fcList[filepos].close();
				osList[filepos].close();
			}

		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}
}

