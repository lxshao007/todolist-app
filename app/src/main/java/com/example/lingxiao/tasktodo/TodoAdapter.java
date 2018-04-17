package com.example.lingxiao.tasktodo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lingxiao.tasktodo.models.Todo;

import java.util.List;

/**
 * Created by lingxiao on 3/25/18.
 */

public class TodoAdapter extends BaseAdapter {

    private MainActivity activity;
    private List<Todo> data;

    public TodoAdapter(MainActivity activity, List<Todo> data) {
        this.activity = activity;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    static class ViewHolder
    {
        public TextView text;

    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = activity.getLayoutInflater().inflate(R.layout.list_item, viewGroup, false);
            vh.text = (TextView) convertView.findViewById(R.id.list_item_text);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        final Todo todo = data.get(i);
        vh.text.setText(todo.text);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, TodoDetail.class);
                intent.putExtra(TodoDetail.KEY_TODO, todo);
                activity.startActivityForResult(intent, MainActivity.REQ_CODE_TODO_DETAIL);
            }
        });

        return convertView;
    }
}
