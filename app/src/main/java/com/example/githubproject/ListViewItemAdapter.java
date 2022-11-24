package com.example.githubproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ListViewItemAdapter extends BaseAdapter {

    private ArrayList<ListViewAdapterData> item_list = new ArrayList<ListViewAdapterData>();

    public ListViewItemAdapter() {}
    @Override
    public int getCount() {
        return item_list.size();
    }

    @Override
    public Object getItem(int position) {
        return item_list.get(position);
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
            convertView = inflater.inflate(R.layout.item_listview, parent, false);
        }
        TextView workoutname = (TextView) convertView.findViewById(R.id.worktoutname);
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.routinecheck);

        ListViewAdapterData listViewItem = item_list.get(position);
        workoutname.setText(listViewItem.getWorkout_name());

        return convertView;
    }

    public void addItem(String name) {
        ListViewAdapterData item = new ListViewAdapterData();

        item.setWorkout_name(name);

        item_list.add(item);
    }
}
