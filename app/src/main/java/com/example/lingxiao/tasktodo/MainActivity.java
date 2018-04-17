package com.example.lingxiao.tasktodo;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lingxiao.tasktodo.Utils.DateUtils;
import com.example.lingxiao.tasktodo.models.Todo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int REQ_CODE_TODO_DETAIL = 100;
    private List<Todo> todos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TodoDetail.class);
                startActivityForResult(intent, REQ_CODE_TODO_DETAIL);
            }
        });

        fakeData();
        setupUI();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE_TODO_DETAIL && resultCode == RESULT_OK){
            Todo todo = data.getParcelableExtra(TodoDetail.KEY_TODO);
            updateTodo(todo);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateTodo(Todo todo) {
        boolean found = false;
        for(int i = 0; i < todos.size(); ++i){
            Todo t = todos.get(i);
            if (TextUtils.equals(todo.id, t.id)){
                todos.set(i, todo);
                found = true;
                break;
            }
        }
        if (!found) {
            todos.add(todo);
        }
        setupUI();
    }

    private void deleteTodo(String todoId) {
    }


    private void fakeData() {
        todos = new ArrayList<>();
        for (int i = 0; i < 2; ++i) {
            Todo td = new Todo("task" + i, DateUtils.stringToDate("2018 3 25 11:00"));
            todos.add(td);
        }
    }

    private void setupUI() {
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(new TodoAdapter(this, todos));
    }


}
