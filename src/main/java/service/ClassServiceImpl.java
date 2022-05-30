package service;

import model.Classroom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassServiceImpl implements IClassService {
    public ClassServiceImpl() {
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
    public List<Classroom> findAll() throws SQLException {
        List<Classroom> classrooms =  new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM class")) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                classrooms.add(new Classroom(id, name));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return classrooms;

    }

    @Override
    public void add(Classroom classroom) throws SQLException {

    }

    @Override
    public Classroom findById(int id) {
        Classroom classroomz = new Classroom();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM class where id = ?")) {
            preparedStatement.setInt(1,id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int idClass = rs.getInt("id");
                String name = rs.getString("name");
                classroomz=new Classroom(idClass, name);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return classroomz;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Classroom classroom) throws SQLException {
        return false;
    }

}
