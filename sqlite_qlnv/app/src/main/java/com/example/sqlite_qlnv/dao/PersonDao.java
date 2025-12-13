package com.example.sqlite_qlnv.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.sqlite_qlnv.database.DatabaseHelper;
import com.example.sqlite_qlnv.model.Person;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PersonDao {
    SQLiteDatabase db;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    public PersonDao(Context context) {
        DatabaseHelper help = new DatabaseHelper(context);
        db = help.getWritableDatabase();
    }

    public List<Person> getAll() {
        List<Person> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM Person", null);
        while (c.moveToNext()) {
            Person p = new Person();
            p.setId(c.getInt(0));
            p.setName(c.getString(1));
            try {
                p.setBirthday(sdf.parse(c.getString(2)));
            } catch (ParseException e) {
                p.setBirthday(new Date());
            }
            p.setGender(c.getString(3));
            list.add(p);
        }
        c.close();
        return list;
    }

    public Person getById(int id) {
        Cursor cr = db.rawQuery("SELECT * FROM Person WHERE Id=?", new String[]{String.valueOf(id)});
        Person p = new Person();
        if (cr.moveToFirst()) {
            p.setId(cr.getInt(0));
            p.setName(cr.getString(1));
            try {
                p.setBirthday(sdf.parse(cr.getString(2)));
            } catch (ParseException e) {
                p.setBirthday(new Date());
            }
            p.setGender(cr.getString(3));
        }
        cr.close();
        return p;
    }

    public long insert(Person p) {
        ContentValues v = new ContentValues();
        v.put("Id", p.getId());
        v.put("Name", p.getName());
        v.put("Birthday", sdf.format(p.getBirthday()));
        v.put("Gender", p.getGender());
        return db.insert("Person", null, v);
    }

    public long update(Person p) {
        ContentValues v = new ContentValues();
        v.put("Name", p.getName());
        v.put("Birthday", sdf.format(p.getBirthday()));
        v.put("Gender", p.getGender());
        return db.update("Person", v, "Id=?", new String[]{String.valueOf(p.getId())});
    }

    public long delete(int id) {
        return db.delete("Person", "Id=?", new String[]{String.valueOf(id)});
    }
}