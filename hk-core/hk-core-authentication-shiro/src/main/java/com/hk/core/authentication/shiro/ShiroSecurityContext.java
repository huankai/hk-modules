package com.hk.core.authentication.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.hk.core.authentication.api.SecurityContext;
import com.hk.core.authentication.api.UserPrincipal;

/**
 * 
 * @author huangkai
 *
 */
public class ShiroSecurityContext implements SecurityContext {

	@Override
	public UserPrincipal getPrincipal() {
		return SecurityUtils.getSubject().getPrincipals().oneByType(UserPrincipal.class);
	}

	@Override
	public boolean isAuthenticated() {
		return SecurityUtils.getSubject().isAuthenticated();
	}

	@Override
	public void setSessionAttribute(Object key, Object value) {
		setSessionAttribute(key, value, false);
	}

	@Override
	public void setSessionAttribute(Object key, Object value, boolean create) {
		Session session = SecurityUtils.getSubject().getSession(create);
		if (null != session) {
			session.setAttribute(key, value);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getSessionAttribute(Object key) {
		return (T) SecurityUtils.getSubject().getSession().getAttribute(key);
	}

	@Override
	public void removeSessionAttribute(Object key) {
		Session session = SecurityUtils.getSubject().getSession(false);
		if (null != session) {
			session.removeAttribute(key);
		}
	}

}
