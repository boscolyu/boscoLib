package org.bosco.platform.fileprocessor;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.bosco.nhn.dump.DumpFileReader;

public class ContentToTrecProcessor extends ControlProcessor {
	
	public ContentToTrecProcessor(ConcurrentLinkedQueue<Document> readQueue, ConcurrentLinkedQueue<Document> writeQueue) {
		super(readQueue, writeQueue);
	}
	
	protected Document handle(Document doc) {
		if (null == doc.getData("Doc-CONTENT")) {
			System.out.println("invalid raw data : " + doc.getData("Doc-URI"));
		}
		
		String []contentInfo = DumpFileReader.splitHeaderAndHtml(doc.getData("Doc-CONTENT"));
		if (null == contentInfo || contentInfo.length != 2) {
			System.out.println("invalid raw data : " + doc.getData("Doc-URI"));
			return null;
		}

		StringBuffer sb = new StringBuffer();
		sb.append("<DOC>\n");
		sb.append("<DOCNO>");
		sb.append(doc.getGlobalDocId());
		sb.append("</DOCNO>\n");
		sb.append("<DOCOLDNO>");
		sb.append(doc.getGlobalDocId());
		sb.append("</DOCOLDNO>\n");

		sb.append("<DOCHDR>\n");
		sb.append(doc.getUri() + "\n");
		sb.append(contentInfo[0]);
		sb.append("\n</DOCHDR>\n\n");

		sb.append(contentInfo[1]);
		sb.append("\n</DOC>\n");
		doc.setTrecdata(sb.toString());
		return doc;
	}
}
