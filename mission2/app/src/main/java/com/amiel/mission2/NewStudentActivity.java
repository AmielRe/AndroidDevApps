package com.amiel.mission2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NewStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);



        TextView nameTv = findViewById(R.id.newstudent_name);
        TextView idTv = findViewById(R.id.newstudent_id);
        TextView phoneTv = findViewById(R.id.newstudent_phone);
        TextView addressTv = findViewById(R.id.newstudent_address);
        CheckBox checkBox = findViewById(R.id.newstudent_checkbox);



        Button saveBtn = findViewById(R.id.newstudent_saveBtn);
        Button cancelBtn = findViewById(R.id.newstudent_cancelBtn);

        saveBtn.setOnClickListener(view -> {
            Student student = new Student(nameTv.getText().toString(), idTv.getText().toString(), "",
                    checkBox.isChecked(),phoneTv.getText().toString(),addressTv.getText().toString());
            Model.instance().data.add(student);
            finish();
        });

        cancelBtn.setOnClickListener(view -> {
            finish();
        });

        //need to fix in next missions
        ImageView imageView = findViewById(R.id.newstudent_image);
        imageView.setImageResource(R.drawable.avatar);
    }


}