package org.bosco.jdk;

import java.io.UnsupportedEncodingException;
import java.net.IDN;
import java.net.URI;
import java.net.URISyntaxException;


public class KoreanURITest {
	public static void main(String []args) {
//		try {
//			String koreaDomainTest = "http://�ѱ�.blogspot.com/2011/01/���-���ã��-p2p��õ-��ƺý��ϴ�.html";
//			
//			testIDNMethod("�ѱ�.blogspot.com");
//			
//			URI koreaDomainTestUri = new URI(koreaDomainTest);
//			URI normalizedKoreaDomainTestUri = koreaDomainTestUri.normalize();
//			
//			URLNormalizer0 urlnormal = new URLNormalizer0(); 
//			try {
//				System.out.println(urlnormal.normalize(koreaDomainTest, "utf-8"));
//				System.out.println(urlnormal.normalize(koreaDomainTest, "euc-kr"));
//			} catch (URLNormalizerException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println(koreaDomainTestUri.getHost());
//			System.out.println(normalizedKoreaDomainTestUri.getHost());
//			System.out.println(normalizedKoreaDomainTestUri.toString());
//			System.out.println(normalizedKoreaDomainTestUri.toASCIIString());
//			
//			URI uri = new URI("http://amoratorio.blogspot.com/2011/01/���-���ã��-p2p��õ-��ƺý��ϴ�.html");
//			System.out.println(uri.getHost());
//			URI normalizedUri = uri.normalize();
//			
//			System.out.println(normalizedUri.toString());
//			System.out.println(normalizedUri.toASCIIString());
//			System.out.println(uri.toString());
//
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	private static void testIDNMethod(String host) {
		//RFC 3490
		System.out.println("source : " + host);
		String changedHost = IDN.toASCII(host);
		System.out.println("toASCII : " + changedHost);
		String orgHost = IDN.toUnicode(changedHost);
		System.out.println("toUnicode : " + orgHost);
	}
}
