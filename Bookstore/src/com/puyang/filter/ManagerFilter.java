package com.puyang.filter;

import com.puyang.types.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ManagerFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        if (isAdmin(user)) {
            chain.doFilter(request, response);
            return;
        }
        request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
    }

    private boolean isAdmin(User user) {
        return user != null && "admin".equals(user.getUsername());
    }
}
