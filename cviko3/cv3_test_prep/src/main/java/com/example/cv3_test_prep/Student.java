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
        return String.format("<br>Name: %s <br>Surname: %s <br>ID:%s<br>Year:%s", name,surname,id, year);
    }

}


