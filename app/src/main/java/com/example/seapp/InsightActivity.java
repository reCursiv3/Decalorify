package com.example.seapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.YELLOW;
import static com.example.seapp.BaseActivity.breakfast;
import static com.example.seapp.BaseActivity.calmorning;
import static com.example.seapp.BaseActivity.dinner;
import static com.example.seapp.BaseActivity.evening;
import static com.example.seapp.BaseActivity.lunch;

public class InsightActivity extends AppCompatActivity {

    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insight);

        barChart= (BarChart)findViewById(R.id.barchart);
        barChart.setDrawBarShadow(false);
        barChart.setMaxVisibleValueCount(1200);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(false);

        ArrayList<BarEntry> barEntries =new ArrayList<>();
        barEntries.add(new BarEntry(0,breakfast));
        barEntries.add(new BarEntry(1,calmorning));
        barEntries.add(new BarEntry(2,lunch));
        barEntries.add(new BarEntry(3,evening));
        barEntries.add(new BarEntry(4,dinner));

        final ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("Breakfast");
        xAxisLabel.add("Morning Snack");
        xAxisLabel.add("Lunch");
        xAxisLabel.add("Evening Snack");
        xAxisLabel.add("Dinner");

        XAxis bottomAxis = barChart.getXAxis();
        bottomAxis.setLabelCount(barEntries.size());
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabel));

        BarDataSet barDataSet = new BarDataSet(barEntries,"Data Set1");
        barDataSet.setColor(R.color.b1colore);

        BarData data=new BarData(barDataSet);
        data.setBarWidth(0.35f);

        barChart.setData(data);
    }
}
