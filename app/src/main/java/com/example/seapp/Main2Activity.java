package com.example.seapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class Main2Activity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button b;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        email = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);

        final BaseActivity food = BaseActivity.getInstance();
        b = findViewById(R.id.button);

        mAuth = FirebaseAuth.getInstance();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Se App");
                mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(Main2Activity.this, "Sign up Successful", Toast.LENGTH_SHORT).show();
                                    food.changeuID(FirebaseAuth.getInstance().getUid());
                                    Intent i = new Intent(Main2Activity.this, Main3Activity.class);
                                    startActivity(i);
                                } else {
                                    Toast.makeText(Main2Activity.this, "Sign up Unsuccessful", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}
