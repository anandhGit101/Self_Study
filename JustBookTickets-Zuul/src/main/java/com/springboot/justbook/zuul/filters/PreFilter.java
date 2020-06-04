/**
 * 
 */
package com.springboot.justbook.zuul.filters;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;
import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;

public class PreFilter extends ZuulFilter {
	
	static Logger LOGGER = LoggerFactory.getLogger(PreFilter.class);

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}
	
	@Override
	public boolean shouldFilter() {
		RequestContext context = getCurrentContext();
		return context.getRequest().getParameter("service") != null;
	}

	@Override
	public Object run() {

		try {
			RequestContext context = getCurrentContext();
			LOGGER.info("Request Method : {}, Request URL {}: ", context.getRequest().getMethod(),
					context.getRequest().getRequestURL());
			InputStream in = (InputStream) context.get("requestEntity");
			if (in == null) {
				in = context.getRequest().getInputStream();
			}
			String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
			body = "request body modified via request wrapper: " + body;
			byte[] bytes = body.getBytes("UTF-8");
			context.setRequest(new HttpServletRequestWrapper(getCurrentContext().getRequest()) {
				@Override
				public ServletInputStream getInputStream() throws IOException {
					return new ServletInputStreamWrapper(bytes);
				}

				@Override
				public int getContentLength() {
					return bytes.length;
				}

				@Override
				public long getContentLengthLong() {
					return bytes.length;
				}
			});
			LOGGER.info("Exiting Request Method : {}, Request URL {}: ", context.getRequest().getMethod(),context.getRequest().getRequestURL());
		} catch (IOException e) {
			LOGGER.info("Exception occurred");
			rethrowRuntimeException(e);
		}
		return null;
	}
}