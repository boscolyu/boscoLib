package org.bosco.platform.fileprocessor.trec;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.bosco.nhn.dump.FormatFileReader;

public class CalculateAnchorTextCount {

	HashMap<Integer, Integer> linkCountMap = new HashMap<Integer, Integer>();
	HashMap<Integer, String> linkSampleMap = new HashMap<Integer, String>();
	
	public void process(String sourcePath) {
		File file = new File(sourcePath);
		BlockingQueue<File> queue = new LinkedBlockingQueue<File>();
		
		if (file.isDirectory() == false) {
			queue.add(file);
		}
		else {
			File []dirlist = file.listFiles();
			for (File urlFile:dirlist) {
				queue.add(urlFile);
			}
		}
		
		while (queue.size() > 0) {
			File data = queue.poll();
			try {
				FormatFileReader ffr = new FormatFileReader(data, "DOCNO=", "=");
				while(ffr.hasNext() == true) {
					try {
						HashMap<String, String> rawData = ffr.nextDocument();
						if (rawData == null) {
							continue;
						}
							
						String url  = rawData.get("URI");
						String linkcount = rawData.get("LINKS");
						if (linkcount != null && linkcount.trim().length() > 0 ) {
							if (linkCountMap.containsKey(new Integer(linkcount)) == true) {
								Integer currentLinkCount = linkCountMap.get(new Integer(linkcount));
								linkCountMap.put(new Integer(linkcount), currentLinkCount + 1);
							}
							else {
								linkCountMap.put(new Integer(linkcount), new Integer(1));
								linkSampleMap.put(new Integer(linkcount), url);
							}
						}
						System.out.println(linkcount + "\t" + url);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		Set<Entry<Integer, Integer>> dataSet = linkCountMap.entrySet();
		for (Entry<Integer, Integer> data : dataSet) {
			System.out.println("linkCount=> " + data.getKey() + ", document size=> " + data.getValue() + ", sample=> " + linkSampleMap.get(data.getKey())) ;
		}
	}
		
	public static void main(String args[]) {
		String sourcePath = args[0] ;
		CalculateAnchorTextCount calcuate = new CalculateAnchorTextCount();
		calcuate.process(sourcePath);


		
	}

}
