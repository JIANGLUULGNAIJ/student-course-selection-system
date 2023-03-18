package pers.jl.web.servlet.student;

import pers.jl.dao.StudentDao;
import pers.jl.pojo.Student;
import pers.jl.service.StudentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentlistServlet", value = "/student")
public class StudentlistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Student> list = StudentDao.SelectAll();
            System.out.println("list"+list);
            request.setAttribute("Studentlist",list);
            System.out.println("get1"+request.getAttribute("Studentlist"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //查询当前学生的信息
        request.getRequestDispatcher("page/student/list.jsp").forward(request,response);


    }
}
