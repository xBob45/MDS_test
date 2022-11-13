package mds.spring.model;

/*
    Třída pro vytváření studentů. Model, který by se v produkci běžně získával z databáze.
    Tento model by tedy odpovídal jedné tabulce v DB.
 */
public class Student {

    private String surname;
    private String name;

    private int id;
    private int year;


    //konstruktor
    public Student(String surname, String name, int id, int year) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.year = year;
    }

    //Gettery pro přístup k hodnotám

    public String getSurname() {
        return surname;
    }
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public int getYear() {
        return year;
    }
}
