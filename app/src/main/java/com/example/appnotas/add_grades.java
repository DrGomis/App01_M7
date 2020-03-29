package com.example.appnotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_grades extends AppCompatActivity {

    String [] name_list;
    int [] grade_a_list;
    int [] grade_b_list;
    int [] grade_c_list;
    int counter;
    EditText studentName, gradeA, gradeB, gradeC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grades);


        // getIntent gets the information sent by "MainActivity"
        Intent getInformation = getIntent();
        // Green names refers to the information sent by "MainActivity"
        name_list = getInformation.getStringArrayExtra("name_list");
        counter = getInformation.getIntExtra("counter", 0);
        grade_a_list = getInformation.getIntArrayExtra("grade_a_list");
        grade_b_list = getInformation.getIntArrayExtra("grade_b_list");
        grade_c_list = getInformation.getIntArrayExtra("grade_c_list");

        /*
        TextView MostrarGuardado = findViewById(R.id.prueba);

        if(counter > 0){
            MostrarGuardado.setText(name_list[counter - 1]);
        }*/

        // Get the information from the inputs
        studentName = findViewById(R.id.insert_student);
        gradeA = findViewById(R.id.insert_gradeA);
        gradeB = findViewById(R.id.insert_gradeB);
        gradeC = findViewById(R.id.insert_gradeC);



        // Information is saved
        final Button btnAfegirUsuari = findViewById(R.id.btn_save);
        btnAfegirUsuari.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean isValid = verifyData(v);

                if (isValid == true) {
                    name_list[counter] = studentName.getText().toString();
                    grade_a_list[counter] = Integer.parseInt(gradeA.getText().toString());
                    grade_b_list[counter] = Integer.parseInt(gradeB.getText().toString());
                    grade_c_list[counter] = Integer.parseInt(gradeC.getText().toString());
                    counter++;
                }
            }
        });
    }


    // Verifies the input data
    public boolean verifyData(View v) {
        // Need to use Strings to properly use isEmpty()
        String validate_name = studentName.getText().toString();
        String validate_gradeA = gradeA.getText().toString();
        String validate_gradeB =gradeB.getText().toString();
        String validate_gradeC = gradeC.getText().toString();

        // VALIDATORS
        if (validate_name.isEmpty() == true) {
            studentName.setError("The field name cannot be empty :(");
            return false;
        }
        if (validate_gradeA.isEmpty() == true || Integer.parseInt(validate_gradeA) < 0 || Integer.parseInt(validate_gradeA) > 10) {
            gradeA.setError("Grade A must be a number between 0 and 10!");
            return false;
        }
        if (validate_gradeB.isEmpty() == true || Integer.parseInt(validate_gradeB) < 0 || Integer.parseInt(validate_gradeB) > 10) {
            gradeB.setError("Grade B must be a number between 0 and 10!");
            return false;
        }
        if (validate_gradeC.isEmpty() == true || Integer.parseInt(validate_gradeC) < 0 || Integer.parseInt(validate_gradeC) > 10) {
            gradeC.setError("Grade C must be a number between 0 and 10!");
            return false;
        }
        // Checking if "Student Name" has a number
        for (int i = 0; i < validate_name.length(); i++) {
            char checker = validate_name.charAt(i);
            if (checker == '0' || checker == '1' || checker == '2' || checker == '3' || checker == '4' || checker == '5' || checker == '6' || checker == '7' || checker == '8' || checker == '9') {
                studentName.setError("Numbers are not allowed!");
                return false;
            }
        }
        // If everything is ok, we go back with good news!
        Toast.makeText(this, "Successfully saved!", Toast.LENGTH_SHORT).show();
        return true;
    }






    // Returns the stored data when we press "back"
    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("name_list", name_list);
        returnIntent.putExtra("grade_a_list", grade_a_list);
        returnIntent.putExtra("grade_b_list", grade_b_list);
        returnIntent.putExtra("grade_c_list", grade_c_list);
        returnIntent.putExtra("counter", counter);

        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
