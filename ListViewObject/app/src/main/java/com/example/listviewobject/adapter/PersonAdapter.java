package com.example.listviewobject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.listviewobject.R;
import com.example.listviewobject.model.Person;

import java.util.List;

public class PersonAdapter extends ArrayAdapter<Person> {

    public PersonAdapter(@NonNull Context context, int resource, @NonNull List<Person> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.list_item_person, parent, false);
        }

        Person person = getItem(position);

        if (person != null) {
            ImageView ivGender = v.findViewById(R.id.ivGender);
            TextView tvName = v.findViewById(R.id.tvName);
            TextView tvDetails = v.findViewById(R.id.tvDetails);

            tvName.setText(person.getName());
            String details = "ID: " + person.getId() + " - Age: " + person.getAge();
            tvDetails.setText(details);

            if (person.getGender().equalsIgnoreCase("Nam")) {
                ivGender.setImageResource(R.drawable.ic_male);
            } else if (person.getGender().equalsIgnoreCase("Nữ")) {
                ivGender.setImageResource(R.drawable.ic_female);
            } else {
                ivGender.setImageResource(R.drawable.ic_other);
            }
        }

        return v;
    }
}
