package mds.spring;

import mds.spring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller /* Přidána anotace Controller určující, že jde o třídu controlleru jedná se o variantu příbuznou k anotaci @RestController */
public class WebController {


    private StudentService studentService;

    @Autowired //Import služby, umožňuje používat třídu jako statickou a mít jí spuštěnou pouze jednou
    public WebController(StudentService studentService) {
        this.studentService = studentService;
    }

    /*
    Spring hledá název šablony podle textu v návratové hodnotě metody.
    */
    @GetMapping("/static_page")
    public String staticPage() {
        return "staticpage";
    }

    /*
    Oproti předchozí metodě je zde navíc vytvořen objekt model, který z šablony dělá dynamickou stránku, která
    svůj obsah dokáže měnit v průběhu času.
    Neplést s JavaScriptem! Obsah je měněn na straně serveru a není zde žádné obnovování na straně klienta.
    Je tedy nutné obnovit stránku ručně.
     */
    @GetMapping("/dynamic_page")
    public String dynamicPage(Model model) {
        model.addAttribute("name", "Petr Číka");
        model.addAttribute("id", "10719");
        return "dynamicpage";
    }


    /*
        Řešení předchozího cvičení s použitím šablony a Thymeleaf a modelových proměnných.
     */
    @GetMapping("/students")
    public String students(Model model, @RequestParam(required = false) Integer id) {
        model.addAttribute("id", id);
        model.addAttribute("students", studentService.getStudents(id));
        return "students";
    }




    //--------------- Řešení samostatné práce ---------------//

    @GetMapping("/myself")
    public String myself(Model model) {
        return "myself";
    }

    @GetMapping("/alice")
    public String alice(Model model) {
        model.addAttribute("name", "Alice");
        return "template";
    }

    @GetMapping("/bob")
    public String bob(Model model) {
        model.addAttribute("name", "Bob");
        return "template";
    }

    //--------------- Řešení samostatné práce ---------------//

}
