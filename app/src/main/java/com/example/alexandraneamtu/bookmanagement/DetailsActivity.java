package com.example.alexandraneamtu.bookmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.alexandraneamtu.bookmanagement.model.Book;
import com.example.alexandraneamtu.bookmanagement.repository.BookRepository;

import static com.example.alexandraneamtu.bookmanagement.R.id.textView;

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
