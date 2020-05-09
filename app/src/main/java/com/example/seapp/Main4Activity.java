package com.example.seapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Main4Activity extends AppCompatActivity {

    String status;
    TextView measure;
    TextView grow;
    Button add;
    Button subtract;
    TextView weight;
    Button submit;
    Spinner intensity;
    int temp = 0;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        final BaseActivity food = BaseActivity.getInstance();

        add = (Button)findViewById(R.id.button2);
        subtract = (Button)findViewById(R.id.button3);
        weight = (TextView)findViewById(R.id.textView6);
        measure = findViewById(R.id.textView2);
        grow = findViewById(R.id.textView3);
        submit = findViewById(R.id.button);
        intensity = findViewById(R.id.spinner);

        final int weightToCal = food.weight;
        int heightToCal = food.height;

        double low = 18.5 * ((food.height/100) ^ 2);
        double high = 24 * ((food.height/100) ^ 2);
        status = new String();
        if(food.BMI>25) {
            status = "Overweight";
        } else if (food.BMI>18.5) {
            status = "Normal";
        } else {
            status = "Underweight";
        }


        measure.setText(status);
        String s = Double.toString(low) + "kgs to" + Double.toString(high) + "kgs";
        grow.setText(s);
        weight.setText(Integer.toString(food.weight));
        final int finalWeight = food.weight;

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(temp<20) {
                    temp++;
                    int s = finalWeight + temp;
                    weight.setText(s + " kgs");
                }
            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(temp>-20){
                    temp--;
                    int s = finalWeight + temp;
                    weight.setText(s + " kgs");
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> user = new HashMap<>();
                user.put("age",weight.getText().toString());
                user.put("intensity",intensity.getSelectedItem().toString());

                db.collection("CAL_TRACKER").document(food.uID).set(user)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(Main4Activity.this, "Information Saved", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Main4Activity.this, MainActivity1.class);
                                startActivity(i);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Main4Activity.this, "Network Error", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


    }
}
