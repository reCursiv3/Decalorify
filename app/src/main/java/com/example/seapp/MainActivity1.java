package com.example.seapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Calendar;

import static com.example.seapp.BaseActivity.total;

public class MainActivity1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Button button3;

    //Food Menu Variables
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    //Date Variables
    private TextView mDisplayDate;
    private TextView mCalCount;
    private TextView mcalRec;
    private DatePickerDialog.OnDateSetListener mDataSetListener;

    ProgressBar pb;
    private Button insights;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        button3=(Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity1.this,premium_activity.class);
                startActivity(intent);
            }
        });
        final BaseActivity tmp = BaseActivity.getInstance();
        mCalCount=(TextView)findViewById(R.id.calCount);
        mcalRec=(TextView)findViewById(R.id.CalRec);
        mCalCount.setText("0");

        insights=(Button)findViewById(R.id.insights_bt);
        insights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity1.this,InsightActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Navigation Bar
        mDrawerLayout=(DrawerLayout)findViewById(R.id.MainActivity1);
        mToggle=new ActionBarDrawerToggle(this, mDrawerLayout,R.string.Open,R.string.Close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        navigationView = (NavigationView)findViewById(R.id.NavigationView);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
//                        //menuItem.setChecked(true);

                        int id = menuItem.getItemId();
                        switch(id)
                        {
                            case R.id.db:
                                Intent i = new Intent(MainActivity1.this, ProfileSetting.class);
                                startActivity(i);
                                Toast.makeText(MainActivity1.this, "Dashboard",Toast.LENGTH_LONG).show();
                                break;
                            default:
                                return true;
                        }
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

        //Food Menu ViewPager
        final ArrayList<ExampleItem> exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem("Breakfast",tmp.breakfast+" of 430 Cal"));
        exampleList.add(new ExampleItem("Morning Snack",tmp.calmorning+" of 164 Cal"));
        exampleList.add(new ExampleItem("Lunch",tmp.lunch+" of 438 Cal"));
        exampleList.add(new ExampleItem("Evening Snack",tmp.evening+" of 164 Cal"));
        exampleList.add(new ExampleItem("Dinner",tmp.dinner+" of 438 Cal"));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(exampleList);
        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Toast.makeText(MainActivity1.this, "No food has been added yet",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDeleteClick(int position) {
                if (position == 0) {
                    tmp.changeStatus("Breakfast");
                } else if (position == 1) {
                    tmp.changeStatus("Morning");
                } else if (position == 2) {
                    tmp.changeStatus("Lunch");
                } else if (position == 3) {
                    tmp.changeStatus("Evening");
                } else if (position == 4) {
                    tmp.changeStatus("Dinner");
                }
                Intent intent=new Intent(MainActivity1.this,Food_Activity.class);
                startActivity(intent);
            }
        });

        prog();

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        //Date Picker Part
        mDisplayDate=(TextView)findViewById(R.id.DatePicker);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(
                        MainActivity1.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDataSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDataSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String text=dayOfMonth+"/"+month+"/"+year;
                mDisplayDate.setText(text);
            }
        };
    }

    public void prog(){
        pb=(ProgressBar)findViewById(R.id.progressBar2);
        if(total<1000)
        {
            pb.setProgress(total);
            mCalCount.setText(Integer.toString(total));
        }
        else if(total>900&&total<1000)
        {
            Toast.makeText(MainActivity1.this,"Bro, you have almost reached the limit..Marna hai kya?",Toast.LENGTH_LONG).show();
            pb.setProgress(total);
            mCalCount.setText(Integer.toString(total));
        }
        else if(total>=1000)
        {
            Toast.makeText(MainActivity1.this,"YOU HAVE CROSSED YOUR LIMIT OF DAILY CALORIE CONSUMPTION", Toast.LENGTH_LONG).show();
            pb.setProgress(1000);
            mCalCount.setText(Integer.toString(total));

            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity1.this);
            builder.setCancelable(true);
            builder.setTitle("CALORIE CONSUMPTION LIMIT");
            builder.setMessage("Dear User, you have reached the limit of you daily calorie consumption. Consuming more calories will prevent you from reaching your desired weight goals");

            builder.setNegativeButton("RISK IT", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    mCalCount.setText(Integer.toString(total-1000));
                    pb.setProgress(total-1000);
                    mcalRec.setText("Excess Calories");
                }
            });

            builder.setPositiveButton("UNDERSTOOD", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    mCalCount.setText(Integer.toString(total-1000));
                    pb.setProgress(total-1000);
                    mcalRec.setText("Excess Calories");
                }
            });
            builder.show();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.db: Intent intent=new Intent(MainActivity1.this,ProfileSetting.class);
            startActivity(intent); break;
            default: Log.i("Notif","ALarm");
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
