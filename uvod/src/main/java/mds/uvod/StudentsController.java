package mds.uvod;

import mds.uvod.model.Student;
import mds.uvod.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    */
    @GetMapping("students")
    public String students(@RequestParam(defaultValue = "-1") int vutid) {
        String page = ""; //String pro ukláání hodnot pře vrácením do prohlížeče

        if (vutid == -1) { //Kontrola, zda došlo k zadání ID
            for (Student s : studentService.getStudents()) { //foreach pro vypsání všech studentů
                page += s.toString() + "<br>"; //<br> pro odřádkování v HTML
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
