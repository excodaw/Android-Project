package com.example.githubproject;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddRecordDialog extends AlertDialog {
    protected AddRecordDialog(Context context) {
        super(context, androidx.appcompat.R.style.Theme_AppCompat_DayNight_Dialog);
    }

    MyDBHelper mydbHelper;
    Button record_save;
    EditText record_id;
    Spinner type_spins;
    InputMethodManager imm;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_record);



        record_save = findViewById(R.id.record_save);
        type_spins = findViewById(R.id.spinner_types);
        record_id = findViewById(R.id.record_id);
        mydbHelper = new MyDBHelper(getContext(), 1);
        ArrayAdapter typeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.result_names, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_spins.setAdapter(typeAdapter);

        imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

        record_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm.showSoftInput(record_id, InputMethodManager.SHOW_FORCED);
            }
        });

        record_save.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(record_id.length() == 0) {
                }
                else if(record_id.length() >= 4) {
                    Toast.makeText(getContext(), "설마... 아니죠?", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getContext(), type_spins.getSelectedItem().toString() + " 기록 완료", Toast.LENGTH_LONG).show();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
                    String currentDate = simpleDateFormat.format(new Date());
                    mydbHelper.insert(Integer.parseInt(record_id.getText().toString()), type_spins.getSelectedItem().toString(), currentDate);
                    imm.hideSoftInputFromWindow(record_save.getWindowToken(), 0);
                    dismiss();
                }

            }
        });
    }
}
