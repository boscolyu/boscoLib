package org.bosco.jdk;

import java.io.UnsupportedEncodingException;

import com.twmacinta.util.MD5;
import org.junit.Test;


public class MD5Test {

	@Test
	public void TestMD51() {
		
		String url = "http://www.lykzone.com/home/4837";
		String encoding = "utf-8";

		MD5 md5 = new MD5();
		try {
			md5.Update(url, encoding);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String md51 = md5.asHex();
		String globalDocId = md51.substring(0, md51.length() / 2);
		System.out.println(url);
		System.out.println(globalDocId);
		
	}
}