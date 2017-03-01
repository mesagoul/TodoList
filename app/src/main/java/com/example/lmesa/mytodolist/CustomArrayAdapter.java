package com.example.lmesa.mytodolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

/**
 * Created by lmesa on 01/03/2017.
 */
public class CustomArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;
    private Gson gson;
    public CustomArrayAdapter(Context context, String[] values) {
        super(context, R.layout.adapter_listview, values);
        this.context = context;
        this.gson = new Gson();
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adapter_listview, parent, false);
        Todo maTodo =  gson.fromJson(values[position], Todo.class);
        TextView textView = (TextView) rowView.findViewById(R.id.totoTextView);
        CheckBox check  = (CheckBox) rowView.findViewById(R.id.myCheckbox);

        textView.setText(maTodo.getText());
        check.setChecked(maTodo.getChecked());

        return rowView;
    }
}
