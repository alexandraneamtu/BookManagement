package com.example.alexandraneamtu.bookmanagement;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PrepareBookActivity extends AppCompatActivity {

    EditText date;
    DatePickerDialog datePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_book);

        date = (EditText) findViewById(R.id.dateID);
        // perform click event on edit text
        setDateTimeField();
        date.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                datePickerDialog.show();
                return false;
            }
        });
    }

    private void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
                final Date startDate = newDate.getTime();
                String fdate = sd.format(startDate);

                date.setText(fdate);

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());

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

        EditText dateEditText = (EditText) findViewById(R.id.dateID);
        if (dateEditText.getText().toString().matches("")) {
            Toast.makeText(this, "You did not enter a book title", Toast.LENGTH_SHORT).show();
            return;
        }


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"test@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Prepare a book");
        intent.putExtra(Intent.EXTRA_TEXT, "Hello,\nPlease keep " + bookEditText.getText() + " for "+ nameEditText.getText()+" until "+ dateEditText.getText()+".\n"+"Thank you!");
        try {
            startActivity(Intent.createChooser(intent, "Send mail..."));
        }catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(PrepareBookActivity.this, "There are no Email clients installed!", Toast.LENGTH_SHORT).show();
        }

    }
}

