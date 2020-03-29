package com.example.appnotas;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class app_stats extends AppCompatActivity {

    String [] name_list;
    int [] grade_a_list;
    int [] grade_b_list;
    int [] grade_c_list;
    int counter;

    int global_approved = 0;
    int total_avg = 0;

    int a_avg = 0; // Average note from A
    int b_avg = 0; // Average note from B
    int c_avg = 0; // Average note from C


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_stats);


        Intent getInformation = getIntent();
        // Green names refers to the information sent by "MainActivity"
        name_list = getInformation.getStringArrayExtra("name_list");
        counter = getInformation.getIntExtra("counter", 0);
        grade_a_list = getInformation.getIntArrayExtra("grade_a_list");
        grade_b_list = getInformation.getIntArrayExtra("grade_b_list");
        grade_c_list = getInformation.getIntArrayExtra("grade_c_list");

        // Search all approved
        for (int i = 0; i < counter; i++) {
            int average = ((grade_a_list[i] + grade_b_list[i] + grade_c_list[i]) / 3);
            if (average >= 5) {
                global_approved++;
            }
        }

        // Shows percentage of approved
        float percentage_apr = (global_approved * 100) / counter;
        TextView ShowPercentage = findViewById(R.id.result_per);

        if (percentage_apr <= 35) {
            ShowPercentage.setTextColor(Color.parseColor("#C51111"));
        }
        if (percentage_apr >= 36 && percentage_apr <= 55) {
            ShowPercentage.setTextColor(Color.parseColor("#FF6E00"));
        }
        if (percentage_apr > 55) {
            ShowPercentage.setTextColor(Color.parseColor("#20871D"));
        }

        ShowPercentage.setText(String.valueOf(percentage_apr) + "%");


        /* ======== DATA FROM CLASS A ======== */
        int dataA [] = getAllDataFromClass(grade_a_list, counter); // 0, Total | 1, Apr | 2, Fail | 3, % Apr | 4, % Fail
        a_avg = dataA[0] / counter;

        // Shows all data from Class A
        TextView ApprovedA = findViewById(R.id.approved_a_res);
        ApprovedA.setText(String.valueOf(dataA[1]));

        TextView FailureA = findViewById(R.id.failure_a_res);
        FailureA.setText(String.valueOf(dataA[2]));

        TextView PerApprovedA = findViewById(R.id.approved_a_per);
        PerApprovedA.setText("(" + dataA[3] + "%)");

        TextView PerFailureA = findViewById(R.id.failure_a_per);
        PerFailureA.setText("(" + dataA[4] + "%)");

        // Overall Class A grades
        TextView AverageA = findViewById(R.id.avg_note_a_res);
        if (IsAGoodGrade(a_avg) == true) {
            AverageA.setTextColor(Color.parseColor("#20871D"));
        } else { // Bad news...
            AverageA.setTextColor(Color.parseColor("#C51111"));
        }
        AverageA.setText(String.valueOf(a_avg));

        /* ======== DATA FROM CLASS B ======== */
        int dataB [] = getAllDataFromClass(grade_b_list, counter); // 0, Total | 1, Apr | 2, Fail | 3, % Apr | 4, % Fail
        b_avg = dataB[0] / counter;

        // Shows all data from Class B
        TextView ApprovedB = findViewById(R.id.approved_b_res);
        ApprovedB.setText(String.valueOf(dataB[1]));

        TextView FailureB = findViewById(R.id.failure_b_res);
        FailureB.setText(String.valueOf(dataB[2]));

        TextView PerApprovedB = findViewById(R.id.approved_b_per);
        PerApprovedB.setText("(" + dataB[3] + "%)");

        TextView PerFailureB = findViewById(R.id.failure_b_per);
        PerFailureB.setText("(" + dataB[4] + "%)");



        // Overall Class B grades
        TextView AverageB = findViewById(R.id.avg_note_b_res);
        if (IsAGoodGrade(b_avg) == true) {
            AverageB.setTextColor(Color.parseColor("#20871D"));
        } else { // Bad news...
            AverageB.setTextColor(Color.parseColor("#C51111"));
        }
        AverageB.setText(String.valueOf(b_avg));

        /* ======== DATA FROM CLASS C ======== */
        int dataC [] = getAllDataFromClass(grade_b_list, counter); // 0, Total | 1, Apr | 2, Fail | 3, % Apr | 4, % Fail
        c_avg = dataC[0] / counter;

        // Shows all data from Class C
        TextView ApprovedC = findViewById(R.id.approved_c_res);
        ApprovedC.setText(String.valueOf(dataC[1]));

        TextView FailureC = findViewById(R.id.failure_c_res);
        FailureC.setText(String.valueOf(dataC[2]));

        TextView PerApprovedC = findViewById(R.id.approved_c_per);
        PerApprovedC.setText("(" + dataC[3] + "%)");

        TextView PerFailureC = findViewById(R.id.failure_c_per);
        PerFailureC.setText("(" + dataC[4] + "%)");

        // Overall Class C grades
        TextView AverageC = findViewById(R.id.avg_note_c_res);
        if (IsAGoodGrade(c_avg) == true) {
            AverageC.setTextColor(Color.parseColor("#20871D"));
        } else { // Bad news...
            AverageC.setTextColor(Color.parseColor("#C51111"));
        }
        AverageC.setText(String.valueOf(c_avg));

        /* ======== AVERAGE NOTE FROM ALL CLASSES ======== */
        total_avg = (a_avg + b_avg + c_avg) / 3;
        TextView AverageTotal = findViewById(R.id.result_avg);

        if (IsAGoodGrade(total_avg) == true) {
            AverageTotal.setTextColor(Color.parseColor("#20871D"));
        } else { // Bad news...
            AverageTotal.setTextColor(Color.parseColor("#C51111"));
        }
        AverageTotal.setText(String.valueOf(total_avg));








    }


    // Gets all relevant information from a Class
    public int[] getAllDataFromClass(int class_to_check [], int count) {
        int stored_data [] = new int[5];
        int total = 0;
        int apr = 0;
        int fail = 0;

        for (int i = 0; i < count; i++) {
            if (class_to_check[i] >= 5) {
                apr++; // Raises approved people
            } else {
                fail++;
            }
            total = total + class_to_check[i];
        }
        stored_data[0] = total;
        stored_data[1] = apr;
        stored_data[2] = fail;
        stored_data[3] = (apr * 100) / count;
        stored_data[4] = (fail * 100) / count;

        return stored_data;
    }

    // Checks if is a good note
    public boolean IsAGoodGrade(float average) {
        if (average >= 5) { // Good overall note!
            return true;
        } else {
            return false;
        }
    }
}
