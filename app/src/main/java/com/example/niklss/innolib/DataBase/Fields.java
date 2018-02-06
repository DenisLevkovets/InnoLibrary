package com.example.niklss.innolib.DataBase;

/**
 * Created by solo1 on 03.02.2018.
 */
//enumeration with fields of tables
public enum Fields {
    First_name(0),
    Last_name(1),
    available_copies(2),
    numbers(3);
    Fields(int i) {
        this.fieldCode =i;
    }
    public int getFieldCode()
    {
        return fieldCode;
    }
    private int fieldCode;
}
