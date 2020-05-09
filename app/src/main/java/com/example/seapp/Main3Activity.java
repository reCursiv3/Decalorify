package com.example.seapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class Main3Activity extends AppCompatActivity {

    EditText age;
    EditText weight;
    EditText height;
    EditText special;
    Button b;
    Spinner s;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        age = findViewById(R.id.editText);
        weight = findViewById(R.id.editText2);
        height = findViewById(R.id.editText3);
        special = findViewById(R.id.editText4);
        s = findViewById(R.id.spinner);
        b = findViewById(R.id.button);

        final BaseActivity food = BaseActivity.getInstance();


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int BMI = Integer.parseInt(weight.getText().toString()) / (Integer.parseInt(height.getText().toString())/100)^2;
                food.changeBMI(BMI);
                food.changeWeight(Integer.parseInt(weight.getText().toString()));
                food.changeHeight(Integer.parseInt(weight.getText().toString()));

                Map<String, Object> user = new HashMap<>();
                user.put("age",age.getText().toString());
                user.put("weight",weight.getText().toString());
                user.put("height",height.getText().toString());
                user.put("special",special.getText().toString());

                db.collection("USER_INFO").document(food.uID).set(user)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(Main3Activity.this, "Information Saved", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Main3Activity.this, Main4Activity.class);
                                startActivity(i);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Main3Activity.this, "Network Error", Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });
    }
}
