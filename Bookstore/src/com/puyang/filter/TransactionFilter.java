package com.puyang.filter;

import com.puyang.utils.JdbcUtils;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TransactionFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        try {
            chain.doFilter(request,response);
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
