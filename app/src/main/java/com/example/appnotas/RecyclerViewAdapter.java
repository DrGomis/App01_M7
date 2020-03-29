package com.example.appnotas;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    String [] name_list;
    int [] grade_a_list;
    int [] grade_b_list;
    int [] grade_c_list;
    int counter;
    private Context context;

    // Gets the parameters from "list.java"
    public RecyclerViewAdapter(Context con, String[] r_name, int [] r_grade_A, int [] r_grade_B, int [] r_grade_C, int r_counter){
        name_list = r_name;
        grade_a_list = r_grade_A;
        grade_b_list = r_grade_B;
        grade_c_list = r_grade_C;
        counter = r_counter;
        context = con;


        // Sorts data by the better average note
        for (int i = 0; i < counter; i++) {
            for (int j = 0; j < counter - i; j++) {
                if ((grade_a_list[j] + grade_b_list[j] + grade_c_list[j]) / 3 < (grade_a_list[j + 1] + grade_b_list[j + 1] + grade_c_list[j + 1]) / 3) {
                    // Saves data temporally
                    String name_temp = name_list[j];
                    int grade_a_temp = grade_a_list[j];
                    int grade_b_temp = grade_b_list[j];
                    int grade_c_temp = grade_c_list[j];

                    // Switches data
                    name_list[j] = name_list[j + 1];
                    name_list[j + 1] = name_temp;

                    grade_a_list[j] = grade_a_list[j + 1];
                    grade_a_list[j + 1] = grade_a_temp;

                    grade_b_list[j] = grade_b_list[j + 1];
                    grade_b_list[j + 1] = grade_b_temp;

                    grade_c_list[j] = grade_c_list[j + 1];
                    grade_c_list[j + 1] = grade_c_temp;
                }
            }
        }






    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.student_name.setText(name_list[position]);
        holder.grade_A.setText(Integer.toString(grade_a_list[position]));
        holder.grade_B.setText(Integer.toString(grade_b_list[position]));
        holder.grade_C.setText(Integer.toString(grade_c_list[position]));
        int average = (grade_a_list[position] + grade_b_list[position] + grade_c_list[position]) / 3;
        if (average <= 4) {
            holder.average_grade.setTextColor(Color.RED); // A failing grade it's shown in red
        } else if (average == 5) {
            holder.average_grade.setTextColor(Color.parseColor("#FFB400")); // An average grade it's shown in orange
        } else {
            holder.average_grade.setTextColor(Color.GREEN); // A great grade it's shown in green
        }
        holder.average_grade.setText(String.valueOf(average));


    }

    @Override
    public int getItemCount() {
        return counter;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView student_name;
        TextView grade_A;
        TextView grade_B;
        TextView grade_C;
        TextView average_grade;
        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            student_name = itemView.findViewById(R.id.s_name);
            grade_A = itemView.findViewById(R.id.s_grade_A);
            grade_B = itemView.findViewById(R.id.s_grade_B);
            grade_C = itemView.findViewById(R.id.s_grade_C);
            average_grade = itemView.findViewById(R.id.s_average);


            layout = itemView.findViewById(R.id.layout);
        }
    }



}