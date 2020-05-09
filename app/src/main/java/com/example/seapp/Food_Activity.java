package com.example.seapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Food_Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ExampleAdapter2 mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public int calories = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_);

        final BaseActivity food = BaseActivity.getInstance();

        final ArrayList<ExampleItem2> exampleList2 = new ArrayList<>();
        exampleList2.add(new ExampleItem2("Steamed Idli","78 Cal","1.0 idli"));
        exampleList2.add(new ExampleItem2("Banana Ripe","117 Cal","1.0 small(6' to 6-7/b' long)"));
        exampleList2.add(new ExampleItem2("Dosa(Plain)","302 Cal","1.0 serving(2.0 piece)"));
        exampleList2.add(new ExampleItem2("Wheat,bread (Brown)","136 Cal","2.0 slice"));
        exampleList2.add(new ExampleItem2("Tea with Milk and Sugar","73 Cal","1.0 serving(1.0 tea cup)"));
        exampleList2.add(new ExampleItem2("Wheat Chapatti","146 Cal","3.0 pieces"));
        exampleList2.add(new ExampleItem2("Boiled Egg","86 Cal","1.0 serving"));
        exampleList2.add(new ExampleItem2("Cow's Milk","167 Cal","1.0 glass"));
        exampleList2.add(new ExampleItem2("Aloo Ka Paratha","182 Cal","1.0 serving"));
        exampleList2.add(new ExampleItem2("FIlter Coffee with Milk","53 Cal","1.0 serving"));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter2(exampleList2);
        mAdapter.setOnItemClickListener(new ExampleAdapter2.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Toast.makeText(Food_Activity.this, "This is part of the food menu",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDeleteClick(int position) {
                Toast.makeText(Food_Activity.this, "Food has been added to your diet :)",Toast.LENGTH_LONG).show();
                Intent intent2=new Intent(Food_Activity.this,MainActivity1.class);
                switch(position){
                    case 0:calories=78;break;
                    case 1:calories=117;break;
                    case 2:calories=302;break;
                    case 3:calories=136;break;
                    case 4:calories=73;break;
                    case 5:calories=146;break;
                    case 6:calories=86;break;
                    case 7:calories=167;break;
                    case 8:calories=182;break;
                    case 9:calories=53;break;
                }
                startActivity(intent2);
                if (food.active == "Breakfast") {
                    food.changeBreakfast(calories);
                } else if (food.active == "Morning") {
                    food.changeMorning(calories);
                } else if (food.active == "Lunch") {
                    food.changeLunch(calories);
                } else if (food.active == "Evening") {
                    food.changeEvening(calories);
                } else if (food.active == "Dinner") {
                    food.changeDinner(calories);
                }
                food.calculateTotal();
                finish();
            }
        });

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
