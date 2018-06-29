package com.sexyalbum.filter;

import com.sexyalbum.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "LoginFilter")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String url=((HttpServletRequest)servletRequest).getRequestURL().toString();
        if(!(url.contains("/account/login")||url.contains("/account/signup")||url.contains(".html"))) {
            HttpSession session = ((HttpServletRequest) servletRequest).getSession();
            User currentUser;
            boolean loginSuccess = true;
            if (session == null)
                loginSuccess = false;
            if (loginSuccess && (currentUser = (User) session.getAttribute("currentuser")) == null)
                loginSuccess = false;
            if (!loginSuccess)
                ((HttpServletResponse) servletResponse).sendRedirect("/login.html");
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
