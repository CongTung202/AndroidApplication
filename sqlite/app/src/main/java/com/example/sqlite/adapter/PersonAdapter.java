package com.example.sqlite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.sqlite.R;
import com.example.sqlite.model.Person;
import java.text.SimpleDateFormat;
import java.util.List;

public class PersonAdapter extends ArrayAdapter<Person> {

    public PersonAdapter(@NonNull Context context, @NonNull List<Person> objects) {
        super(context, 0, objects);
    }

    private static class ViewHolder {
        TextView txtId;
        TextView txtName;
        TextView txtBirthday;
        TextView txtGender;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_person, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txtId = convertView.findViewById(R.id.txtId);
            viewHolder.txtName = convertView.findViewById(R.id.txtName);
            viewHolder.txtBirthday = convertView.findViewById(R.id.txtBirthday);
            viewHolder.txtGender = convertView.findViewById(R.id.txtGender);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Person p = getItem(position);
        if (p != null) {
            viewHolder.txtId.setText(String.valueOf(p.getId()));
            viewHolder.txtName.setText(p.getName());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            viewHolder.txtBirthday.setText(sdf.format(p.getBirthday()));
            viewHolder.txtGender.setText(p.getGender());
        }

        return convertView;
    }
}
