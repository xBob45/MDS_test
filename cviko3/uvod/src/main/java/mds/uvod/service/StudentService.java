package mds.uvod.service;

import mds.uvod.model.Student;
import mds.uvod.data.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.getStudents();
    }

    public Student findStudent(int id) {
        Optional<Student> result = studentRepository.getStudents().stream().filter(item -> item.getId() == id).findFirst();
        if (result.isEmpty()) return null;
        else return result.get();
    }
}
