package mds.spring.data;

import mds.spring.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/*
    Zde by se běžně přistupovalo do databáze. V našem případě je zde nadefinovaný statický list.
 */
@Repository
public class StudentRepository {

    //Definice statického listu
    private static List<Student> students = new CopyOnWriteArrayList<>();

    {   //Inicializace statického listu. Takto využité závorky bez vazby na jakoukoliv metodu či proměnou,
        //lze provést jakýkoliv kód při prvním použití této třídy. Tzv. Static Code Block.

        //Jedná se převážně o náhodně vygenerovaná jména
        students.add(new Student("Číka","Petr",10719,2022));
        students.add(new Student("Kohout","David",195823,1996));
        students.add(new Student("Masaryk","Tomáš",123456,1850));
        students.add(new Student("Zrno","Tomáš",183666,2001));
        students.add(new Student("Roman","Miroslav",199128,1999));
        students.add(new Student("Vichrová","Danuše",209507,1993));
        students.add(new Student("Matějovský","Jan",207972,1994));
        students.add(new Student("Fajt","Jiří",214737,1993));
        students.add(new Student("Nonfried","Petr",218833,1981));
        students.add(new Student("Vlček","Štěpán",177710,1982));
        students.add(new Student("Rozsypálková","Nikola",166398,1998));
        students.add(new Student("Kunčar","Hynek",168159,1992));
        students.add(new Student("Horváthová","Eva",166351,1986));
        students.add(new Student("Downey Jr.","Robert",202019,1965));
        students.add(new Student("Vacková","Hana",196837,2008));
        students.add(new Student("Říhová","Lenka",165902,2005));
        students.add(new Student("Broučková","Yelyzaveta",171821,1987));
        students.add(new Student("Orava","Antonín",190532,2005));
        students.add(new Student("Ambros","Josef",188746,1992));
        students.add(new Student("Žinčáková","Jana",221132,2000));
        students.add(new Student("Hladká","Lucie",184738,1995));
        students.add(new Student("Puchalková","Bohdana",183523,1992));
        students.add(new Student("Kebortová","Božena",220451,1988));
        students.add(new Student("Fialová","Marie",202605,2005));
    }

    //Získání listu studentů
    public List<Student> getStudents(){
        return students;
    }
}
