package com.larry.book.web;

import com.larry.book.dao.BookDao;
import com.larry.book.domain.Book;
import com.larry.book.service.BookService;
import com.larry.book.service.exception.NoBookException;
import com.larry.book.service.exception.NoThatBookException;
import com.larry.book.service.exception.NoThatBooksException;
import com.larry.util.BaseService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "BookServlet" ,urlPatterns = "/book")
public class BookServlet extends BaseService {
    private BookDao bookDao = new BookDao();
    private BookService bookService = new BookService();

    public String removeUser(HttpServletRequest request , HttpServletResponse response){
        request.getSession().removeAttribute("user");
        return "s:/jsps/main.jsp";
    }

    public String show(HttpServletRequest request , HttpServletResponse response){
        // TODO: 2018/1/11   把所有书籍查出并显示到页面上
        try {
            List<Book> books = bookService.getAllBook();
//            for (Book book : books) {
//                System.out.println(book.getImage());
//            }
            request.getSession().setAttribute("bookList",books);
           return "s:/jsps/book/list.jsp";
        } catch (NoBookException e) {
            request.setAttribute("msg",e);
            return "f:/jsps/msg.jsp";
        }
    }

    public String javase(HttpServletRequest request , HttpServletResponse response){
        String key = "JavaSE";
        return getString(request, key);
    }

    public String javaee(HttpServletRequest request , HttpServletResponse response){
        String key = "JavaEE";
        return getString(request, key);
    }

    public String javaScript(HttpServletRequest request , HttpServletResponse response){
        String key = "Javascript";
        return getString(request, key);
    }

    // TODO: 2018/1/11 依据分类查出书籍并将要连接的地址返回
    private String getString(HttpServletRequest request, String key) {
        try {
            List<Book> bookByKey = bookService.getBookByKey(key);
            request.getSession().setAttribute("bookList",bookByKey);
            return "s:/jsps/book/list.jsp";
        } catch (NoThatBooksException e) {
            request.setAttribute("msg",e);
            return "f:/jsps/msg.jsp";
        }
    }

    // TODO: 2018/1/11 依据bid查找书籍，返回书籍，设置到session域中
    public String desc(HttpServletRequest request , HttpServletResponse response){
        String bid = request.getParameter("bid");
        try {
            Book book = bookService.fidBook(bid);
            request.getSession().setAttribute("book",book);
            return "s:/jsps/book/desc.jsp";
        } catch (NoThatBookException e) {
            request.setAttribute("msg",e);
            return "f:/jsps/msg.jsp";
        }
    }
}
