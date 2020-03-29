package com.example.appnotas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class list extends AppCompatActivity {
    String [] name_list;
    int [] grade_a_list;
    int [] grade_b_list;
    int [] grade_c_list;
    int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Getting data from Main
        Intent getInformation = getIntent();
        name_list = getInformation.getStringArrayExtra("name_list");
        grade_a_list = getInformation.getIntArrayExtra("grade_a_list");
        grade_b_list = getInformation.getIntArrayExtra("grade_b_list");
        grade_c_list = getInformation.getIntArrayExtra("grade_c_list");
        counter = getInformation.getIntExtra("counter", 0);


        // Send the parameters to the RecyclerViewAdapter
        RecyclerView list_items = findViewById(R.id.recycler_list);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, name_list, grade_a_list, grade_b_list, grade_c_list, counter);


        list_items.setAdapter(adapter);
        list_items.setLayoutManager(new LinearLayoutManager((this)));


    }
}
