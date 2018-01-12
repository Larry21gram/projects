package com.larry.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseService extends HttpServlet {
    /**
     * 获得依据方法名获得子类方法，并运用方法返回跳转页面路径。
     * 依据路径进行跳转。
     * @param req 请求
     * @param resp 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");

        Method relMethod = null;
        try {
            relMethod = this.getClass().getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
        } catch (NoSuchMethodException e) {
            throw  new RuntimeException("sorry,there is not a method which name is " + method);
        }
        if (relMethod != null){
            try {
                String result = (String) relMethod.invoke(this,req, resp);
                String[] split = result.split(":");
                String key = split[0];
                String path = split[1];
                if (key.equals("f")){
                   req.getRequestDispatcher(path).forward(req,resp);
               }else if (key.equals("s")){
                    resp.sendRedirect(path);
                }
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        }

    }
}
