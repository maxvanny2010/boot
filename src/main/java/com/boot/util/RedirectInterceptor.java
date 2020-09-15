package com.boot.util;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * RedirectInterseptor.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 9/15/2020
 */
public class RedirectInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(final HttpServletRequest request,
                           final HttpServletResponse response,
                           final Object handler,
                           final ModelAndView modelAndView) {
        if (modelAndView != null) {
            String args = request.getQueryString() != null ? request.getQueryString() : "";
            String url = request.getRequestURI() + "?" + args;
            response.setHeader("Turbolinks-Location", url);
        }
    }
}
