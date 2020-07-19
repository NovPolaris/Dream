package com.puyang.web;

import com.puyang.service.OrderService;
import com.puyang.service.impl.OrderServiceImpl;
import com.puyang.types.Order;
import com.puyang.types.OrderItem;
import com.puyang.types.User;
import com.puyang.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet {
    private final OrderService orderService = new OrderServiceImpl();

    protected void createOrder() {
        orderService.createOrder();
    }

    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = orderService.showAllOrders();
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }

    protected void shipOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId = WebUtils.parseInt(req.getParameter("orderId"), -1);
        if (orderId == -1) {
            req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
            return;
        }
        orderService.shipOrder(orderId);
        resp.sendRedirect("orderServlet?action=showAllOrders");
    }

    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId = WebUtils.parseInt(req.getParameter("orderId"), -1);
        if (orderId == -1) {
            req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
            return;
        }
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        req.setAttribute("orderItems", orderItems);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
    }

    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
            return;
        }
        List<Order> orders = orderService.showMyOrders(user.getId());
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
    }

    protected void signOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId = WebUtils.parseInt(req.getParameter("orderId"), -1);
        if (orderId == -1) {
            req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
            return;
        }
        orderService.signOrder(orderId);
        resp.sendRedirect("orderServlet?action=showMyOrders");
    }
}
