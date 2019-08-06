package org.bosco.platform.fileprocessor;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.bosco.nhn.dump.DumpFileReader;


public class ReaderProcessor extends Thread {

	BlockingQueue<File> queue = null;
	ConcurrentLinkedQueue<Document> readQueue = null;
	
	
	public ReaderProcessor(BlockingQueue<File> queue, ConcurrentLinkedQueue<Document> readQueue) {
		// TODO Auto-generated constructor stub
		this.queue = queue;
		this.readQueue = readQueue;
	}
	

	public void run() {
		// TODO Auto-generated method stub
		try {
			while (queue.size() > 0) {
				File dumpFile = queue.poll();
				
				if (dumpFile == null)
					break;
				
				if (false == dumpFile.canRead()) {
					System.out.println("ERROR: can't read file : no authority" + dumpFile.getAbsolutePath());
				}
				
				if (true == dumpFile.isDirectory()) {
					System.out.print("file is directory : " + dumpFile.getAbsolutePath());
				}
				readFile(dumpFile);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	protected void readFile(File dumpFile) 
	throws FileNotFoundException, IOException
	{
		FileInputStream in = new FileInputStream(dumpFile);
		BufferedInputStream bis = new BufferedInputStream(in);
	
		while (true)
		{
			byte []raw = DumpFileReader.readDumpFile(bis);
			if (null == raw)
				break;
			
			HashMap<String, String> info = DumpFileReader.parseDumpData(raw);
			if (null == info || info.size() < 1) {
				continue;
			}
			
			Document doc = new Document(info);
			if (null == doc)
				break;
			
			while (readQueue.size() > 200) {
			//while (readQueue.size() > 10) {
				try {
					Thread.currentThread().sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
			readQueue.add(doc);
		}
		
		bis.close();
		in.close();
	}

}
