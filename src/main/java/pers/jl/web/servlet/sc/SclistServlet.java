package pers.jl.web.servlet.sc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SclistServlet", value = "/scquery")
public class SclistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parameter = request.getParameter("methon");
        System.out.println(parameter);
        if ("query_range".equals(parameter)){
            request.getRequestDispatcher("page/sc/query_range.jsp").forward(request,response);
        }else if ("query_jgl".equals(parameter)){
            request.getRequestDispatcher("page/sc/query_jgl.jsp").forward(request,response);
        }



    }
}
