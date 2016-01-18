package org.bosco.algorithm.lv1;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.Assert;

public class StringCompressorTest {

	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Test
	public void testStringCompressor() {
		
		String source = "aaabbcccccca";
		String expected = "a3b2c6a1";
		
		String actual = new StringCompressor().compress(source);

		Assert.assertEquals(expected, actual);
	}
}
