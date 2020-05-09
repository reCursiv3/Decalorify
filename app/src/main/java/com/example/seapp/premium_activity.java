package com.example.seapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class premium_activity extends AppCompatActivity {
   Button button,bb;
    ImageButton ib2;
    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    Integer [] colors=null;
    ArgbEvaluator argbEvaluator= new ArgbEvaluator();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium_activity);
        ib2=(ImageButton)findViewById(R.id.ib2);
        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(premium_activity.this,MainActivity1.class);
                startActivity(intent);
            }
        });
        models=new ArrayList<>();
        models.add(new Model(R.drawable.z,"Silver Membership"," 24 x 7"));
        models.add(new Model(R.drawable.g,"Gold Membership"," 24 x 7"));


        adapter = new Adapter(models,this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,0,130,0);
        Integer [] colors_temp ={getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                //getResources().getColor(R.color.color3)

        };
        colors=colors_temp;


        button =(Button)findViewById(R.id.button);
        bb=(Button)findViewById(R.id.bb);
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(premium_activity.this,Paytm.class);
                startActivity(intent);

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentmethod();
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position < (adapter.getCount() -1) && position > (colors.length -1)) {
                    viewPager.setBackgroundColor(
                            (Integer)argbEvaluator.
                                    evaluate(positionOffset,colors[position]
                                            ,colors[position + 1])
                    );
                }
                else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public void paymentmethod(){
        Intent intent= new Intent(premium_activity.this,Paytm.class);
        startActivity(intent);
    }
}
