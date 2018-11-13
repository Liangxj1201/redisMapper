
package com.example.demo.config;

import java.security.MessageDigest;

public class Md5Tools {

	private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	private static String byteToString(byte abyte0[]) {
		int i = abyte0.length;
		char ac[] = new char[i * 2];
		int j = 0;
		for (int k = 0; k < i; k++) {
			byte byte0 = abyte0[k];
			ac[j++] = hexDigits[byte0 >>> 4 & 0xf];
			ac[j++] = hexDigits[byte0 & 0xf];
		}
		return new String(ac);
	}

	/**
	 * @fun Get Md5 value of String
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public static String getMd5(String s) throws Exception {
		MessageDigest messagedigest = MessageDigest.getInstance("MD5");
		messagedigest.reset();
		byte abyte0[] = messagedigest.digest(s.getBytes());
		return byteToString(abyte0);
	}
	
	public static void main(String srg[]) throws Exception{
		System.out.println(Md5Tools.getMd5("111111"));
	}

}

