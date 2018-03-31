package com.example.niklss.innolib.Classes;

import java.util.ArrayList;

/**
 * Created by Niklss on 31.03.2018.
 */

public class AV {

    private String title;
    private String authors;
    private int avId;
    private int countAv;
    private UserCard user;
    private int daysLeft;

    public AV(ArrayList<String> av) {
        this.title = av.get(0);
        this.authors = av.get(1);
        this.avId = Integer.parseInt(av.get(2));
        this.countAv = Integer.parseInt(av.get(3));
    }

    public AV(String title, String authors, int avId, int countAv) {
        this.title = title;
        this.authors = authors;
        this.avId = avId;
        this.countAv = countAv;
    }

    public AV(String[] av) {
        this.title = av[0];
        this.authors = av[1];
        this.avId = Integer.parseInt(av[2]);
        this.countAv = Integer.parseInt(av[3]);
    }



    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public int getAvId() {
        return avId;
    }

    public int getCountAv() {
        return countAv;
    }

    public void setCountAv(int countAv) {
        this.countAv = countAv;
    }

    public UserCard getUser() {
        return user;
    }

    public void setUser(UserCard user) {
        this.user = user;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }
}
