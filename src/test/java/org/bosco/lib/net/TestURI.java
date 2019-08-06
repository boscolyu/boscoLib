package org.bosco.lib.net;

import java.net.URI;
import java.net.URISyntaxException;

public class TestURI {

	public static void main(String args[]) {
		URI uri = null;
		try {
			uri = new URI("http://www.naver.com");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		URI normalizedURI = uri.normalize();
		System.out.println(normalizedURI.toString());
	}
}
