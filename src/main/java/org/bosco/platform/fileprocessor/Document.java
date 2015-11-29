package org.bosco.platform.fileprocessor;

import java.util.HashMap;

public class Document {
	
	private HashMap<String, String> metaDocument = new HashMap<String, String>();


	public Document(HashMap<String, String> data) {
		this.metaDocument = data;
	}
	
	public String setData(String key, String value) {
		return this.metaDocument.put(key, value);
		
	}
	public String getData(String key) {
		return this.metaDocument.get(key);
		
	}
	public Document(String uri, String globalDocId, String reverseDomain, String encoding, String content, String contentInfo) {
		metaDocument.put("Doc-URI", uri);
		metaDocument.put("Doc-GLOBAL_DOC_ID", globalDocId);
		metaDocument.put("Doc-RVRSD_DOMAIN", reverseDomain);
		metaDocument.put("Doc-ENCODING", encoding);
		metaDocument.put("Doc-CONTENT", content);
		metaDocument.put("Doc-CONTENT_INFO", contentInfo);
	}

	public String getUri() {
		return getData("Doc-URI");
	}

	public void setUri(String uri) {
		setData("Doc-URI", uri);
	}

	public String getGlobalDocId() {
		return getData("Doc-GLOBAL_DOC_ID");
	}

	public void setGlobalDocId(String globalDocId) {
		setData("Doc-GLOBAL_DOC_ID", globalDocId);
	}

	public String getContent() {
		return getData("Doc-CONTENT");
	}

	public void setHtml(String content) {
		setData("Doc-CONTENT", content);
	}

	public String getReverseDomain() {
		return getData("Doc-RVRSD_DOMAIN");
	}

	public void setReverseDomain(String reverseDomain) {
		setData("Doc-RVRSD_DOMAIN", reverseDomain);
	}

	public void setTrecdata(String trecdata) {
		setData("Doc-TREC_FORMAT", trecdata);
	}

	public String getTrecdata() {
		return getData("Doc-TREC_FORMAT");
	}
	

}
