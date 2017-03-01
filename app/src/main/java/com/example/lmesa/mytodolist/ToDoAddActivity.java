package com.example.lmesa.mytodolist;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lmesa on 01/03/2017.
 */
public class ToDoAddActivity extends Activity {
    public static final String PREFS_NAME = "MyPrefsFile";







    private EditText myEditText ;
    private Button btnValider ;
    private CheckBox check ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task_layout);

        btnValider = (Button) findViewById(R.id.myButtonValider);
        check = (CheckBox) findViewById(R.id.myCheckbox);
        myEditText = (EditText) findViewById(R.id.myEditText);

        btnValider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // init SharedPreferences
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                Set<String> set = new HashSet<String>(settings.getStringSet("Text", new HashSet<String>()));

                // on serialise
                Todo monTodo = new Todo(String.valueOf(myEditText.getText()), check.isChecked());
                Gson gson = new Gson();
                String json = gson.toJson(monTodo);

                set.add(json);
                editor.putStringSet("Text",set);
                editor.commit();
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
