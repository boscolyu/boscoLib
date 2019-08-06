package org.bosco.platform.fileprocessor;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

//import com.chutnoon.super100.ds.KeyValues;
//import com.chutnoon.super100.net.InfoPayloadParser;
//import com.chutnoon.super100.net.ContentEscape.UnescapeException;

public class ContentInfoToTrecProcessor extends ControlProcessor {

	public ContentInfoToTrecProcessor(ConcurrentLinkedQueue<Document> readQueue, ConcurrentLinkedQueue<Document> writeQueue) {
		super(readQueue, writeQueue);
	}
	
//	protected Document handle(Document doc) {
//		if (null == doc.getData("Doc-CONTENT_INFO")) {
//			System.out.println("invalid raw data : " + doc.getData("Doc-URI"));
//		}
//		try {
//			List<KeyValues> kvsList = getAnchorTitleFromContentInfo(doc.getData("Doc-CONTENT_INFO").getBytes("utf-8"));
//			
//			if (kvsList == null || kvsList.size() < 1) {
//				return null;
//			}
//			StringBuffer sb = new StringBuffer();
//			sb.append("<DOC>\n");
//			sb.append("<DOCNO>");
//			sb.append(doc.getGlobalDocId());
//			sb.append("</DOCNO>\n");
//			sb.append("<DOCOLDNO>");
//			sb.append(doc.getGlobalDocId());
//			sb.append("</DOCOLDNO>\n");
//	
//			sb.append("<DOCHDR>\n");
//			sb.append(doc.getUri()).append("\n");
//			sb.append("HTTP/1.1 200 OK\n");
//			sb.append("Connection: close\n");
//			sb.append("Content-Type: text/html\n");
//			sb.append("URL: ").append(doc.getUri()).append("\n");
//			sb.append("\n</DOCHDR>\n\n");
//			sb.append("<html>\n");
//			sb.append("<head>\n");
//			sb.append("</head>\n");
//			sb.append("<body>\n\n");
//			
//			for (int i = 0; i < kvsList.size(); i++) {
//				KeyValues kv = kvsList.get(i);
//				sb.append("<a href=\"").append(kv.values[0]).append("\">").append(kv.keyName).append("</a>\n");
//				
//			}
//	
//			sb.append("\n</body>\n");
//			sb.append("</html>\n");
//			sb.append("\n</DOC>\n");
//			doc.setTrecdata(sb.toString());
//		} catch (UnescapeException e) {
//			e.printStackTrace();
//			return null;
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//		return doc;
//	}
//	
	
//	public List<KeyValues> getAnchorTitleFromContentInfo(byte[] raw) 
//	throws UnescapeException 
//	{
//		List<KeyValues> orgKvs = InfoPayloadParser.parsePayload(raw, "utf-8");
//		
//		List<KeyValues> newKvs = new ArrayList<KeyValues>();
//		String lastAnchor = null;
//		String lastAnchorText = null;
//		
//		boolean findFirstAnchor = false;
//		
//		for (KeyValues kv : orgKvs) {
//			
//			if ("Anchor".equals(kv.keyName)) {
//				if(findFirstAnchor == true) { // �댁쟾��anchor瑜�泥섎━�섍린 �꾪븳 �⑸룄
//					
//					KeyValues tempKv = createNewKeyValues(lastAnchor, lastAnchorText);
//					if (tempKv != null)
//						newKvs.add(tempKv);
//					
//					lastAnchor = null;
//					lastAnchorText = null;
//					findFirstAnchor = false;
//				}
//				
//				if ("Anchor".equals(kv.keyName) && kv.values.length > 0 && kv.values[0] != null && kv.values[0].length() > 0) {
//					lastAnchor = kv.values[0];
//					findFirstAnchor = true;
//				}
//			} else if ("AnchorX".equals(kv.keyName)) {
//				if (!"~".equals(kv.values[0])) {
//					lastAnchor = kv.values[0];
//				}
//			}					
//			
//			if (findFirstAnchor == true && "Text".equals(kv.keyName)) {
//				lastAnchorText = kv.values[0];
//			}
//		}
//		return newKvs;
//	}

//	private KeyValues createNewKeyValues(String lastAnchor, String lastAnchorTitle) {
//		if (lastAnchor != null && lastAnchorTitle != null) {
//			return new KeyValues(lastAnchorTitle, lastAnchor);
//		}		
//		return null;
//	}
}
