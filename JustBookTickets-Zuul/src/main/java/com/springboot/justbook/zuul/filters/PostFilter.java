/**
 * 
 */
package com.springboot.justbook.zuul.filters;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;
import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @author M1006601
 *
 */

public class PostFilter extends ZuulFilter {
	
	static Logger LOGGER = LoggerFactory.getLogger(PostFilter.class);
 
  @Override
  public String filterType() {
    return "post";
  }
 
  @Override
  public int filterOrder() {
    return 1;
  }
 
  @Override
  public boolean shouldFilter() {
	  RequestContext context = getCurrentContext();
		return context.getRequest().getParameter("service") == null;
  }
 
	@Override
	public Object run() {
		
		
		RequestContext ctx = RequestContext.getCurrentContext();
		InputStream responseDataStream = ctx.getResponseDataStream();
		try {
			String responseAsString = StreamUtils.copyToString(responseDataStream, Charset.forName("UTF-8"));
			LOGGER.info("The responseBody after the post filter...:: {}", responseAsString);
			ctx.setResponseBody(responseAsString);
			LOGGER.info("Exiting Response Filter");
		} catch (IOException e) {
			LOGGER.info("Exception Occurred");
			rethrowRuntimeException(e);
		}
		return null;
	}
}