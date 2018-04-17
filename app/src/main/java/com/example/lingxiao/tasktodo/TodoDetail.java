package com.example.lingxiao.tasktodo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.lingxiao.tasktodo.Utils.DateUtils;
import com.example.lingxiao.tasktodo.models.Todo;

import java.util.Calendar;
import java.util.Date;

public class TodoDetail extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    public static final String KEY_TODO = "todo";


    private EditText todoEdit;
    private TextView dateTv;
    private TextView timeTv;
    private CheckBox completeCb;

    private Todo todo;
    private Date remindDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);
        setupUI();
    }

    //back button
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //back button ui
    private void setupActionBar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);
        setTitle(null);
    }

    private void setupUI(){
        setupActionBar();

        todoEdit = (EditText) findViewById(R.id.todo_detail_todo_edit);
        dateTv = (TextView) findViewById(R.id.todo_detail_date);
        timeTv = (TextView) findViewById(R.id.todo_detail_time);
        completeCb = (CheckBox) findViewById(R.id.todo_detail_complete);

        // check todo view
        if (todo != null) {
            todoEdit.setText(todo.text);
            completeCb.setChecked(todo.done);
            findViewById(R.id.todo_detail_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //delete();
                }
            });
        } else {
            findViewById(R.id.todo_detail_delete).setVisibility(View.GONE);

        }

        if (remindDate != null) {
            dateTv.setText(DateUtils.dateToStringDate(remindDate));
            timeTv.setText(DateUtils.dateToStringTime(remindDate));
        } else {
            dateTv.setText("set date");
            timeTv.setText("set time");
        }

        setupDatePicker();
        setupCheckBox();
        setupSaveButton();


    }



    //pick date dialog
    private void setupDatePicker() {
        dateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get time to display
                Calendar c = getCalendarFromRemindDate();
                Dialog dialog = new DatePickerDialog(TodoDetail.this,
                        (DatePickerDialog.OnDateSetListener) TodoDetail.this, c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
        timeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = getCalendarFromRemindDate();
                Dialog dialog = new TimePickerDialog(TodoDetail.this,
                        (TimePickerDialog.OnTimeSetListener) TodoDetail.this,
                        c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true);
                dialog.show();
            }
        });
    }

    //checkBox
    private void setupCheckBox() {
    }

    //setup SaveButton
    private void setupSaveButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.todo_detail_done);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAndExit();
            }
        });
    }

    private void saveAndExit() {
        if (todo == null){
            todo = new Todo(todoEdit.getText().toString(), remindDate);
            todo.done = completeCb.isChecked();
        }else{
            todo.text = todoEdit.getText().toString();
            todo.remindDate = remindDate;
            todo.done = completeCb.isChecked();
        }

        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_TODO, todo);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private Calendar getCalendarFromRemindDate() {
        //get current time
        Calendar c = Calendar.getInstance();
        if (remindDate != null) {
            c.setTime(remindDate);
        }
        return c;
    }


    // DatePickerDialog.OnDateSetListener Interface get date from dialog
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c = getCalendarFromRemindDate();
        c.set(i, i1, i2);

        remindDate = c.getTime();
        dateTv.setText(DateUtils.dateToStringDate(remindDate));


    }
    // TimePickerDialog.OnTimeSetListener Interface get time from dialog
    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        Calendar c = getCalendarFromRemindDate();
        c.set(Calendar.HOUR_OF_DAY,i);
        c.set(Calendar.MINUTE, i1);

        remindDate = c.getTime();
        timeTv.setText(DateUtils.dateToStringTime(remindDate));

    }
}
