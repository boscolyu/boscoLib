package org.bosco.lib.io;

import java.io.FileNotFoundException;

import org.bosco.lib.io.FileWriter;

public class TestFileWriter {

	public static void main(String []args) {
		FileWriter fw = new FileWriter("E:\\TEMP", "test", ".log", 10L*1024L*1024L*1024L);
		try {
			fw.init();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		fw.write("test".getBytes());
		fw.terminate();
	}
}
