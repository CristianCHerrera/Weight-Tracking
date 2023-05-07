package com.example.project2final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity { //Driving code

    EditText enterUsername,enterPassword,reEnterPassword;
    Button registerButton, loginButton;
    DBHelper myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterUsername = (EditText) findViewById(R.id.enterUsername);
        enterPassword = (EditText) findViewById(R.id.enterPassword);
        reEnterPassword = (EditText) findViewById(R.id.reEnterPassword);
        registerButton = (Button) findViewById(R.id.registerButton);
        loginButton = (Button) findViewById(R.id.loginButton);


        myDatabase = new DBHelper(this);

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ //Checks data entered into the 3 text boxes when "Register account" is clicked
                String user = enterUsername.getText().toString();
                String pass = enterPassword.getText().toString();
                String repass = reEnterPassword.getText().toString();
                if (user.equals("") || pass.equals("") || repass.equals("")){ //If either of the 3 text boxes are empty
                    Toast.makeText(MainActivity.this, "Fill out all the fields.", Toast.LENGTH_SHORT).show(); //Error message
                }
                else{ //If all boxes are filled
                    if(pass.equals(repass)){ //If passwords match
                        Boolean userResult = myDatabase.verifyUser(user); //True or false, check if user is already in database
                        if (userResult == false){ //If user not in database
                            Boolean result = myDatabase.insertUser(user,pass); //True or false, add username and password to database
                            if (result = true){ //If true, move to next screen and send success message
                                Toast.makeText(MainActivity.this,"Registration Successful.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Login.class);
                                startActivity(intent);
                            }
                            else{ //Error message
                                Toast.makeText(MainActivity.this,"Registration Failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{ //User is already in database
                            Toast.makeText(MainActivity.this,"User already exists! \n Please enter another Username.", Toast.LENGTH_SHORT).show(); //Error message
                        }
                    }
                    else{ //If passwords don't match
                        Toast.makeText(MainActivity.this,"Passwords do not match.", Toast.LENGTH_SHORT).show(); //Error message
                    }
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //If user click "Sign in" button, moves them to sign in screen
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });




    }
}
