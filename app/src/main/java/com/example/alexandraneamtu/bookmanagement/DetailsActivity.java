package com.example.alexandraneamtu.bookmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class DetailsActivity extends AppCompatActivity {
    //BookRepository bookRepository = new BookRepository();

    int id;
    int image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        String title = getIntent().getStringExtra("title");
        String author = getIntent().getStringExtra("author");
        String description = getIntent().getStringExtra("description");
        image = getIntent().getIntExtra("image",0);
        id = getIntent().getIntExtra("id",0);

        showDetails(title,author,description,image);
    }

    public void showDetails(String name,String author,String description, int image){


        TextView titleView = (TextView)findViewById(R.id.title);
        titleView.setText(name);
        TextView authorView = (TextView)findViewById(R.id.author);
        authorView.setText(author);
        TextView descriptionView = (TextView)findViewById(R.id.description);
        descriptionView.setText(description);
        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageResource(image);

        int upperCaseLetters=0;
        for(int i=0; i<name.length(); i++)
            if(Character.isUpperCase(name.charAt(i)))
                upperCaseLetters++;

        int lowerCaseLetters=0;
        for(int i=0; i<name.length(); i++)
            if(Character.isLowerCase(name.charAt(i)))
                lowerCaseLetters++;

        int spaces=0;
        for(int i=0; i<name.length(); i++)
            if(name.charAt(i)== ' ')
                System.out.println("+++++");
                spaces++;

        PieChart pieChart = (PieChart) findViewById(R.id.chart);

        pieChart.setUsePercentValues(true);

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(upperCaseLetters, 1));
        yvalues.add(new Entry(lowerCaseLetters, 2));
        yvalues.add(new Entry(spaces,3));
        yvalues.add(new Entry(name.length()-upperCaseLetters-lowerCaseLetters-spaces,4));

        PieDataSet dataSet = new PieDataSet(yvalues, "Title analyse");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        ArrayList<String> xEntrys = new ArrayList<>();

        xEntrys.add("UpperCaseLetters");
        xEntrys.add("LowerCaseLetters");
        xEntrys.add("Numbers");
        xEntrys.add("Spaces");

        PieData data = new PieData(xEntrys,dataSet);
        pieChart.setData(data);
        pieChart.setDescription("");
        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);

    }

    public void save(View view){
        TextView titleView = (TextView)findViewById(R.id.title);
        String title = titleView.getText().toString();
        TextView authorView = (TextView)findViewById(R.id.author);
        String author = authorView.getText().toString();
        TextView descriptionView = (TextView)findViewById(R.id.description);
        String description = descriptionView.getText().toString();

        //bookRepository.updateBook(title,author,description);
        Intent intent = new Intent();
        intent.putExtra("id2",id);
        intent.putExtra("title2",title);
        intent.putExtra("author2",author);
        intent.putExtra("description2",description);
        intent.putExtra("image2",image);

        setResult(RESULT_OK,intent);
        finish();
    }


}
