package com.hk.core.authentication.security.encrypt;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.hk.commons.util.encrypt.Encrypt;

/**
 * 
 * @author huangkai
 * @date 2017年12月19日下午1:12:14
 */
public class SecurityPasswordEncrypt implements Encrypt, PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return null;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return false;
	}

	@Override
	public String asSha512HashToBase64(Object source, Object salt) {
		return null;
	}

	@Override
	public String asSha512HashToBase64(Object source, Object salt, int hashIterations) {
		return null;
	}

	@Override
	public String asMD5ToString(Object source, Object salt) {
		return null;
	}

	@Override
	public String asMD5ToString(Object source, Object salt, int hashIterations) {
		return null;
	}

}
