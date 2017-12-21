package com.hk.core.authentication.security;

import java.util.Objects;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hk.core.authentication.api.SecurityContext;
import com.hk.core.authentication.api.UserPrincipal;
import com.hk.core.web.Webs;

/**
 * 
 * @author huangkai
 *
 */
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
		if (!Objects.isNull(key)) {
			Webs.setAttributeFromSession(key.toString(), value, create);
		}
	}

	@Override
	public <T> T getSessionAttribute(Object key) {
		return Objects.isNull(key) ? null : Webs.getAttributeFromSession(key.toString());
	}

	@Override
	public void removeSessionAttribute(Object key) {
		if (!Objects.isNull(key)) {
			Webs.removeAttributeFromSession((String) key);
		}
	}

}
