package com.example.niklss.innolib;

/**
 * Created by Niklss on 31.01.2018.
 * Useful interface
 */

public interface LibrarianInterface {
    public void checkOverDue();
    public void managePatrons(int CardNum);
    public void addDoc();
    public void modifyDoc();
    public void deleteDoc();
}
