package org.bosco.platform.fileprocessor;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ControlProcessor extends Thread  {

	boolean bReadCompleted = false;
	
	ConcurrentLinkedQueue<Document> readQueue = null;
	ConcurrentLinkedQueue<Document> writeQueue = null;

	
	public boolean isbReadCompleted() {
		return bReadCompleted;
	}
	public void setbReadCompleted(boolean bReadCompleted) {
		this.bReadCompleted = bReadCompleted;
	}
	
	public ControlProcessor(ConcurrentLinkedQueue<Document> readQueue, ConcurrentLinkedQueue<Document> writeQueue) {
		this.readQueue = readQueue;
		this.writeQueue = writeQueue;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			Document doc = readQueue.poll();
			if (doc == null && bReadCompleted == true)
				break;
			else if (doc == null){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				continue;
			}
			
			doc = handle(doc);
			if (doc == null) {
				continue;
			}
				
			while (writeQueue.size() > 200) {
			//while (readQueue.size() > 10) {
				try {
					Thread.currentThread().sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
			writeQueue.add(doc);
		}
	}
	protected Document handle(Document doc) {
		return doc;

	}

}
