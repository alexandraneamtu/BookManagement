package com.example.alexandraneamtu.bookmanagement;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PrepareBookActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_book);
    }

    public void prepareBook(View view) {
        EditText nameEditText = (EditText) findViewById(R.id.name);
        if (nameEditText.getText().toString().matches("")) {
            Toast.makeText(this, "You did not enter a name", Toast.LENGTH_SHORT).show();
            return;
        }

        EditText bookEditText = (EditText) findViewById(R.id.bookTitle);
        if (bookEditText.getText().toString().matches("")) {
            Toast.makeText(this, "You did not enter a book title", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"test@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Prepare a book");
        intent.putExtra(Intent.EXTRA_TEXT, "Hello,\nPlease keep " + bookEditText.getText() + " for "+ nameEditText.getText()+".\n"+"Thank you!");
        try {
            startActivity(Intent.createChooser(intent, "Send mail..."));
        }catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(PrepareBookActivity.this, "There are no Email clients installed!", Toast.LENGTH_SHORT).show();
        }

    }
}

