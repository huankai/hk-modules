package com.hk.core.authentication.security;

import org.springframework.security.core.context.SecurityContextHolder;

import com.hk.core.authentication.api.SecurityContext;
import com.hk.core.authentication.api.UserPrincipal;

public class SpringSecurityContext implements SecurityContext {

	@Override
	public UserPrincipal getPrincipal() {
		return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	@Override
	public boolean isAuthenticated() {
		return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
	}

	@Override
	public void setSessionAttribute(Object key, Object value) {
	}

	@Override
	public void setSessionAttribute(Object key, Object value, boolean create) {
	}

	@Override
	public <T> T getSessionAttribute(Object key) {
		return null;
	}

	@Override
	public void removeSessionAttribute(Object key) {

	}

}
