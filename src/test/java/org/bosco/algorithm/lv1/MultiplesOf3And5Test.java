package org.bosco.algorithm.lv1;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.Assert;

public class MultiplesOf3And5Test {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * If we list all the natural numbers below 10 that are multiples of 3 or 5, 
	 * we get 3, 5, 6 and 9. The sum of these multiples is 23.
	 * Find the sum of all the multiples of 3 or 5 below 1000.
	 */
	@Test
	public void testMultiplesOf3And5V1() {
		//int expected = 0;
		long expected = 233168;
		long sum = new MultiplesOf3And5().multiplesOf3And5Method1(1000);
		Assert.assertEquals(expected, sum);
		
		//int expected = 0;
		expected = 233333333166666668L;
		long start = System.currentTimeMillis();
		sum = new MultiplesOf3And5().multiplesOf3And5Method1(1000000000);
		long end = System.currentTimeMillis();
		System.out.println("time : " + (end - start));
		Assert.assertEquals(expected, sum);
	}
	
	
	@Test
	public void testMultiplesOf3And5V2() {
		//int expected = 0;
		long expected = 233168;
		long sum = new MultiplesOf3And5().multiplesOf3And5Method2(1000);
		Assert.assertEquals(expected, sum);
		
		//int expected = 0;
		expected = 233333333166666668L;
		long start = System.currentTimeMillis();
		sum = new MultiplesOf3And5().multiplesOf3And5Method2(1000000000);
		long end = System.currentTimeMillis();
		System.out.println("time : " + (end - start));
		Assert.assertEquals(expected, sum);
	}
	
	@Test
	public void testMultiplesOf3And5V3() {
		//int expected = 0;
		long expected = 233168;
		long sum = new MultiplesOf3And5().multiplesOf3And5Method3(1000);
		Assert.assertEquals(expected, sum);
		
		//int expected = 0;
		expected = 233333333166666668L;
		long start = System.currentTimeMillis();
		sum = new MultiplesOf3And5().multiplesOf3And5Method3(1000000000);
		long end = System.currentTimeMillis();
		System.out.println("time : " + (end - start));
		Assert.assertEquals(expected, sum);
	}
	
	

}
