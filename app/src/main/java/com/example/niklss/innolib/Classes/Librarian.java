package com.example.niklss.innolib.Classes;



public class Librarian extends UserCard{

    public Librarian(String name, String secondName, String adress, int id, String num, int isLib) {
        super(name, secondName, adress, id, num, isLib);
    }

    public Librarian(String[] a){
        super(a);
    }


}
