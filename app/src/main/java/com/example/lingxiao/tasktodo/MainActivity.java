package com.example.lingxiao.tasktodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lingxiao.tasktodo.Utils.DateUtils;
import com.example.lingxiao.tasktodo.models.Todo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Todo> todos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fakeData();
        setupUI();

    }


    private void fakeData() {
        todos = new ArrayList<>();
        for (int i = 0; i < 1000; ++i) {
            Todo td = new Todo("task" + i, DateUtils.stringToDate("2018 3 25 11:00"));
            todos.add(td);
        }
    }

    private void setupUI() {
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(new TodoAdapter(this, todos));
    }


}
