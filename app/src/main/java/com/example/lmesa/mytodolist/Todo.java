package com.example.lmesa.mytodolist;

/**
 * Created by lmesa on 01/03/2017.
 */
public class Todo {
    private String text;
    private boolean checked;

    public Todo(String text, boolean checked){
        this.text = text;
        this.checked = checked;
    }
    public boolean getChecked(){
        return this.checked;
    }

    public String getText(){
        return this.text;
    }


}
