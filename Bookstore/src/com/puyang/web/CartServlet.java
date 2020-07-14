package com.puyang.web;

import com.puyang.pojo.Book;
import com.puyang.pojo.Cart;
import com.puyang.pojo.CartItem;
import com.puyang.pojo.User;
import com.puyang.service.BookService;
import com.puyang.service.CartService;
import com.puyang.service.impl.BookServiceImpl;
import com.puyang.service.impl.CartServiceImpl;
import com.puyang.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

public class CartServlet extends BaseServlet {
    private final CartService cartService = new CartServiceImpl();
    private final BookService bookService = new BookServiceImpl();

    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Cart cart = cartService.queryCart(user.getUsername());
        req.setAttribute("cart", cart);
        req.getRequestDispatcher("/pages/cart/cart.jsp").forward(req, resp);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        Book book = bookService.queryBookById(WebUtils.parseInt(req.getParameter("id"), -1));
        CartItem cartItem = helper(book);

        cartService.addItem(cartItem, user.getUsername());
        req.getSession().setAttribute("operation", "add");
        req.getSession().setAttribute("lastName", cartItem.getName());
        req.getSession().setAttribute("totalCountInCart", cartService.queryForTotalCount(user.getUsername()));
        resp.sendRedirect(req.getHeader("Referer"));
    }

    private CartItem helper(Book book) {
        return CartItem.builder()
                .sku(UUID.nameUUIDFromBytes(book.getName().getBytes()).toString())
                .name(book.getName())
                .count(1)
                .price(BigDecimal.valueOf(book.getPrice()))
                .totalPrice(BigDecimal.valueOf(book.getPrice()))
                .build();
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        String sku = req.getParameter("sku");
        cartService.deleteItem(sku, user.getUsername());
        req.getSession().setAttribute("operation", "delete");
        req.getSession().setAttribute("lastName", req.getParameter("name"));
        req.getSession().setAttribute("totalCountInCart", cartService.queryForTotalCount(user.getUsername()));
        resp.sendRedirect("cartServlet?action=list");
    }

    public void clear(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        cartService.clear(user.getUsername());
        req.getSession().setAttribute("operation", "clear");
        req.getSession().setAttribute("totalCountInCart", cartService.queryForTotalCount(user.getUsername()));
        resp.sendRedirect("cartServlet?action=list");
    }

    public void updateItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        String sku = req.getParameter("sku");
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        cartService.updateItem(sku, count, user.getUsername());
        req.getSession().setAttribute("operation", "update");
        req.getSession().setAttribute("lastName", req.getParameter("name"));
        req.getSession().setAttribute("totalCountInCart", cartService.queryForTotalCount(user.getUsername()));
        resp.sendRedirect("cartServlet?action=list");
    }
}
