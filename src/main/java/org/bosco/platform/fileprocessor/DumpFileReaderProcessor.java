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

public class DumpFileReaderProcessor extends ReaderProcessor {

	public DumpFileReaderProcessor(BlockingQueue<File> queue,
			ConcurrentLinkedQueue<Document> readQueue) {
		super(queue, readQueue);
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
		System.out.println("end to read file : " + dumpFile.toString());
	}
}
