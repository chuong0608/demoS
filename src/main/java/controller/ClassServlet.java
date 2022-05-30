package controller;

import model.Classroom;
import service.ClassServiceImpl;
import service.IClassService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ClassServlet", value = "/Classes")
public class ClassServlet extends HttpServlet {
    IClassService classService = new ClassServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            default:{
                try {
                    showList(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Classroom> classrooms = classService.findAll();
        request.setAttribute("ds",classrooms);
        request.getRequestDispatcher("class/list.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
