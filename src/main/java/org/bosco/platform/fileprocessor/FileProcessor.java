package org.bosco.platform.fileprocessor;

import gnu.getopt.Getopt;
import gnu.getopt.LongOpt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class FileProcessor {
	
	static String VERSION = "2011-06-28";
	
	static int READER_THREAD_COUNT = 1;
	static int MATCHER_THREAD_COUNT = 1;
	static int WRITER_THREAD_COUNT = 1;
	
	ReaderProcessor []readTaskList = null;
	ControlProcessor []matchTaskList = null;
	WriterProcessor []writerTaskList = null;
	
	public static boolean bReadCompleted = false;
	public static boolean bMatchCompleted = false;
	
	public static ConcurrentLinkedQueue<Object> readQueue = new ConcurrentLinkedQueue<Object>();;
	public static ConcurrentLinkedQueue<Object> writeQueue = new ConcurrentLinkedQueue<Object>();;
	
	public FileProcessor() {
		
	}
	
	public void process(FileProcessorConfig properties) {
		startThread(properties);
		joinThread();
	}

	
	private void startThread(FileProcessorConfig properties) {
		
		Thread monitorThread = null;
		monitorThread = new Thread(new Monitor(), Monitor.class.getSimpleName());
		monitorThread.setDaemon(true);
		monitorThread.start();
		
		String sourcePath = properties.getSourceDumpFilePath();
		String outputPath = properties.getOutputDumpFilePath();

		readTaskList = new ReaderProcessor[READER_THREAD_COUNT];
		matchTaskList = new ControlProcessor[MATCHER_THREAD_COUNT];
		writerTaskList = new WriterProcessor[WRITER_THREAD_COUNT];
		
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
		

		Class<?> readerProcessor = null;
		Class<?> handleProcessor = null;
		Class<?> writerProcessor = null;

		try {
			readerProcessor = Class.forName(properties.getREADER_PROCESSOR());
			handleProcessor = Class.forName(properties.getHANDLE_PROCESSOR());
			writerProcessor = Class.forName(properties.getWRITER_PROCESSOR());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < READER_THREAD_COUNT; i++) {

			Class<?>[] paraType = new Class<?>[]{BlockingQueue.class, ConcurrentLinkedQueue.class};
			Constructor<?> constructor = null;
			try {
				constructor = readerProcessor.getConstructor(paraType);
				Object[] initPara = new Object[]{queue, readQueue};
				readTaskList[i] = (ReaderProcessor)constructor.newInstance(initPara);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			readTaskList[i].setName("READERTHREAD_" + i );
			readTaskList[i].start();
		}
		
		for (int i = 0; i < MATCHER_THREAD_COUNT; i++) {
			Class<?>[] paraType = new Class<?>[]{ConcurrentLinkedQueue.class, ConcurrentLinkedQueue.class};
			Constructor<?> constructor = null;
			
			try {
				constructor = handleProcessor.getConstructor(paraType);
				Object[] initPara = new Object[]{readQueue, writeQueue};
				matchTaskList[i] = (ControlProcessor)constructor.newInstance(initPara);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

			matchTaskList[i].setName("MATCHERTHREAD_" + i );
			matchTaskList[i].start();
		}
		
		for (int i = 0; i < WRITER_THREAD_COUNT; i++) {

			Class<?>[] paraType = new Class<?>[]{String.class, ConcurrentLinkedQueue.class};
			Constructor<?> constructor = null;
			
			try {
				constructor = writerProcessor.getConstructor(paraType);
				Object[] initPara = new Object[]{outputPath, writeQueue};
				writerTaskList[i] = (WriterProcessor)constructor.newInstance(initPara);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			writerTaskList[i].setName("WRITERTHREAD_" + i );
			writerTaskList[i].start();
		}
	}


	private void joinThread() {

		try {
			for (int i = 0; i < READER_THREAD_COUNT; i++) {
				readTaskList[i].join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < MATCHER_THREAD_COUNT; i++) {
			matchTaskList[i].setbReadCompleted(true);
		}
		
		try {
			for (int i = 0; i < MATCHER_THREAD_COUNT; i++) {
				matchTaskList[i].join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < WRITER_THREAD_COUNT; i++) {
			writerTaskList[i].setbHandleCompleted(true);
		}
		
		try {
			for (int i = 0; i < WRITER_THREAD_COUNT; i++) {
				writerTaskList[i].join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static class Monitor implements Runnable{

		public void run() {
			// TODO Auto-generated method stub
			
			while(true){
				System.out.println("Input : " + readQueue.size() + "\tOutput : " + writeQueue.size());
				try {
					Thread.sleep(2500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}

	private static Properties analyzeSystemProperties(String[] args){
		
		List<LongOpt> longOptList = new ArrayList<LongOpt>();

		longOptList.add(new LongOpt("config-file", LongOpt.REQUIRED_ARGUMENT, null, 0));
		longOptList.add(new LongOpt("help", LongOpt.NO_ARGUMENT, null, 1));
		longOptList.add(new LongOpt("SOURCE_DUMP_FILE_PATH", LongOpt.REQUIRED_ARGUMENT, null, 2));
		longOptList.add(new LongOpt("OUTPUT_DUMP_FILE_PATH", LongOpt.REQUIRED_ARGUMENT, null, 3));
		longOptList.add(new LongOpt("READER_THREAD_COUNT", LongOpt.REQUIRED_ARGUMENT, null, 4));
		longOptList.add(new LongOpt("HANDLE_THREAD_COUNT", LongOpt.REQUIRED_ARGUMENT, null, 5));
		longOptList.add(new LongOpt("WRITER_THREAD_COUNT", LongOpt.REQUIRED_ARGUMENT, null, 6));
		longOptList.add(new LongOpt("READER_CLASS", LongOpt.REQUIRED_ARGUMENT, null, 7));
		longOptList.add(new LongOpt("HANDLER_CLASS", LongOpt.REQUIRED_ARGUMENT, null, 8));
		longOptList.add(new LongOpt("WRITER_CLASS", LongOpt.REQUIRED_ARGUMENT, null, 9));
		

		LongOpt[] longopts = longOptList.toArray(new LongOpt[longOptList.size()]);
		Getopt g = new Getopt(FileProcessor.class.getSimpleName(), args, "", longopts);

		int optionId;
		String optionValue;
		
		Properties systemProperties = new Properties();
		
		while ((optionId = g.getopt()) != -1){
			switch (optionId){
				case 0:					
					optionValue = g.getOptarg();
					systemProperties.put("config-file", optionValue);
					break;
				case 1:
					systemProperties.put("help", "help");
					break;
				case 2:
					optionValue = g.getOptarg();
					systemProperties.put("SOURCE_DUMP_FILE_PATH", optionValue);
					break;
				case 3:
					optionValue = g.getOptarg();
					systemProperties.put("OUTPUT_DUMP_FILE_PATH", optionValue);
					break;
				case 4:
					optionValue = g.getOptarg();
					systemProperties.put("READER_THREAD_COUNT", optionValue);
					break;
				case 5:
					optionValue = g.getOptarg();
					systemProperties.put("MATCHER_THREAD_COUNT", optionValue);
					break;
				case 6:
					optionValue = g.getOptarg();
					systemProperties.put("WRITER_THREAD_COUNT", optionValue);
					break;
				case 7:
					optionValue = g.getOptarg();
					systemProperties.put("READER_CLASS", optionValue);
					break;
				case 8:
					optionValue = g.getOptarg();
					systemProperties.put("HANDLER_CLASS", optionValue);
					break;
				case 9:
					optionValue = g.getOptarg();
					systemProperties.put("WRITER_CLASS", optionValue);
					break;					
				default:
			    	systemProperties.put("help", "help");
			    	break;
			}
		}
		
		return systemProperties;
		

	}
	
	
	private static FileProcessorConfig analyzeProperties(Properties systemProperties){
		
		FileProcessorConfig config = new FileProcessorConfig();
		
		String propertiesFileName = systemProperties.getProperty("config-file");
		Properties properties = new Properties();
		
				
		FileInputStream fis;
		try {
			fis = new FileInputStream(propertiesFileName);
			properties.load(fis);
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Entry<Object, Object> entry:systemProperties.entrySet()){
			properties.put(entry.getKey(), entry.getValue());
		}

		// can override ?
		config.setSourceDumpFilePath(properties.getProperty("SOURCE_DUMP_FILE_PATH"));
		config.setOutputDumpFilePath(properties.getProperty("OUTPUT_DUMP_FILE_PATH"));
		config.setReaderThreadCount(properties.getProperty("READER_THREAD_COUNT"));
		config.setMatcherThreadCount(properties.getProperty("HANDLER_THREAD_COUNT"));
		config.setWriterThreadCount(properties.getProperty("WRITER_THREAD_COUNT"));
		config.setREADER_PROCESSOR(properties.getProperty("READER_CLASS"));
		config.setHANDLE_PROCESSOR(properties.getProperty("HANDLER_CLASS"));
		config.setWRITER_PROCESSOR(properties.getProperty("WRITER_CLASS"));
		return config;
	}
	
	private static void printUsage(){
		System.out.println("Usage : " + FileProcessor.class.getName() + " --config-file=<properties file> [OPTION]...");
		System.out.println("");
		System.out.println("file 愿��젴 �샃�뀡:");
		System.out.println("  --SOURCE_DUMP_FILE_PATH		�씫�뼱�뱾�씪 �뜡�봽 �뙆�씪�쓣 ���옣�맂 �뵒�젆�넗由щ�� 吏��젙�빀�땲�떎.");
		System.out.println("  --OUTPUT_DUMP_FILE_PATH		理쒖쥌 寃곌낵臾쇱쓣 ���옣�븷 �뵒�젆�넗由щ�� 吏��젙�빀�땲�떎.");
		System.out.println("");
		System.out.println("Thread 愿��젴 �샃�뀡:");
		System.out.println("  --READER_THREAD_COUNT         �뜡�봽 �뙆�씪�쓣 �씫�쓣 Thread�쓽 媛��닔瑜� 吏��젙�빀�땲�떎.");
		System.out.println("  --HANDLE_THREAD_COUNT        臾몄꽌瑜� 泥섎━�븷 Thread�쓽 媛��닔瑜� 吏��젙�빀�땲�떎.");
		System.out.println("  --WRITER_THREAD_COUNT       	臾몄꽌瑜� �뙆�씪�쓣 �벝 Thread�쓽 媛��닔瑜� 吏��젙�빀�땲�떎.");
		System.out.println("湲고� �샃�뀡:");
		System.out.println("  --config-file              file濡� �릺�뼱 �엳�뒗 �꽕�젙�쓣 媛��졇�샃�땲�떎.");
		System.out.println("  --help                     �씠 �룄��留먯쓣 蹂댁뿬以띾땲�떎.");
		System.out.println("");
		System.out.println("<heetae.lyu@nhncorp.com>�쑝濡� 踰꾧렇瑜� �븣�젮二쇱떗�떆�삤.");
	}
	
	public static void main(String args[]) {
		
		System.out.println("VERSION=" + VERSION);
		// parse command line options
		Properties systemProperties = analyzeSystemProperties(args);

		/*
		 * check
		 * - existence of the config-file
		 * - help option
		 */
		if(systemProperties.get("config-file") == null || systemProperties.get("help") != null){
			printUsage();
			return;
		}

		// parse config file
		FileProcessorConfig config = analyzeProperties(systemProperties);
		

		READER_THREAD_COUNT = config.getReaderThreadCount();
		MATCHER_THREAD_COUNT = config.getMathcerThreadCount();
		WRITER_THREAD_COUNT = config.getWriterThreadCount();
		

		FileProcessor fp = new FileProcessor();
		fp.process(config);

	}
}
