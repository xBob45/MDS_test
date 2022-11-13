package com.example.cv3_test_prep;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class StudentsController {

    @GetMapping("name") //http://localhost:8080/name
    public String name(){
        return "<br>My personal site</br><br>Bye</br>";} //<br> = new line

    @GetMapping("rqv_param")
    public String request_param(@RequestParam(defaultValue = "Allmighty") String name){
        return String.format("<b>My name is %s so watch me -_-", name);
        //http://localhost:8080/rqv_param       <-- DEFAULT
        //http://localhost:8080/rqv_param?name=Vojta    <--IF PARAMETER IS SET
    }

    @GetMapping("list")
    public List<String> list(){
        return List.of("Practise", "you", "must", "young padavan"); //List is printed in JSON form
    }

    @RequestMapping("param_list")
    public String param_list(@RequestParam List<String> names){
        String output = "";
        for (String name : names){
            output+="<br>"+name+"</br>";}
        return output;}

    @RequestMapping(name = "rqst_mthd", method = {RequestMethod.POST, RequestMethod.GET})
    public String name_returned(@RequestParam(defaultValue = "Vojta") String name){
        return String.format("Name: %s",name);}


    @GetMapping("student")
    public String student(@RequestParam(defaultValue = "Vojtech Moravec") String name, @RequestParam(defaultValue = "219263") int id) {
        return String.format("<br>Name: %s <br>ID: %s", name, id);
    } //<br> to make a new line  , http://localhost:8080/student?name=Jarda&id=234556



    //  1) Vytvořit konstruktor 'Student'(v samostatné metodě 'Student')

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


    return page;}


}