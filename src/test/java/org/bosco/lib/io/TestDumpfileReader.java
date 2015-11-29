package org.bosco.lib.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

public class TestDumpfileReader {

	public static void main(String args[]) {
		
		File dumpFile  = new File("E:/TEMP/titleextraction/INPUT/h0_s000.txt");
		FileInputStream in;
		try {
			in = new FileInputStream(dumpFile);
			int limit = 1024;
			int readCount = 0;
			ByteBuffer byteBuffer = ByteBuffer.allocate(limit);
			while(true) {
				int buf;
				buf = in.read();
				if (buf == -1) {
					break;
				}

				if (buf == '\0') {
					System.out.println(byteBuffer.toString());
					System.out.println();
					byte []temp = byteBuffer.array();
					byte []raw = new byte[readCount];
					for (int i = 0 ; i < raw.length;i++) {
						raw[i] = temp[i];
					}
					System.out.println(new String(raw));
				}
				if (byteBuffer.hasRemaining() == false) {
					limit = limit + 1024;
					int pos = byteBuffer.position();
					ByteBuffer newByteBuffer = ByteBuffer.allocate(limit);
					newByteBuffer.position(pos);
					newByteBuffer.put(byteBuffer);
					byteBuffer = newByteBuffer;	
					System.out.println("position" + byteBuffer.position());
				}

				byteBuffer.put((byte)buf);
				readCount ++;
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
