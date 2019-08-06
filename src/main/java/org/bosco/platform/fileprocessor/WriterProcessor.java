package org.bosco.platform.fileprocessor;

import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class WriterProcessor extends Thread {

	ConcurrentLinkedQueue<Document> writeQueue = null;
	
	boolean bHandleCompleted = false;
	
	String strPath = "";
	
	public WriterProcessor(String strPath, ConcurrentLinkedQueue<Document> writeQueue) {
		this.strPath = strPath;
		this.writeQueue = writeQueue;
	}

	
	public boolean isbHandleCompleted() {
		return bHandleCompleted;
	}

	public void setbHandleCompleted(boolean bHandleCompleted) {
		this.bHandleCompleted = bHandleCompleted;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true) {
			Document doc = writeQueue.poll();
			if (doc == null && isbHandleCompleted() == true) {
				break;
			}
			else if (doc == null){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				continue;
			}
			write(doc);
		}
		
	}


	abstract void write(Document doc);
	
	abstract protected void terminate();

}
