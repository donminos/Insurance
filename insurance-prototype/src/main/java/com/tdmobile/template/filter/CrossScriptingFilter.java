package com.tdmobile.template.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class CrossScriptingFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        //logger.info("In doFilter CrossScriptingFilter  ...............");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        //String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        //Enumeration<String> headerNames = httpRequest.getHeaderNames();

        /*if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String nameHeader = headerNames.nextElement();
                System.out.println("Header: " + nameHeader + ":" + httpRequest.getHeader(nameHeader));
            }//Accept-Language
        }*/
        Cookie[] cookies = httpRequest.getCookies();
        Cookie myCookie = null;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i += 1) {
                if (cookies[i].getName().equals("localeCookie")) {
                    myCookie = cookies[i];
                    break;
                }
            }
        }
        String ClearLanguage = request.getLocale().toString();
        if (myCookie == null) {
            myCookie = new Cookie("localeCookie", ClearLanguage);
            myCookie.setPath("/");
            httpResponse.addCookie(myCookie);
            httpResponse.setHeader("Content-Language", ClearLanguage);
        } else {
            if (!myCookie.getValue().equals(ClearLanguage)) {
                myCookie.setValue(ClearLanguage);
                myCookie.setPath("/");
                httpResponse.addCookie(myCookie);
                httpResponse.setHeader("Content-Language", ClearLanguage);
            }
        }

        chain.doFilter(new RequestWrapper((HttpServletRequest) request), response);
        //logger.info("Out doFilter CrossScriptingFilter ...............");
    }

}

