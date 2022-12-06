package com.amiel.mission2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class StudentDetailsActivity extends AppCompatActivity {
    Student student;
    int numberData;

    @Override
    public void onRestart() {
        super.onRestart();
        recreate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        // get the student number from intent extra info
        Intent intent = getIntent();
        numberData = intent.getIntExtra("name", 0);
        student=Model.instance().data.get(numberData);


        TextView nameTv = findViewById(R.id.studentdetails_name);
        TextView idTv = findViewById(R.id.studentdetails_id);
        TextView phoneTv = findViewById(R.id.studentdetails_phone);
        TextView addressTv = findViewById(R.id.studentdetails_address);
        CheckBox cb = findViewById(R.id.studentdetails_checkbox);
        Button editBtn = findViewById(R.id.studentdetails_editbtn);

        nameTv.setText("name : "+student.name);
        idTv.setText("id : "+student.id);
        phoneTv.setText("phone : "+student.phone);
        addressTv.setText("address : "+student.address);
        if(student.cb)
            cb.setChecked(true);
        else
            cb.setChecked(false);


        /*cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                student.cb=!student.cb;
            }
        });*/

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), StudentEditActivity.class);
                intent.putExtra("name",numberData);
                startActivity(intent);
            }
        });


        //need to fix for each specific image in next missions
        ImageView imageView = findViewById(R.id.studentdetails_img);
        imageView.setImageResource(R.drawable.avatar);
    }
}