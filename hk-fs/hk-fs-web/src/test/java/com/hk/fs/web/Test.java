package com.hk.fs.web;

import com.hk.commons.util.encrypt.Encrypt;
import com.hk.core.authentication.security.encrypt.SecurityPasswordEncrypt;

public class Test {

	public static void main(String[] args) {
		Encrypt encrypt = new SecurityPasswordEncrypt();
		System.out.println(encrypt.asMD5ToString("admin", "admin"));
		System.out.println(encrypt.asMD5ToString("admin", "admin", 2));
		System.out.println(encrypt.asSha512HashToBase64("admin", "admin"));
		System.out.println(encrypt.asSha512HashToBase64("admin", "admin", 2));

	}
}
