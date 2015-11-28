package org.bosco.lib.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileWriter {

	private long maxFileSize = 10L*1024L*1024L*1024L;
	
	private String filePath;
	private String prefix;
	private String ext;
	private File outFile = null;
	private OutputStream out = null;
	private BufferedOutputStream outBuff = null;

	private enum WritingDestination {
		FILE, DIR
	}
	
	private WritingDestination mode = WritingDestination.FILE;
	
	private int fileNumber = 0;
	private long totalLength = 0;
	
	
	public FileWriter(String filePath) {
		this.filePath = filePath;	
		this.mode = WritingDestination.FILE;
	}
	
	public FileWriter(String dirPath, String prefix, String ext, long maxFileSize) {
		this.filePath = dirPath;
		this.prefix = prefix;
		this.ext = ext;
		this.mode = WritingDestination.DIR;
		this.maxFileSize = maxFileSize;
		this.fileNumber = 0;
	}
	
	public static boolean checkDirectory(String filePath) {
		File file = new File(filePath);
		if (false == file.exists())
			return false;
		
		if (false == file.isDirectory()) 
			return false;
		
		if (false == file.canRead())
			return false;
		
		if (false == file.canWrite())
			return false;
		return true;
	}
	
	public void init() 
	throws FileNotFoundException
	{
		if (checkDirectory(filePath) && mode == WritingDestination.DIR) {
			outBuff = getNewFile();
		}
		else {
			outFile = new File(filePath);
			out = new FileOutputStream(outFile);
			outBuff = new BufferedOutputStream(out);			
		}
	}
	
	public BufferedOutputStream getNewFile() {
		File file = new File(filePath);
		String fileName = "";
		File newFile = null;

		fileName = file.getAbsolutePath() + File.separator + prefix + fileNumber + ext;
		newFile = new File(fileName);

		FileOutputStream out;
		try {
			out = new FileOutputStream(newFile);
			BufferedOutputStream newOutBuff = new BufferedOutputStream(out);
			fileNumber++;
			totalLength = 0;
			return newOutBuff;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void write(byte[] dataToWrite) {
		try {
			if( dataToWrite.length == 0 ) {
				return;
			}
			
			totalLength += (long)dataToWrite.length;
			if (mode == WritingDestination.DIR && totalLength > maxFileSize) {
				outBuff.flush();
				outBuff.close();
				outBuff = getNewFile();
			}
			outBuff.write(dataToWrite);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void terminate() {
		
		try {
			if (outBuff != null)
				outBuff.flush();
				outBuff.close();
			if (out != null)
				out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}