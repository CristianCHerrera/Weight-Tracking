package com.example.project2final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity { //Class that handles login

    EditText username,password;
    Button login;
    DBHelper myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);

        myDatabase = new DBHelper(this);


        login.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals("")){ //If user or password box are empty
                    Toast.makeText(Login.this, "Please enter login credentials", Toast.LENGTH_SHORT).show(); //Error message
                }
                else{
                    Boolean result = myDatabase.verifyUserAndPassword(user,pass); //True or false, if username and password are in database
                        if (result == true){ // If in data base
                            Intent intent = new Intent(getApplicationContext(),MainActivity2.class); //Move user to home screen
                            startActivity(intent);
                    }
                        else{
                            Toast.makeText(Login.this, "Invalid login credentials", Toast.LENGTH_SHORT).show(); //Error message
                        }
                }
            }
        });
    }
}