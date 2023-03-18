package pers.jl.web.servlet.student;

import pers.jl.dao.StudentDao;
import pers.jl.pojo.Student;
import pers.jl.service.StudentService;
import pers.jl.utils.Util;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet",value = "/login")
public class StudentLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("userName");
        String password = request.getParameter("password");
        String p = Util.encrypByMd5(password);
        String type = request.getParameter("type");
        HttpSession session = request.getSession();//拿到session对象
        Student student = null;
        try {
             student = StudentService.login(name,p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (student.getStuName()!=null){//登录成功
            session.setAttribute("id",student.getStuId());
            session.setAttribute("user",student);
            session.setAttribute("type",type);
            session.setAttribute("repwd",p);//真实加密密码
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }else {
            request.setAttribute("error","登录失败！");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }
}
