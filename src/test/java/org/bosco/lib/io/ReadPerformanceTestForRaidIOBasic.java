package org.bosco.lib.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadPerformanceTestForRaidIOBasic {
	
	public final static int MAX_FILE_COUNT = 8;
	
	public static void main(String args[]) {
		if (args.length != 1) {
			System.err.println("usage : " + ReadPerformanceTestForRaidIOBasic.class.getName() + " [output file name]");
			System.exit(-1);
		}
		
		File []fileList = new File[MAX_FILE_COUNT];
		
		for (int filepos = 0; filepos < MAX_FILE_COUNT; filepos++) {
			String filename = args[0] + new Integer(filepos).toString(); 
			System.out.println("parameter : " + filename);
			fileList[filepos] = new File(filename);
			if (fileList[filepos].exists() == false || fileList[filepos].canRead() == false || fileList[filepos].isDirectory()) {
				System.err.println("invalid file name");
				System.exit(-2);
			}
		}

		new ReadPerformanceTestForRaidIOBasic().run(fileList);	
	}
	
	public void run(File[] fileList) {
		
		try {
			FileInputStream isList[] = new FileInputStream[MAX_FILE_COUNT];
			FileChannel 	 fcList[] = new FileChannel[MAX_FILE_COUNT];
			for (int filepos = 0; filepos < MAX_FILE_COUNT;filepos++) {
				isList[filepos] = new FileInputStream(fileList[filepos]);
				fcList[filepos] = isList[filepos].getChannel();
			}
			
			int totalCount = 0;
			byte data[] = new byte[1024*1024];
			while (totalCount < 30)
			{
				for (int pos1 = 0; pos1 < 1024; pos1++){
					for (int filepos = 0; filepos < MAX_FILE_COUNT;filepos++) {
						ByteBuffer buffer = ByteBuffer.wrap(data);						
						int ret = fcList[filepos].read(buffer);
						if (ret == -1) {
							break;
						}
					}
				}
				totalCount ++;
				System.out.println(totalCount);
			}
			for (int filepos = 0; filepos < MAX_FILE_COUNT; filepos++){
				fcList[filepos].close();
				isList[filepos].close();
			}

		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		
	}
}
