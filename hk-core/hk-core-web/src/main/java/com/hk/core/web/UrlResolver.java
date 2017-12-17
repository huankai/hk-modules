package com.hk.core.web;

import javax.servlet.ServletContext;

import com.hk.commons.util.AssertUtils;

/**
 *
 */
public class UrlResolver{

    /**
     *
     * @param ctx
     * @param url
     * @return
     */
	public static String resolveUrl(ServletContext ctx, String url){
		AssertUtils.notNull(ctx,"ctx");
		AssertUtils.notNull(url,"url");
		if(url.startsWith("/")){
			return String.format("%s%s", ctx.getContextPath(),url);
		}
		return url;
	}

}
