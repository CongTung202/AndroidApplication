package com.example.sqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sqlite.database.DatabaseHelper;
import com.example.sqlite.model.Person;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonDao extends DatabaseHelper {
    SQLiteDatabase db;
    public PersonDao(Context context) {
        super(context);
        DatabaseHelper helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }
    public List<Person> getAll() {
        Cursor cs = db.rawQuery("select * from Person", null);
        List<Person> list = new ArrayList<>();
        try {
            if (cs.moveToFirst()) {
                do {
                    Person p = new Person();
                    p.setId(cs.getInt(0));
                    p.setName(cs.getString(1));
                    Date d;
                    try {
                        d = new SimpleDateFormat("dd/MM/yyyy").parse(cs.getString(2));
                    } catch (Exception e) {
                        d = new Date();
                    }
                    p.setBirthday(d);
                    p.setGender(cs.getString(3));
                    list.add(p);
                } while (cs.moveToNext());
            }
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
        return list;
    }
    public long insert(Person p) {
        ContentValues v = new ContentValues();
        v.put("Name", p.getName());
        v.put("Birthday", new SimpleDateFormat("dd/MM/yyyy").format(p.getBirthday()));
        v.put("Gender", p.getGender());
        return db.insert("Person", null, v);
    }



}
