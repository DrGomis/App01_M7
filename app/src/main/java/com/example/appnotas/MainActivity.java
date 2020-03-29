package com.example.appnotas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {
    // Initializations
    static final int MAX_STUDENTS = 50; // You can change the array size here


    String [] name_list = new String [MAX_STUDENTS];
    int [] grade_a_list = new int [MAX_STUDENTS];
    int [] grade_b_list = new int [MAX_STUDENTS];
    int [] grade_c_list = new int [MAX_STUDENTS];
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button to go to "Add Grades"
        final ImageButton btn_goToAddGrades = findViewById(R.id.img_btn_add);
        btn_goToAddGrades.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToAddGrades();
            }
        });

        // Button to go to "List"
        final ImageButton btn_goToList = findViewById(R.id.img_btn_see);
        btn_goToList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (counter < 1) {
                    Toast.makeText(MainActivity.this, "No students saved!", Toast.LENGTH_SHORT).show();
                } else {
                    goToList(); // If we have students, we will travel
                }
            }
        });

        // Button to go to "Stats"
        final ImageButton btn_goToStats = findViewById(R.id.img_stats);
        btn_goToStats.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (counter < 1) {
                    Toast.makeText(MainActivity.this, "No students saved!", Toast.LENGTH_SHORT).show();
                } else {
                    goToStats(); // If we have students, we will travel
                }
            }
        });

        // Button to Approve All"
        final ImageButton btn_approveAll = findViewById(R.id.img_approve);
        btn_approveAll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (counter < 1) {
                    Toast.makeText(MainActivity.this, "No students saved!", Toast.LENGTH_SHORT).show();
                } else {
                    int howMany = 0;
                    for (int i = 0; i < counter; i++) {
                        int avg = ((grade_a_list[i] + grade_b_list[i] + grade_c_list[i])/3);

                        if (avg < 5) {
                            if (grade_a_list[i] < 5) {
                                grade_a_list[i] = 5;
                            }
                            if (grade_b_list[i] < 5) {
                                grade_b_list[i] = 5;
                            }
                            if (grade_c_list[i] < 5) {
                                grade_c_list[i] = 5;
                            }
                            howMany++;
                        }
                    }
                    if (howMany > 0) {
                        Toast.makeText(MainActivity.this, "Approved " + howMany + " students!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "No changes were made!", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });

        // Button to go to "Help"
        final ImageButton btn_goToHelp = findViewById(R.id.img_halp);
        btn_goToHelp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToHelp(); // If we have students, we will travel
            }
        });




    }

    // Methods to travel between pages
    private void goToAddGrades() {
        Intent sendToAddGrades = new Intent(this, add_grades.class);
        sendToAddGrades.putExtra("name_list", name_list);
        sendToAddGrades.putExtra("grade_a_list", grade_a_list);
        sendToAddGrades.putExtra("grade_b_list", grade_b_list);
        sendToAddGrades.putExtra("grade_c_list", grade_c_list);
        sendToAddGrades.putExtra("counter", counter);
        startActivityForResult(sendToAddGrades, 1);
    }

    private void goToList(){
        Intent sendToList = new Intent (this, list.class);
        sendToList.putExtra("name_list", name_list);
        sendToList.putExtra("grade_a_list", grade_a_list);
        sendToList.putExtra("grade_b_list", grade_b_list);
        sendToList.putExtra("grade_c_list", grade_c_list);
        sendToList.putExtra("counter", counter);
        startActivity(sendToList);
    }

    private void goToStats(){
        Intent sendToStats = new Intent (this, app_stats.class);
        sendToStats.putExtra("name_list", name_list);
        sendToStats.putExtra("grade_a_list", grade_a_list);
        sendToStats.putExtra("grade_b_list", grade_b_list);
        sendToStats.putExtra("grade_c_list", grade_c_list);
        sendToStats.putExtra("counter", counter);
        startActivity(sendToStats);
    }

    private void goToHelp(){
        Intent sendToHelp = new Intent (this, halp.class);
        startActivity(sendToHelp);
    }




    // Received Data
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                name_list = data.getStringArrayExtra("name_list");
                grade_a_list = data.getIntArrayExtra("grade_a_list");
                grade_b_list = data.getIntArrayExtra("grade_b_list");
                grade_c_list = data.getIntArrayExtra("grade_c_list");
                counter = data.getIntExtra("counter", 0);
            }
        }
    }
}
