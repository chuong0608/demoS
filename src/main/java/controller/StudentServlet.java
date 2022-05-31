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

@WebServlet(name = "StudentServlet", value = "/Students")
public class StudentServlet extends HttpServlet {
    IStudentService studentService = new StudentServiceIpml();
    IClassService classService = new ClassServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                try {
                    showCreateForm(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "view":{
                try {
                    showView(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "update":{
                try {
                    showUpdateForm(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
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


    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("student/update.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        List<Classroom> classrooms = classService.findAll();
        request.setAttribute("classes", classrooms);
        Student student = studentService.findById(id);
        request.setAttribute("students", student);
        requestDispatcher.forward(request, response);

    }

    private void showView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("student/view.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        List<Classroom> classrooms = classService.findAll();
        request.setAttribute("classes", classrooms);
        Student student = studentService.findById(id);
        request.setAttribute("students", student);
        requestDispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("student/create.jsp");
        List<Classroom> classrooms = classService.findAll();
        request.setAttribute("classes", classrooms);
        requestDispatcher.forward(request, response);
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Student> students = studentService.findAll();
        request.setAttribute("st", students);
        request.getRequestDispatcher("student/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                try {
                    create(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "update":{
                try {
                    update(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "delete":{
                try {
                    delete(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentService.delete(id);
        response.sendRedirect("/home");

    }
    private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        int classId = Integer.parseInt(request.getParameter("classId"));
        Classroom claszz = classService.findById(classId);
        studentService.update(new Student(id, name, age, claszz));
        response.sendRedirect("/home");
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        int classId = Integer.parseInt(request.getParameter("classId"));
        Classroom claszz = classService.findById(classId);
        studentService.add(new Student(0, name, age, claszz));
        response.sendRedirect("/home");
    }
}
