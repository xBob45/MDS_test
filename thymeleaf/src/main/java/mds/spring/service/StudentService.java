package mds.spring.service;

import mds.spring.model.Student;
import mds.spring.data.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
    Oblužná třída pro práci s modelem student. Filtrování, získání studentů atd.
    Běžně by se jednalo o API,
 */
@Service
public class StudentService {

    private StudentRepository studentRepository;

    /*
        Načtení/inject student repository pro použití v této třídě
     */
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /*
        Získání listu všech studentů, jednoho, nebo prázdného listu v případdě, že student neexistuje.
     */
    public List<Student> getStudents(Integer id) {
        if(id == null)
                return studentRepository.getStudents();
        else {
            Student s = findStudent(id.intValue());
            return (s == null ? new ArrayList<>() : List.of(s)); // prázdný list, když je student null,
            //jinak list s jedním studentem, kterého ID jsme hledali.
        }
    }

    /*
        Nalezení studenta s dadným ID.
     */
    public Student findStudent(int id) {
        Optional<Student> result = studentRepository.getStudents().stream().filter(item -> item.getId() == id).findFirst();
        if (result.isEmpty()) return null;
        else return result.get();
    }
}
