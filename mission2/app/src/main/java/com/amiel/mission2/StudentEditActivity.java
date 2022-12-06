package com.amiel.mission2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class StudentEditActivity extends AppCompatActivity {
    Student student;
    int numberData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_edit);

        Intent intent = getIntent();
        numberData = intent.getIntExtra("name", 0);
        student=Model.instance().data.get(numberData);

        TextView nameTv = findViewById(R.id.studentedit_name);
        TextView idTv = findViewById(R.id.studentedit_id);
        TextView phoneTv = findViewById(R.id.studentedit_phone);
        TextView addresTv = findViewById(R.id.studentedit_address);
        CheckBox cb = findViewById(R.id.studentedit_checkbox);
        Button saveBtn = findViewById(R.id.studentedit_savebtn);
        Button deleteBtn = findViewById(R.id.studentedit_deletebtn);
        Button cancelBtn = findViewById(R.id.studentedit_cancelbtn);

        nameTv.setText(student.name);
        idTv.setText(student.id);
        phoneTv.setText(student.phone);
        addresTv.setText(student.address);
        cb.setChecked(student.cb);


        //need to fix in the next missions
        ImageView imageView = findViewById(R.id.studentedit_image);
        imageView.setImageResource(R.drawable.avatar);



        saveBtn.setOnClickListener(view -> {
            student.name=nameTv.getText().toString();
            student.id=idTv.getText().toString();
            student.phone=phoneTv.getText().toString();
            student.address=addresTv.getText().toString();
            student.cb=cb.isChecked();
            Model.instance().data.set(numberData,student);
            //finish();
            Intent backMain = new Intent(getApplicationContext(),StudentRecyclerList.class);
            startActivity(backMain);
            finish();
        });

        deleteBtn.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Notice");
            builder.setMessage("Are you sure you want to delete "+student.name+" profile?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    List<Student> list =Model.instance().getAllStudents();
                    list.remove(numberData);
                    Intent backMain = new Intent(getApplicationContext(),StudentRecyclerList.class);
                    backMain.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(backMain);
                }
            });
            builder.setNegativeButton("No",null);
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        cancelBtn.setOnClickListener(view -> {
            finish();
        });
    }
}