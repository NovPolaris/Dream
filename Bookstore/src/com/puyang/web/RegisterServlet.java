package com.puyang.web;

import com.puyang.pojo.User;
import com.puyang.service.UserService;
import com.puyang.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        if ("abcde".equalsIgnoreCase(code)) {
            if (userService.existsUsername(username)) {
                System.out.println("用户名[" + username + "]已存在!");
                req.getRequestDispatcher("/pages/user/register.html").forward(req, resp);
            } else {
                userService.registerUser(User.builder()
                        .username(username)
                        .password(password)
                        .email(email)
                        .build());
                req.getRequestDispatcher("/pages/user/register_success.html").forward(req, resp);
            }
        } else {
            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/register.html").forward(req, resp);
        }
    }
}
