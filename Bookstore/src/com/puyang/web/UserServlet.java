package com.puyang.web;

import com.google.gson.Gson;
import com.puyang.types.User;
import com.puyang.service.UserService;
import com.puyang.service.impl.UserServiceImpl;
import com.puyang.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private final UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        String code = req.getParameter("code");
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        User user = WebUtils.copyParamsToBean(new User(), req.getParameterMap());
        User loginUser = userService.login(user);

        if (token != null && token.equalsIgnoreCase(code)) {
            if (loginUser == null) {
                req.setAttribute("errorMsg", "用户名或密码错误");
                req.setAttribute("username", user.getUsername());
                req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            } else {
                req.getSession().setAttribute("user", loginUser);
                req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("errorMsg", "验证码错误");
            req.setAttribute("username", user.getUsername());
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }
    }

    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        String code = req.getParameter("code");
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        User user = WebUtils.copyParamsToBean(new User(), req.getParameterMap());
        req.setAttribute("email", user.getEmail());

        if (token != null && token.equalsIgnoreCase(code)) {
            if (userService.existsUsername(user.getUsername())) {
                req.setAttribute("errorMsg", "用户名已存在");
                req.getRequestDispatcher("/pages/user/register.jsp").forward(req, resp);
            } else {
                userService.registerUser(user);
                req.getSession().setAttribute("user", user);
                req.getRequestDispatcher("/pages/user/register_success.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("errorMsg", "验证码错误");
            req.setAttribute("username", user.getUsername());
            req.getRequestDispatcher("/pages/user/register.jsp").forward(req, resp);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        Map<String, Object> resultMap = new HashMap<>();
        boolean existsUsername = userService.existsUsername(username);
        resultMap.put("existsUsername", existsUsername);
        resp.getWriter().write(new Gson().toJson(resultMap));
    }
}
