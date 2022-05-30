package controller;

import model.Classroom;
import model.Student;
import service.IStudentService;
import service.StudentServiceIpml;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "/Students")
public class StudentServlet extends HttpServlet {
    IStudentService studentService = new StudentServiceIpml();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default: {
                try {
                    showList(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Student> students = studentService.findAll();
        request.setAttribute("st", students);
        request.getRequestDispatcher("student/list.jsp").forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
