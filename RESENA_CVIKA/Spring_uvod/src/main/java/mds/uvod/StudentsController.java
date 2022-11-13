package mds.uvod;

import mds.uvod.model.Student;
import mds.uvod.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class StudentsController {


    private StudentService studentService;

    @Autowired //Import služby, umožňuje používat třídu jako statickou a mít jí spuštěnou pouze jednou
    public StudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    /*
        Jednoduchá metoda se správným nastavením výchozích hodnot v @RequestParam
        http://localhost:8080/student -> Vypíše se studentovo jméno a ID
        http://localhost:8080/student?name=Petr_Cika&id=10719 -> vypíše se zadané jméno a id
    */
    @GetMapping("student")
    public String student(@RequestParam(defaultValue = "David_Kohout") String name,
                          @RequestParam(defaultValue = "195823") String id) {
        return String.format("Student: <b>%s</b> ID: <b>%s</b>", name, id);
    }


    /*
    Jednoduchá metoda se správným nastavením výchozích hodnot v @RequestParam
    http://localhost:8080/students -> výpis celého listu
    http://localhost:8080/students?vutid=10719 -> pouze jeden výsledek
    http://localhost:8080/students?vutid=9999 -> nikdo nenalezen
    Konstruktor pro objekt 'Student' je v 'model/Student'
    List s daty je vložen v samostatné třídě 'data/StudentRepository'
    */
    @GetMapping("students")
    public String students(@RequestParam(defaultValue = "-1") int vutid) {
        String page = ""; //String pro ukláání hodnot pře vrácením do prohlížeče

        if (vutid == -1) { //Kontrola, zda došlo k zadání ID
            for (Student s : studentService.getStudents()) { //foreach pro vypsání všech studentů. 's' je proměnná typu 'Student'; metoda getStudents() je v 'StudentRepository'
                page += s.toString() + "<br>"; //<br> pro odřádkování v HTML; Overload 'toString()' v metodě 'Student'
            }
        } else { //else vyhledání studenta podle ID

            //vyhledání shody podle pole ID v objektu Student
            Student student = studentService.findStudent(vutid);

            //Pokud je null, nikdo nebyl nalezen
            if (student == null) {
                page = "<b>Nikdo nebyl nalezen!</b>";
            } else {
                page = student.toString();
            }
        }

        return page; //Navrácení nalezených hodnot
    }
}


//--------------------------------------------Jiné řešení---------------------------------------------------------

/*
//  1) Vytvořit konstruktor 'Student'(v samostatné metodě 'Student')

        package com.example.cv3_test_prep;
        public class Student {

         //At first one need to declare a variables
            String surname, name;
            int id, year;


        //Then, if there's need the Constructor is declared
             Student(String surname, String name, int id, int year){
             this.surname = surname;
             this.name = name;
             this.id = id;
             this.year = year;}

        @Override
        public String toString(){
            return String.format("<br>Name: %s <br>Surname: %s <br>ID:%s<br>Year:%s", name,surname,id, year);}}




    //  2)Vytvořit List do, kterého se nahází hodnoty. Ten List akceptuje 'záznamy' typu 'Student'
    public static List<Student> students = new CopyOnWriteArrayList<>(); //CopyOnWriteArrayList - thread safe metoda Arraylistu

    { //Složené závorky jsou nutné pro to aby to fungovalo -> vytovří se tím 'static list'

            students.add(new Student("Blažek", "Jan", 200666, 2001));
            students.add(new Student("Kubiš", "Adam", 213431, 1998));
            students.add(new Student("Moravec", "Vojtěch", 219263, 1999));
            students.add(new Student("Nakládalová", "Kateřina", 220713, 1999));
            students.add(new Student("Kurmanova", "Aidana", 227247, 2002));
            students.add(new Student("Rezek", "Jan", 227374, 2001));
            students.add(new Student("Tuček", "Adam", 227855, 2001));
            students.add(new Student("Písek", "Miroslav", 229096, 2001));
            students.add(new Student("Leitmann", "Peter", 230110, 2000));
            students.add(new Student("Fabík", "Václav", 230548, 2001));
            students.add(new Student("Mačina", "Martin", 230620, 2000));
            students.add(new Student("Misskii", "Anton", 230622, 2001));
            students.add(new Student("Rosa", "Martin", 230648, 2001));
            students.add(new Student("Ramiš", "Karel", 231110, 2001));
            students.add(new Student("Dolák", "Martin", 231232, 2000));
            students.add(new Student("Kolář", "Ondřej", 231245, 2001));
            students.add(new Student("Maczkó", "Adam", 231252, 2000));
            students.add(new Student("Marek", "Vojtěch", 231253, 1999));
            students.add(new Student("Nagy", "Tomáš", 231258, 2000));
            students.add(new Student("Nečas", "Martin", 231259, 1999));
            students.add(new Student("Olenočin", "Štefan", 231262, 2000));
            students.add(new Student("Ruman", "Róbert", 231273, 2001));
            students.add(new Student("Sladký", "Martin", 231280, 2000));
            students.add(new Student("Szymutko", "Marek", 231285, 2000));
            students.add(new Student("Kohout", "David", 195823, 1996));
            students.add(new Student("Číka", "Petr", 10, 1982));
            students.add(new Student("Masaryk", "Tomáš", 123456, 1850));
            }




    @RequestMapping("students")
    public String students(@RequestParam(defaultValue = "-1") int id) {
        String page = "";
        if (id == -1) { //No specific ID was given as a parameter --> complete list is printed out
            for (Student s : students) {
                page += s.toString();
            }
        } else if (id != -1) {
            for (Student s : students) { //U for:each cyklu je nutné u té 'první' promměné uvést o jaký jde typ v tomto případě jde o typ 'Student'
                if (s.id == id) {
                    page = s.toString();
                    return page;}
                else{
                    page = "No record";
                }

            }}


        return page;}*/

