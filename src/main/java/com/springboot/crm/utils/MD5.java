package com.springboot.crm.utils;

import java.security.MessageDigest;

public class MD5 {
	public String PasswordEncryption(String password) throws Exception {
		String str = new String();
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte b[] = md.digest();
		int x;
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < b.length; i++) {
			x = b[i];
			if (x < 0)
				x += 256;
			if (x < 16)
				buf.append("0");
			buf.append(Integer.toHexString(x));
		}
		str = buf.toString();
		return str;
	}
	public static void main(String[] args) throws Exception{
		System.out.println(new MD5().PasswordEncryption("manager"));

	}
}
