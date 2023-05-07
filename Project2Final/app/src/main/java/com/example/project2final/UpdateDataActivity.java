package com.example.project2final;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateDataActivity extends AppCompatActivity {

    private EditText date, weight;
    private Button updateBtn,deleteBtn;
    private WeightDBHelper wDatabase;
    String userDate, userWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        // initializing all our variables.
        date = findViewById(R.id.idDate);
        weight = findViewById(R.id.idWeight);
        updateBtn = findViewById(R.id.idBtnUpdate);
        deleteBtn = findViewById(R.id.idBtnDelete);

        wDatabase = new WeightDBHelper(UpdateDataActivity.this);


        userDate = getIntent().getStringExtra("date");
        userWeight = getIntent().getStringExtra("weight");

        date.setText(userDate);
        weight.setText(userWeight);

        // adding on click listener to our update data button.
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // inside this method we are calling an update data method and passing all our edit text values.
                wDatabase.updateData(userDate, date.getText().toString(), weight.getText().toString());

                // display success message
                Toast.makeText(UpdateDataActivity.this, "Data Updated..", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(UpdateDataActivity.this, MainActivity2.class);
                startActivity(i);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wDatabase.deleteData(userDate);
                Toast.makeText(UpdateDataActivity.this, "Deleted selected data..", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateDataActivity.this, MainActivity2.class);
                startActivity(i);
            }
        });
    }
}