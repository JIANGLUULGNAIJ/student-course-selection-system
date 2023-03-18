package pers.jl.web.servlet.admin;

import pers.jl.pojo.Admin;
import pers.jl.pojo.Student;
import pers.jl.service.AdminService;
import pers.jl.service.StudentService;
import pers.jl.utils.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet",value = "/login")
public class AdminLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("userName");
        String password = request.getParameter("password");
        String p1 = Util.encrypByMd5(password);
        String p = Util.encrypByMd5(p1);
        String type = request.getParameter("type");
        HttpSession session = request.getSession();//拿到session对象
        Admin admin = null;
        try {
             admin = AdminService.login(name,p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (admin.getName()!=null){//登录成功
            session.setAttribute("id",admin.getId());
            session.setAttribute("user",admin);
            session.setAttribute("type",type);
            session.setAttribute("repwd",p);//真实加密密码
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }else {
            request.setAttribute("error","登录失败！");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }
}
