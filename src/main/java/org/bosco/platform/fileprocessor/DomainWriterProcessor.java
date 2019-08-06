package org.bosco.platform.fileprocessor;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.bosco.lib.io.FileWriter;

public class DomainWriterProcessor extends WriterProcessor {

	HashMap<String, FileWriter> ht = new HashMap<String, FileWriter>();
	
	public DomainWriterProcessor(String strPath,ConcurrentLinkedQueue<Document> writeQueue) {
		super(strPath, writeQueue);
	}
	
	protected void write(Document doc) {
		String threadName = Thread.currentThread().getName();
		FileWriter fw = ht.get(doc.getReverseDomain() + threadName);
		if (null == fw) {
			fw = new FileWriter(strPath, "TITLE_DUMP_" + doc.getReverseDomain() + "_" + threadName + "_", ".DATA", 1024L*1024L*1024L);
			try {
				fw.init();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			ht.put(doc.getReverseDomain() + threadName, fw);
		}
		fw.write(doc.getTrecdata().getBytes());
	}

	@Override
	protected void terminate() {
	
	}

}
