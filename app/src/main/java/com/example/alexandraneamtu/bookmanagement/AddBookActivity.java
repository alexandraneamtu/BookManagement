package com.example.alexandraneamtu.bookmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AddBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
    }

    public void addBook(View view) {

        TextView titleView = (TextView)findViewById(R.id.bookTitle);
        String title = titleView.getText().toString();
        TextView authorView = (TextView)findViewById(R.id.bookAuthor);
        String author = authorView.getText().toString();
        TextView descriptionView = (TextView)findViewById(R.id.bookDescription);
        String description = descriptionView.getText().toString();

        //bookRepository.updateBook(title,author,description);
        Intent intent = new Intent();
        intent.putExtra("title",title);
        intent.putExtra("author",author);
        intent.putExtra("description",description);

        setResult(RESULT_OK,intent);
        finish();
    }
}
