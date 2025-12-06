package com.example.listviewobject.adapter;

import android.content.Context;
import android.graphics.Color;
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

    private int selectedPosition = -1;

    public PersonAdapter(@NonNull Context context, int resource, @NonNull List<Person> objects) {
        super(context, resource, objects);
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
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
            String details = "ID: " + person.getId() + " - Ngày sinh: " + person.getDateOfBirth();
            tvDetails.setText(details);

            if (person.getGender().equalsIgnoreCase("Nam")) {
                ivGender.setImageResource(R.drawable.gender_male);
            } else if (person.getGender().equalsIgnoreCase("Nữ")) {
                ivGender.setImageResource(R.drawable.gender_female);
            } else {
                ivGender.setImageResource(R.drawable.gender_other);
            }
        }

        if (selectedPosition == position) {
            v.setBackgroundColor(Color.LTGRAY);
        } else {
            v.setBackgroundColor(Color.TRANSPARENT);
        }

        return v;
    }
}
