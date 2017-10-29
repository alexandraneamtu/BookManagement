package com.example.alexandraneamtu.bookmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import static com.example.alexandraneamtu.bookmanagement.R.id.textView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        String title = getIntent().getStringExtra("title");
        String author = getIntent().getStringExtra("author");
        String description = getIntent().getStringExtra("description");
        int image = getIntent().getIntExtra("image",0);


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
    }


}
