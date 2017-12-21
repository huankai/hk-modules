package com.hk.core.authentication.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hk.core.authentication.api.SecurityContext;
import com.hk.core.authentication.api.UserPrincipal;
import com.hk.core.web.Webs;

public class SpringSecurityContext implements SecurityContext {

	@Override
	public UserPrincipal getPrincipal() {
		return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	@Override
	public boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return null != authentication && !(authentication instanceof AnonymousAuthenticationToken)
				&& authentication.isAuthenticated();
	}

	@Override
	public void setSessionAttribute(Object key, Object value) {
		setSessionAttribute(key, value, false);
	}

	@Override
	public void setSessionAttribute(Object key, Object value, boolean create) {
		Webs.setAttributeFromSession((String) key, value, create);
	}

	@Override
	public <T> T getSessionAttribute(Object key) {
		return Webs.getAttributeFromSession((String) key);
	}

	@Override
	public void removeSessionAttribute(Object key) {
		Webs.removeAttributeFromSession((String) key);
	}

}
