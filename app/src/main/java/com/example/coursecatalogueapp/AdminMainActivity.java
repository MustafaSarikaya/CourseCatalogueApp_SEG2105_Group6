package com.example.coursecatalogueapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminMainActivity extends Activity {

    private Button instructorsButton;
    private Button Courses;
    private Button studentsButton;



    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminmainpage);

        instructorsButton = (Button)findViewById(R.id.instructors);
        Courses = (Button)findViewById(R.id.Courses);
        studentsButton = (Button)findViewById(R.id.Students);

        studentsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMainActivity.this, StudentListActivity.class);
                startActivity(intent);
            }
        });

        instructorsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMainActivity.this, InstructorsListActivity.class);
                startActivity(intent);
            }
        });

    }

}
