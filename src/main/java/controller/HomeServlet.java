package controller;

import model.Classroom;
import model.Student;
import service.ClassServiceImpl;
import service.IClassService;
import service.IStudentService;
import service.StudentServiceIpml;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    IClassService classService = new ClassServiceImpl();
    IStudentService studentService = new StudentServiceIpml();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":{
                showCreateForm(request,response);
            }
            default: {
                try {
                    showList(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("student/create.jsp");
        requestDispatcher.forward(request,response);
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Classroom> classrooms = classService.findAll();
        request.setAttribute("classes",classrooms);
        List<Student> students = studentService.findAll();
        String name= request.getParameter("name");
        if(name != null){
            students = studentService.findByName("%" + name + "%");
        }
        request.setAttribute("students", students);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
