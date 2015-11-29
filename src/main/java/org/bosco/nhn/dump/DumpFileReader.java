package org.bosco.nhn.dump;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bosco.platform.fileprocessor.Document;


public class DumpFileReader {
	public static final String CONTENT			= "Doc-CONTENT";
	public static final String CONTENT_INFO		= "Doc-CONTENT_INFO";
	public static final String URI				= "Doc-URI";
	public static final String GLOBAL_DOC_ID	= "Doc-GLOBAL_DOC_ID";
	public static final String RVRSD_DOMAIN		= "Doc-RVRSD_DOMAIN";
	public static final String ENCODING			= "Doc-ENCODING";
	
	public static final char 	DUMP_FILE_DOC_SEPARATOR		= '\0';
	public static final String	DUMP_FILE_FIELD_SEPARATOR	= ": ";

	private File dumpFile = null;
	private FileInputStream FIS = null;
	private boolean endPos = false;
	
	public DumpFileReader() {
		
	}
	
	public DumpFileReader(File dumpFile) {
		this.dumpFile = dumpFile;
	}
	
	public boolean hasNext() {
		return endPos;
	}
	
	public Document nextDocument() {
		byte []raw = readDumpFile();
		if (null == raw)
			return null;
		
		HashMap<String, String> info = parseDumpData(raw);
		if (null == info || info.size() < 1) {
			return null;
		}
		
		String []contentInfo = DumpFileReader.splitHeaderAndHtml(info.get(CONTENT));
		Document doc = new Document(info.get(URI), info.get(GLOBAL_DOC_ID), info.get(RVRSD_DOMAIN), info.get(ENCODING), contentInfo[0], contentInfo[1]);
		return doc;
	}

	public boolean init() {
		try {
			FIS = new FileInputStream(dumpFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public byte[] readDumpFile() {
		int limit = 1024;
		int readCount = 0;
		ByteBuffer byteBuffer = ByteBuffer.allocate(limit);
		while(true) {
			try {
				int buf = FIS.read();

				if (buf == -1) {
					endPos = true;
					break;
				}
	
				if (buf == DUMP_FILE_DOC_SEPARATOR) {
					byte []temp = byteBuffer.array();
					byte []raw = new byte[readCount];
					for (int i = 0 ; i < raw.length;i++) {
						raw[i] = temp[i];
					}
					return raw;
				}
				if (byteBuffer.hasRemaining() == false) {
					limit = limit + 1024;
					int pos = byteBuffer.position();
					ByteBuffer newByteBuffer = ByteBuffer.allocate(limit);
					for (int i = 0; i < pos; i ++) {
						newByteBuffer.put(byteBuffer.get(i));
					}
					byteBuffer = newByteBuffer;	
				}
	
				byteBuffer.put((byte)buf);
				readCount ++;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;

	}
	
	/**
	 *  
	 * @param raw
	 * @return
	 */
	public static byte[] readDumpFile(BufferedInputStream bis) {
		int limit = 1024;
		int readCount = 0;
		ByteBuffer byteBuffer = ByteBuffer.allocate(limit);
		
		while(true) {
			try {
				int buf = bis.read();

				if (buf == -1) {
					break;
				}
	
				if (buf == DUMP_FILE_DOC_SEPARATOR) {
					byte []temp = byteBuffer.array();
					byte []raw = new byte[readCount];
					System.arraycopy(temp, 0, raw, 0, raw.length);
					return raw;
				}
				if (byteBuffer.hasRemaining() == false) {
					limit = limit + 1024;
					byte []before = byteBuffer.array();
					byte []after = new byte[limit];
					System.arraycopy(before, 0, after, 0, before.length);
					ByteBuffer newByteBuffer = ByteBuffer.wrap(after);
					int position = byteBuffer.position();
					byteBuffer = newByteBuffer;
					byteBuffer.position(position);
				}
	
				byteBuffer.put((byte)buf);
				readCount ++;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;

	}

	
	/**
	 * 
	 * 
	 * @param raw
	 * @return
	 */
	
	public static HashMap<String, String> parseDumpData(byte[] raw) {
		HashMap<String, String> documentMap = new HashMap<String, String>();
		int pos = 0;
		int length = 0;
		String encoding = "";
		boolean seenCR = false;

		for (int i = 0; i < raw.length; i++) {
			int c = raw[i];
			if (c == '\r') {
				seenCR = true;
			}
			if (c == '\n') {
				String record = null;
				if (seenCR) {
					record = new String(raw, pos, length - 1);
				}
				else {
					record = new String(raw, pos, length);
				}
				seenCR = false;
				if (null != record || record.trim().length() > 1) {
					String []recordList = record.split(DUMP_FILE_FIELD_SEPARATOR);
					if (recordList.length == 2 && null != recordList[1] && recordList[1].trim().length()  > 0 ) {
						documentMap.put(recordList[0],recordList[1].trim());
						if (recordList[0].equals("Doc-ENCODING")) {
							encoding = recordList[1].trim();
						}
						if (recordList[0].equals(CONTENT)) {
							int contentSize = new Integer(recordList[1].trim()).intValue();
							String rawInfo;
							try {
								rawInfo = new String(raw, i + 1, contentSize, encoding);
							} catch (UnsupportedEncodingException e) {
								return null;
							}
							documentMap.put(recordList[0], rawInfo);
							i = i + contentSize;
						}
						else if(recordList[0].equals(CONTENT_INFO)) {
							int contentInfoSize = new Integer(recordList[1].trim()).intValue();
							String contentInfo;
							try {
								contentInfo = new String(raw, i + 1, contentInfoSize, "utf-8");
							} catch (UnsupportedEncodingException e) {
								return null;
							}
							documentMap.put(recordList[0], contentInfo);
							break;
						}
					}
				}
				pos = i + 1;
				length = 0;
			} else {
				length++;
			}
		}
		return documentMap;
	}
	
	
	public static String[] splitHeaderAndHtml(String raw) {
        if (null == raw)
            return null;
		String separator = System.getProperty("line.separator");
		int pos = raw.indexOf(separator+separator);
		String header = raw.substring(0,pos);
		String html = raw.substring(pos + separator.length() * 2);
		String []rawInfo = new String[2];
		rawInfo[0] = header;
		rawInfo[1] = html;
		return rawInfo;
	}
}
