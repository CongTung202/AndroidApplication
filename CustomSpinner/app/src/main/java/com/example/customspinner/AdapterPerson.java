package com.example.customspinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.customspinner.model.Person;

import java.util.List;

public class AdapterPerson extends ArrayAdapter<Person> {

    public AdapterPerson(Context context, List<Person> personList) {
        super(context, 0, personList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createDropdownView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView textView = convertView.findViewById(R.id.textView);

        Person person = getItem(position);

        if (person != null) {
            textView.setText(person.getName());
        }

        return convertView;
    }

    private View createDropdownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_drop, parent, false);
        }

        TextView nameTextView = convertView.findViewById(R.id.nameTextView);
        TextView ageTextView = convertView.findViewById(R.id.ageTextView);

        Person person = getItem(position);

        if (person != null) {
            nameTextView.setText(person.getName());
            ageTextView.setText("Age: " + person.getAge());
        }

        return convertView;
    }
}