package com.example.githubproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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

    public boolean isChecked(int position){return item_list.get(position).checked;}
    public boolean isChecked_false(int position){return item_list.get(position).checked=false;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_listview, parent, false);
        }
        TextView workoutname = (TextView) convertView.findViewById(R.id.worktoutname);
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.routinecheck);
        checkBox.setChecked(item_list.get(position).checked);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean newState= !item_list.get(position).isChecked();
                item_list.get(position).checked=newState;

                if (newState==true) {
                    DBHelper helper = new DBHelper(context.getApplicationContext(), 1);
                    SQLiteDatabase db = helper.getReadableDatabase();
                    Routine_DBHelper r_helper = new Routine_DBHelper(context.getApplicationContext(), 1);
                    r_helper.insert("루틴이름", item_list.get(position).getWorkout_name(), 0, 0, 0, 0);
                    db.close();
                } else if (newState==false) {
                    DBHelper helper = new DBHelper(context.getApplicationContext(), 1);
                    SQLiteDatabase db = helper.getReadableDatabase();
                    Routine_DBHelper r_helper = new Routine_DBHelper(context.getApplicationContext(), 1);
                    r_helper.delete2(item_list.get(position).getWorkout_name());
                    db.close();
                }
            }
        });

        ListViewAdapterData listViewItem = item_list.get(position);
        workoutname.setText(listViewItem.getWorkout_name());
        checkBox.setChecked(isChecked((position)));

        return convertView;
    }

    public void addItem(String name) {
        ListViewAdapterData item = new ListViewAdapterData();

        item.setWorkout_name(name);

        item_list.add(item);
    }

    public String getName(int position) {
        return item_list.get(position).getWorkout_name();
    }
}
