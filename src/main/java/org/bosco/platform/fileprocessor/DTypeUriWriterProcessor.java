package org.bosco.platform.fileprocessor;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.bosco.lib.io.FileWriter;

public class DTypeUriWriterProcessor extends WriterProcessor{

	HashMap<String, FileWriter> ht = new HashMap<String, FileWriter>();
	
	public DTypeUriWriterProcessor(String strPath, ConcurrentLinkedQueue<Document> writeQueue) {
		super(strPath, writeQueue);
	}

	protected void write(Document doc) {
		String threadName = Thread.currentThread().getName();
		FileWriter fw = ht.get(threadName);
		if (null == fw) {
			fw = new FileWriter(strPath, "DUMP_" + threadName + "_", ".txt", 1024L*1024L*1024L);
			try {
				fw.init();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			ht.put(threadName, fw);
		}
		fw.write(new StringBuffer(doc.getUri()).append("\t").append(doc.getData("Doc-D_TYPE")).append("\n").toString().getBytes());
	}

	@Override
	protected void terminate() {
		String threadName = Thread.currentThread().getName();
		FileWriter fw = ht.get(threadName);	
		if (null != fw) {
			fw.terminate();
		}
	}
}
