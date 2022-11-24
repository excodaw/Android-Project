package com.example.githubproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<ListViewAdapterData> list_adapter = new ArrayList<ListViewAdapterData>();
    @Override
    public int getCount() {
        return list_adapter.size();
    }

    @Override
    public Object getItem(int position) {
        return list_adapter.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview, parent, false);
        }
        TextView exercisename = (TextView) convertView.findViewById(R.id.exercise_name);

        ListViewAdapterData listViewAdapterData = list_adapter.get(position);

        exercisename.setText(listViewAdapterData.getWorkout_name());
        return convertView;
    }
    public void plusItem(String name) {
        ListViewAdapterData list = new ListViewAdapterData();

        list.setWorkout_name(name);

        list_adapter.add(list);
    }
}
