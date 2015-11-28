package org.bosco.jdk;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GnuParserTest {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
    
    private static final Options options	= new Options();
    
    
    static {
		options.addOption(OptionBuilder.hasArg().withArgName("where").isRequired()
				.withDescription("where option")
				.create("where"));
		
		options.addOption(OptionBuilder.hasArg().withArgName("config").isRequired()
				.withDescription("config file")
				.create("config"));
		
		options.addOption(OptionBuilder.hasArg().withArgName("delete")
				.withDescription("delete option(=L(Location),D(Document),C(Content),I(ContentInfo))")
				.create("delete"));
		
		options.addOption(OptionBuilder.hasArg().withArgName("reset")
				.withDescription("reset fields to NULL (in DOCUMENT_CONTENT_INFO)")
				.create("reset"));
		options.addOption(OptionBuilder.hasArg().withArgName("sleep")
				.withDescription("sleep time(milli seconds) after query execution")
				.create("sleep"));
		
		options.addOption(OptionBuilder.hasArg().withArgName("count")
				.withDescription("affected row count for query excution")
				.create("count"));
		
		options.addOption(OptionBuilder.hasArg(false).withArgName("toD0")
				.withDescription("update DOCUMENT.D_TYPE to 'D0'")
				.create("toD0"));
	};
	
	public static void main(String args[]) {
		
		for (int i = 0; i < args.length; i ++) {
			System.out.println(args[i]);	
		}
		
		CommandLineParser cmdLineParser = new GnuParser();
		CommandLine cmdLine = null;
		try {
			cmdLine = cmdLineParser.parse(options, args);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String whereclause	= cmdLine.getOptionValue("where");
		String config 		= cmdLine.getOptionValue("config");
		String deleteOption = cmdLine.getOptionValue("delete");
		String resetOption = cmdLine.getOptionValue("reset");
		boolean resetToD0 = cmdLine.hasOption("toD0");
		
		int querySleepTime;
		try {
			querySleepTime = Integer.parseInt(cmdLine.getOptionValue("sleep", "100"));
		} catch (NumberFormatException e) {
			System.err.println("Invalid value for querySleepTime");
			System.exit(1);
			return;
		}
		int deleteGroupCount;
		try {
			deleteGroupCount = Integer.parseInt(cmdLine.getOptionValue("count", "100"));
		} catch (NumberFormatException e) {
			System.err.println("Invalid value for deleteGroupCount");
			System.exit(1);
			return;
		}
		
		if(whereclause==null)
			return;
		whereclause = whereclause.trim();
		if(whereclause.length()<=0)
			return;
		
		System.out.println("##CONFIG :" + config);
		System.out.println("##WHERE :" + whereclause);
		System.out.println("##DELETE :" + deleteOption);
		System.out.println("##RESET :" + resetOption);
		System.out.println("##SLEEP :" + querySleepTime);
		System.out.println("##COUNT :" + deleteGroupCount);
		System.out.println("##TOD0 :" + resetToD0);
		
		
		return ;
	}
}
