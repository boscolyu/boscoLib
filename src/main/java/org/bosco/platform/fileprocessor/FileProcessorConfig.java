package org.bosco.platform.fileprocessor;

public class FileProcessorConfig {
	String sourcePath = "";
	String outputPath = "";
	int READER_THREAD_COUNT = 1;
	int MATCHER_THREAD_COUNT = 1;
	int WRITER_THREAD_COUNT = 1;
	
	String READER_PROCESSOR = "";
	String WRITER_PROCESSOR = "";
	String HANDLE_PROCESSOR = "";
	
	String DOCUMENT_CLASS = "";
	
	public FileProcessorConfig() {
		
	}
	
	public String getREADER_PROCESSOR() {
		return READER_PROCESSOR;
	}

	public void setREADER_PROCESSOR(String rEADER_PROCESSOR) {
		READER_PROCESSOR = rEADER_PROCESSOR;
	}

	public String getWRITER_PROCESSOR() {
		return WRITER_PROCESSOR;
	}

	public void setWRITER_PROCESSOR(String wRITER_PROCESSOR) {
		WRITER_PROCESSOR = wRITER_PROCESSOR;
	}

	public String getHANDLE_PROCESSOR() {
		return HANDLE_PROCESSOR;
	}

	public void setHANDLE_PROCESSOR(String hANDLE_PROCESSOR) {
		HANDLE_PROCESSOR = hANDLE_PROCESSOR;
	}
	
	
	public int getMathcerThreadCount() {
		return this.MATCHER_THREAD_COUNT;
	}

	public int getWriterThreadCount() {
		return this.WRITER_THREAD_COUNT;
	}

	public int getReaderThreadCount() {
		return this.READER_THREAD_COUNT;
	}

	public String getOutputDumpFilePath() {
		return this.outputPath;
	}

	public String getSourceDumpFilePath() {
		return this.sourcePath;
	}

	public void setReaderThreadCount(String property) {
		READER_THREAD_COUNT = new Integer(property).intValue();
	}

	public void setWriterThreadCount(String property) {
		WRITER_THREAD_COUNT = new Integer(property).intValue();
		
	}

	public void setMatcherThreadCount(String property) {
		MATCHER_THREAD_COUNT = new Integer(property).intValue();
		
	}

	public void setOutputDumpFilePath(String property) {
		this.outputPath = property;
	}

	public void setSourceDumpFilePath(String property) {
		this.sourcePath = property;
	}

	public void setDocumentClassName(String DOCUMENT_CLASS) {
		this.DOCUMENT_CLASS = DOCUMENT_CLASS;
	}
	
	public String getDocumentClassName() {
		// TODO Auto-generated method stub
		return DOCUMENT_CLASS;
	}
}
