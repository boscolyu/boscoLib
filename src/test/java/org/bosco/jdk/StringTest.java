package org.bosco.jdk;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

	
public class StringTest {


	private static final Logger logger = LoggerFactory.getLogger(StringTest.class);
	
	public void TestString1() {
		long startMatchTime = System.currentTimeMillis();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(String.format("ROBOTRULE : match time uri = %s, time=%d", "test", System.currentTimeMillis() - startMatchTime));
		return ;
	}
}