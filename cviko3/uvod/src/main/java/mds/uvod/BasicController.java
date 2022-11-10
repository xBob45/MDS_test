package mds.uvod;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //říká springu, že tento kód je koncovým bodem, který by měl být zpřístupněn přes web.
@RequestMapping("basic") //Nastavení cesty v URL. Všechny metoy v této třídě budou v cestě http://localhost:8080/basic/
public class BasicController {

    /*
        Záklaní stránka po přístupu na http://localhost:8080/basic
        Tučně se zde vypíše Hello Wolrd!
     */
    @GetMapping
    public String testBasic() {
        return "<b>Hello world!</b>";
    }


    /*
        Ukázka, že je možné vracet i pole hodnot. Výsledek je poté jako JSON
        http://localhost:8080/basic/list zobrazí výpis hodnot
     */
    @GetMapping("list")
    public List<String> testList() {
        return List.of("Hello", "world", "in", "JSON");
    }


    /*
        List hodnot, kde musíme vložit vlastní parametr přímo v URL, jinak je výsledkem chyba
        http://localhost:8080/basic/test1 -> Error
        http://localhost:8080/basic/test1?name=David -> Hello David! Welcome to our page.
     */
    @GetMapping("test1")
    public String testParam1(@RequestParam String name) {
        return String.format("Hello %s! Welcome to our page.", name);
    }


    /*
        Vyřešení problému z předchozího testu, nastavení výchozí honoty, když nebude zadaná
        http://localhost:8080/basic/test2 -> Hello user! Welcome to our page.
        http://localhost:8080/basic/test2?name=David -> Hello David! Welcome to our page.
     */
    @GetMapping("test2")
    public String testParam2(@RequestParam(defaultValue = "user") String name) {
        return String.format("Hello %s! Welcome to our page.", name);
    }


    /*
        Ukázka, že je možné mít jiný název proměné v kódu a jiný v URL
        http://localhost:8080/basic/test3 -> ve výsledku bude hodnota user
        http://localhost:8080/basic/test3?name=David -> ve bude výchozí hodnota user
        http://localhost:8080/basic/test3?jmeno=David -> ve výledku bude hodnota David
     */
    @GetMapping("test3")
    public String testParam3(@RequestParam(name = "jmeno", defaultValue = "user") String name) {
        return String.format("Hello %s! Welcome to our page.", name);
    }



    /*
        Ukázka, jak je možné vložit více parametrů do URL.
        http://localhost:8080/basic/test4?name=David&id=50 -> ve výsledku David a id 50
     */
    @GetMapping("test4")
    public String testParam4(@RequestParam(defaultValue = "user") String name,
                             @RequestParam(defaultValue = "-1") int id) {
        return String.format("Hello %s! Welcome to our page. Your ID is: %d", name, id);
    }



    /*
        Možnost zadávat i více proměnných do listu
        http://localhost:8080/basic/test5 -> Error
        http://localhost:8080/basic/test5?id=1 -> All IDs are: [1]
        http://localhost:8080/basic/test5?id=1,2,3,4,5 -> All IDs are: [1, 2, 3, 4, 5]
     */
    @GetMapping("test5")
    public String testParam5(@RequestParam List<String> id) {
        return "All IDs are: " + id;
    }


    /*
        Metoda shodná s test2, akorát tato metoda dokáže příjmat i POST requesty
     */
    @RequestMapping(value = "hello", method = {RequestMethod.GET, RequestMethod.POST})
    public String helloTest(@RequestParam(defaultValue = "user") String name) {
        return String.format("Hello %s! Welcome to our page.", name);
    }


    /*
        HTML formulář, který po zadání jména odešle požadavek se jménem na adresu:
        http://localhost:8080/basic/helo?name=ZadaneJmeno
     */
    @GetMapping("form")
    public String helloForm() {
        String html =
                "<html>" +
                    "<body>" +
                        "<form method='post' action='hello'>" +
                            "<input type='text' name='name'/>" +
                            "<input type='submit' value='Pozdrav!'/>" +
                        "</form>" +
                    "</body>" +
                "</html>";
        return html;
    }


}
