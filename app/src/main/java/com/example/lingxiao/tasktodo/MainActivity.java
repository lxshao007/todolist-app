package com.example.lingxiao.tasktodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
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
        LinearLayout linearLayout = findViewById(R.id.todo_list);
        for (Todo todo : todos) {
            View view = getListItemView(todo);
            linearLayout.addView(view);
        }
    }

    private View getListItemView(Todo todo) {
        View view = getLayoutInflater().inflate(R.layout.list_item, null);;

        ((TextView) view.findViewById(R.id.list_item_text)).setText(todo.text);

        return view;

    }
}
