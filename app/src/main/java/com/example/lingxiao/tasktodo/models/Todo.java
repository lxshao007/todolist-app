package com.example.lingxiao.tasktodo.models;

import java.util.Date;

/**
 * Created by lingxiao on 3/25/18.
 */

public class Todo {

    public String text;
    public Date remindDate;

    public Todo(String text, Date date) {
        this.text = text;
        this.remindDate = date;
    }

}
