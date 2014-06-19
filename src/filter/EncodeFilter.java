package filter;

import java.io.IOException;
import javax.servlet.*;

public class EncodeFilter implements Filter{
	
	private String encoding;
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// ÐÞ¸ÄÄ¬ÈÏ±àÂëÎª utf-8
		request.setCharacterEncoding("utf-8");
		chain.doFilter(request, response);
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
