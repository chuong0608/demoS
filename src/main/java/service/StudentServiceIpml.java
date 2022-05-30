package service;

import model.Classroom;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceIpml implements IStudentService{
    IClassService classService =new ClassServiceImpl();
    public StudentServiceIpml() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo5?useSSL=false", "root", "1234");
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public List<Student> findAll() throws SQLException {
        List<Student> students =  new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM student")) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                int classId =  rs.getInt("classId");
                Classroom classroom = classService.findById(classId);
                students.add(new Student(id, name,age,classroom));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return students;
    }

    @Override
    public void add(Student student) throws SQLException {

    }

    @Override
    public Student findById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Student student) throws SQLException {
        return false;
    }


    @Override
    public List<Student> findByName(String name) {
        List<Student> students = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from student where name like ?");) {
            System.out.println(preparedStatement);
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nameStudent = rs.getString("name");
                int age = rs.getInt("age");
                int classId =  rs.getInt("classId");
                Classroom classroom = classService.findById(classId);
                students.add(new Student(id, nameStudent,age,classroom));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return students;
    }

    @Override
    public List<Student> findAllOrderByAge() {
        return null;
    }
}
