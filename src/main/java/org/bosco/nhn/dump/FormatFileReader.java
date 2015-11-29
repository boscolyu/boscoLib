package org.bosco.nhn.dump;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FormatFileReader {
	
	private File formatFile = null;
	private boolean endRead = false;
	
	FileReader FR = null;
	BufferedReader BR = null;
	private String delimeter;
	private String fieldDelimeter;
	boolean isFirst = true;
	
	public FormatFileReader() {
		
	}
	
	public FormatFileReader(File dumpFile, String delimeter) 
	throws FileNotFoundException 
	{
		this.formatFile = dumpFile;
		this.delimeter = delimeter;
		FR = new FileReader(formatFile); 
		BR = new BufferedReader(FR);
	}

	public FormatFileReader(File dumpFile, String delimeter, String fieldDelimeter) 
	throws FileNotFoundException 
	{
		this(dumpFile, delimeter);
		this.fieldDelimeter = fieldDelimeter;
		
	}


	public boolean hasNext() {
		return endRead?false:true;
	}
	
	public HashMap<String, String> nextDocument() 
	throws IOException 
	{

		HashMap<String, String> hashData = new HashMap<String, String>();
		String tmp = "";
		while ((tmp = BR.readLine()) != null) {
			int pos =  tmp.indexOf(fieldDelimeter); 
			if (pos == -1 || tmp.startsWith("http://")) {
				hashData.put("URI", tmp);
			}
			else {
				String key = tmp.substring(0, pos);
				String value = tmp.substring(pos+1);
				if (key.equals("LINKS") == true) {
					hashData.put(key, value);
					return hashData;
				}
			}
		}
		endRead = true;
		return null;
	}
	
	public void close() 
	throws IOException 
	{
		BR.close();
		FR.close();
	}
	

}
