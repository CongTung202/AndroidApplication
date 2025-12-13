package com.example.sqlite_qlnv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.sqlite_qlnv.R;
import com.example.sqlite_qlnv.model.Person;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class PersonAdapter extends ArrayAdapter<Person> {
    public PersonAdapter(@NonNull Context context, int resource, @NonNull List<Person> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);
        }
        TextView txtId = convertView.findViewById(R.id.txtId);
        TextView txtName = convertView.findViewById(R.id.txtName);
        TextView txtBirthday = convertView.findViewById(R.id.txtBirthday);
        TextView txtGender = convertView.findViewById(R.id.txtGender);

        Person p = this.getItem(position);
        if (p != null) {
            txtId.setText(String.valueOf(p.getId()));
            txtName.setText(p.getName());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            txtBirthday.setText(sdf.format(p.getBirthday()));
            txtGender.setText(p.getGender());
        }
        return convertView;
    }
}