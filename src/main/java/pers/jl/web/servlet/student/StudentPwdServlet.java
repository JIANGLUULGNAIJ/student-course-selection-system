package pers.jl.web.servlet.student;

import com.alibaba.fastjson.JSONObject;
import pers.jl.service.StudentService;
import pers.jl.utils.Util;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static javax.swing.text.html.CSS.getAttribute;

@WebServlet(name = "PWDServlet", value = "/pwd")
public class StudentPwdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String prepwd = request.getParameter("prepwd");//拿到存入pwd.jsp中输入的原密码(pwd中/未加密)
        String repwd = (String) session.getAttribute("repwd");//拿到存入request中的真实密码(login中/已加密)
        String newpwd = request.getParameter("newpwd");//修改的密码1
        String newpwd2 = request.getParameter("newpwd2");//修改的密码2
        Integer id = (Integer) session.getAttribute("id");//修改的密码2


        //对prepwd进行加密
        String p1 = Util.encrypByMd5(prepwd);
        String p = Util.encrypByMd5(p1);
        if (p.equals(repwd)) {//用户原密码正确
            if (newpwd.equals(newpwd2)) {//两次新密码正确
                //调修改密码的方法
                boolean update = false;
                try {
                    update = StudentService.update(newpwd, id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (update) {//修改成功跳转登录界面
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {//两次新密码错误
                //提示新密码不一致
                session.setAttribute("msg","新密码不一致！");
                response.sendRedirect("pwd.jsp");
            }
        } else {//用户原密码错误
            //提示原密码错误
            session.setAttribute("msg","原密码错误！");
            response.sendRedirect("pwd.jsp");
        }


    }
}
