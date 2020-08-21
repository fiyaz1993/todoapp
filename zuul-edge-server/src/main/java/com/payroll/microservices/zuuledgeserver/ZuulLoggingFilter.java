/**
 * 
 */
package com.payroll.microservices.zuuledgeserver;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @author dach543
 *
 */
public class ZuulLoggingFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(ZuulLoggingFilter.class);
	
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest req = ctx.getRequest();
		
		log.info(String.format("%s requests to %", req.getMethod(), req.getRequestURL()));
		
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public String filterType() {
		return "pre";
	}
	
}
