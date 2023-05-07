package com.example.project2final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private EditText date, weight;
    private Button submitButton,viewData;
    private WeightDBHelper wdbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // initialize variables.
        date = findViewById(R.id.enterDate);
        weight = findViewById(R.id.enterWeight);
        submitButton = findViewById(R.id.addData);
        viewData = findViewById(R.id.viewData);

        wdbHelper = new WeightDBHelper(MainActivity2.this);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String userDate = date.getText().toString();
                String userWeight = weight.getText().toString();

                // validating if the text fields are empty or not.
                if (userDate.isEmpty() || userWeight.isEmpty()) {
                    Toast.makeText(MainActivity2.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                wdbHelper.addNewData(userDate, userWeight);

                // after adding the data we are displaying a toast message.
                Toast.makeText(MainActivity2.this, "Data has been added.", Toast.LENGTH_SHORT).show();
                date.setText("");
                weight.setText("");
            }
        });

        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                Intent i = new Intent(MainActivity2.this, ViewData.class);
                startActivity(i);
            }
        });
    }
}