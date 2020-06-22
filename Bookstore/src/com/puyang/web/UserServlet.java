package com.puyang.web;

import com.puyang.pojo.User;
import com.puyang.service.UserService;
import com.puyang.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends BaseServlet {
    private final UserService userService = new UserServiceImpl();

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User loginUser = userService.login(User.builder()
                .username(username)
                .password(password)
                .build());
        if (loginUser == null) {
            req.setAttribute("errorMsg", "用户名或密码错误");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        if ("abcde".equalsIgnoreCase(code)) {
            if (userService.existsUsername(username)) {
                req.setAttribute("errorMsg", "用户名已存在");
                req.getRequestDispatcher("/pages/user/register.jsp").forward(req, resp);
            } else {
                userService.registerUser(User.builder()
                        .username(username)
                        .password(password)
                        .email(email)
                        .build());
                req.getRequestDispatcher("/pages/user/register_success.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("errorMsg", "验证码错误");
            req.getRequestDispatcher("/pages/user/register.jsp").forward(req, resp);
        }
    }
}
