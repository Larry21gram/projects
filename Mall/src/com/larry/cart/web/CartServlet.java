package com.larry.cart.web;

import com.larry.book.domain.Book;
import com.larry.book.service.exception.NoThatBookException;
import com.larry.cart.domain.CartItem;
import com.larry.cart.service.CartService;
import com.larry.cart.service.exception.NoMapException;
import com.larry.util.BaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CartServlet",urlPatterns = "/cart")
public class CartServlet extends BaseService{
    private CartService cartService = new CartService();

    public String addCart(HttpServletRequest request ,HttpServletResponse response){
        String key = request.getParameter("count");
        String bid = request.getParameter("bid");

        int count = Integer.parseInt(key);
        CartItem cartItem = new CartItem();
        try {
            Book booksByBid = cartService.getBooksByBids(bid);
            cartItem.setBook(booksByBid);
            cartItem.setCount(count);

            Object car = request.getSession().getAttribute("car");
            Map<String ,CartItem> map = (Map<String, CartItem>) car;
            map.put(bid,cartItem);
            List<CartItem> cartItemList = cartService.getCartItemList(map);

            request.getSession().setAttribute("car",map);
            request.setAttribute("list",cartItemList);
            return "f:/jsps/cart/list.jsp";
        } catch (NoThatBookException e) {
            request.setAttribute("msg",e);
            return "f:/jsps/msg.jsp";
        }
    }

    public  String deleteAll(HttpServletRequest request ,HttpServletResponse response){
        Map<String,CartItem> map = (Map<String, CartItem>) request.getSession().getAttribute("car");
        map.clear();
        request.setAttribute("list",null);
        return "f:/jsps/cart/list.jsp";
    }

    public String deleteOne(HttpServletRequest request ,HttpServletResponse response){
        Map<String,CartItem> map = (Map<String, CartItem>) request.getSession().getAttribute("car");
        String bid = request.getParameter("bid");
        Map<String, CartItem> byBid = cartService.deleteByBid(bid, map);
        request.getSession().setAttribute("car",byBid);
        List<CartItem> itemList = null;
        itemList = cartService.getCartItemList(byBid);
        request.setAttribute("list",itemList);
        return "f:/jsps/cart/list.jsp";

    }

    public String show(HttpServletRequest request ,HttpServletResponse response){
        Map<String,CartItem> map = (Map<String, CartItem>) request.getSession().getAttribute("car");
        List<CartItem> list = null;
        list = cartService.getCartItemList(map);
        request.setAttribute("list",list);
        return "f:/jsps/cart/list.jsp";
    }

}
