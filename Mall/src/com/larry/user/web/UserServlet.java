package com.larry.user.web;

import com.larry.cart.domain.CartItem;
import com.larry.user.domain.User;
import com.larry.user.service.UserService;
import com.larry.user.service.exception.*;
import com.larry.util.BaseService;
import com.larry.util.UUIds;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "UserServlet",urlPatterns = "/user")
public class UserServlet extends BaseService{

    private UserService userService = new UserService();

    public String regist(HttpServletRequest request , HttpServletResponse response) throws BreakHeartException, ServletException, IOException {
        // TODO: 2018/1/10 如果信息正确，返回到登录，不正确到注册
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
            user.setUid(UUIds.getId32());
            user.setCode(UUIds.getId64());
            user.setState(0);
//            System.out.println(user);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        try {
            userService.isRegisted(user);
            return "s:/jsps/user/login.jsp";
        } catch (UserException e) {
            request.setAttribute("msg",e);
            return "f:/jsps/msg.jsp";
        }
    }

    public String login(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            User userDB = userService.isRight(username, password);
            request.getSession().setAttribute("user",userDB);
            // TODO: 2018/1/11 创建购物车Map
            Map<String,CartItem> carMap = new HashMap<String, CartItem>();
            request.getSession().setAttribute("car",carMap);

            return "s:/jsps/main.jsp";
        } catch (UserException e) {
            request.setAttribute("msg",e);
            return "f:/jsps/msg.jsp";
        }
    }

    public String change(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        try {
            userService.changeState(code);
            return "s:/jsps/user/login.jsp";
        } catch (ChangeFailedException e) {
            request.setAttribute("msg",e);
            return "f:/jsps/msg.jsp";
        }

    }


}
