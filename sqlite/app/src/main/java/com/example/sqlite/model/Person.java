package com.example.sqlite.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Person implements Parcelable {
    private int id;
    private String name;
    private Date birthday;
    private String gender;

    public Person() {
    }

    public Person(int id, String name, Date birthday, String gender) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
    }

    protected Person(Parcel in) {
        id = in.readInt();
        name = in.readString();
        long tmpBirthday = in.readLong();
        birthday = tmpBirthday == -1 ? null : new Date(tmpBirthday);
        gender = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeLong(birthday != null ? birthday.getTime() : -1);
        dest.writeString(gender);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
