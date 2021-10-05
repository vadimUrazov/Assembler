package com.example.todolist;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myListView = (ListView) findViewById(R.id.listView);
        final EditText myEditText = (EditText) findViewById(R.id.editText);

        // Массив для хранения списка задач
        final ArrayList<String> todoItems = new ArrayList<String>();

        // Адаптер для привязки массива к ListView
        final ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, todoItems);

        // Связываем массив с ListView
        myListView.setAdapter(adapter);

        myEditText.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER)
                            || (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        todoItems.add(0, myEditText.getText().toString());
                        adapter.notifyDataSetChanged();
                        myEditText.setText("");
                        return true;
                    }

                return false;
            }
        });
    }
}