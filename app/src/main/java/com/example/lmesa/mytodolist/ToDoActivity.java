package com.example.lmesa.mytodolist;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lmesa on 01/03/2017.
 */
public class ToDoActivity extends Activity{

    private static final int ToDoActivityResultCode = 1;
    public static final String PREFS_NAME = "MyPrefsFile";



    private Button btnViderList ;
    private Button btnAjouterListe;
    private ListView myListView;
    private CheckBox check;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitty_task_layout);

        myListView = (ListView) findViewById(R.id.myListView);
        btnAjouterListe = (Button)findViewById(R.id.myButtonAjouter);
        btnViderList = (Button) findViewById(R.id.myButtonViderList);
        check = (CheckBox) findViewById(R.id.myCheckbox);


        refresh();


        btnViderList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // init SharedPreferences
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                Set<String> set = new HashSet<String>(settings.getStringSet("Text", new HashSet<String>()));

                set.clear();
                editor.putStringSet("Text", set);
                editor.commit();
                refresh();
            }
        });

        btnAjouterListe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent_add__task = new Intent(getApplicationContext(),ToDoAddActivity.class);
                startActivityForResult(intent_add__task, ToDoActivityResultCode );
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1)
        {
            if(resultCode == RESULT_OK) {
                refresh();
            }
        }
    }

    private void refresh() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        Set<String> set = settings.getStringSet("Text", new HashSet<String>());
        myListView.setAdapter(new CustomArrayAdapter(getApplicationContext(), set.toArray(new String[set.size()])));
    }


}
