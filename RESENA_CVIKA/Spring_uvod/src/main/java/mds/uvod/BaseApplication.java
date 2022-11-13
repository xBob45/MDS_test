package mds.uvod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller //-- Základní controller, jen ukázka na úvod --//
public class BaseApplication {
    //Startovací třídu není vhodné takto obalovat zbytečným kódem, proto zbytek kóddu je v samostatné třídě.
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

    /*
        Anotace @GetMapping říká springu, aby použil metodu hello() k zodpovězení dotazů na adresu
        http://localhost:8080

        Anotace @ResponseBody říká, že se jedná o obsah, co chceme zobrazit. Výsledek tedy není zpracován jako šablona
     */
    @GetMapping
    @ResponseBody
    public String hello() {
        return "Hello world!";
    }
}
