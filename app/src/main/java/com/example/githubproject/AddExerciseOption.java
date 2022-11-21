package com.example.githubproject;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddExerciseOption extends AppCompatActivity {

    EditText edt1, edt2, edt3, edt4, edt5;
    Button btn1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise_option);

        edt1 = findViewById(R.id.editText1);
        edt2 = findViewById(R.id.editText2);
        edt3 = findViewById(R.id.editText3);
        edt4 = findViewById(R.id.editText4);
        edt5 = findViewById(R.id.editText5);
        btn1 = findViewById(R.id.button_insert);

        String option1 = edt1.getText().toString();
        String option2 = edt2.getText().toString();
        String option3 = edt3.getText().toString();
        String option4 = edt4.getText().toString();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RoutineListDBHelper helper = new RoutineListDBHelper(this);
                SQLiteDatabase db = helper.getWritableDatabase();
                db.execSQL("inset into RoutineList.db VALUES (" +  option1 + "," +
                        option2 + "," + option3 + "," + option4 + ")");
                db.close();

            }
        });

    }
}
