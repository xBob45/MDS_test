package mds.uvod.model;

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

    @Override //Přepsaný toString pro jednodušší výpis.
    public String toString() {
        return String.format("Jméno: <b>%s</b>, Příjmení: <b>%s</b>, Rok narození: <b>%d</b>, ID: <b>%d</b>",
                             name, surname, year,id);
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
