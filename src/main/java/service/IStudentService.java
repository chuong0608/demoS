package service;

import model.Student;

import java.util.List;

public interface IStudentService extends GenaraService<Student> {
    public List<Student> findByName(String key);

    public List<Student> findAllByClass(int classId);
}
