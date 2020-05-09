package com.example.seapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
   public EditText ed1;
 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       // ed1 = (EditText)findViewById(R.id.ed1);

    }
}
