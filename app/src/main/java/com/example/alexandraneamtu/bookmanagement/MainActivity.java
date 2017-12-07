package com.example.alexandraneamtu.bookmanagement;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.view.MotionEvent;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alexandraneamtu.bookmanagement.model.Book;
import com.example.alexandraneamtu.bookmanagement.repository.BookRepository;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private BookRepository bookRepository;// = new BookRepository(getApplicationContext());

    private BookListAdapter bookListAdapter;

    private List<Book> bookList;


    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookRepository = new BookRepository(getApplicationContext());

        setContentView(R.layout.activity_main);

        /*
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(9f, 5));

        BarDataSet dataset = new BarDataSet(entries, "# of Calls");

        */

        Button prepareBookButton = (Button) findViewById(R.id.prepareBook);
        prepareBookButton.setOnClickListener((v)->{
            Intent intent = new Intent(MainActivity.this,PrepareBookActivity.class);
            startActivity(intent);
        });

        Button addBookButton = (Button) findViewById(R.id.addBook);
        addBookButton.setOnClickListener((v)->{
            Intent intent = new Intent(MainActivity.this,AddBookActivity.class);
            startActivityForResult(intent,2);
        });

        //bookList = bookRepository.getBookList();

        bookListAdapter = new BookListAdapter(this,R.layout.list_item, bookRepository.getBookList());
        listView= (ListView) findViewById(R.id.listView);
        listView.setAdapter(bookListAdapter);
        onBookSelected();
        //ListView.setItemsCanFocus(true);



    }


    public void onBookSelected(){
        ListView listView = (ListView) findViewById(R.id.listView);
        //List<Book> books = bookRepository.getBookList();
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //System.out.println("####################"+id+"##############"+position+"##############"+view.getTag());
                //System.out.println("-----------"+Integer.toString(position));
                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                intent.putExtra("id",bookRepository.findOne(position).getId());
                intent.putExtra("title",bookRepository.findOne(position).getTitle());
                intent.putExtra("author",bookRepository.findOne(position).getAuthor());
                intent.putExtra("description",bookRepository.findOne(position).getDescription());
                intent.putExtra("image",bookRepository.findOne(position).getImage());
                startActivityForResult(intent,1);

                //startActivityWithResult
                //Room for local persistence
            }
        };
        listView.setOnItemClickListener(listener);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 1){
            if (resultCode == RESULT_OK){
                String title = data.getStringExtra("title2");
                String author = data.getStringExtra("author2");
                String description = data.getStringExtra("description2");
                int id = data.getIntExtra("id2",0);
                int image = data.getIntExtra("image2",0);
                Book book = new Book();
                book.setId(id);
                book.setTitle(title);
                book.setAuthor(author);
                book.setDescription(description);
                book.setImage(image);
                System.out.println("New booooooooook"+book);
                bookRepository.updateBook(book);
                bookListAdapter.updateReceiptsList(bookRepository.getBookList());
            }
        }
        if(requestCode == 2){
            if(resultCode == RESULT_OK){
                System.out.println("######################################");
                String title = data.getStringExtra("title");
                String author = data.getStringExtra("author");
                String description = data.getStringExtra("description");
                bookRepository.insert(title,author,description);
                bookListAdapter.updateReceiptsList(bookRepository.getBookList());
            }
        }
    }

    public void deleteBook(View view) {
        final int position = listView.getPositionForView((View) view.getParent());
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Are you sure you want to delete this book?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        System.out.println("deleteeeeee " + position);
                        Book book = new Book();
                        int idx  = bookRepository.findOne(position).getId();
                        book.setId(idx);
                        bookRepository.delete(book);
                        bookListAdapter.updateReceiptsList(bookRepository.getBookList());
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
        //System.out.println("deleteeeeee " + position);
        //Book book = new Book();
        //int id  = bookRepository.findOne(position).getId();
        //book.setId(id);
        //bookRepository.delete(book);
        //bookListAdapter.updateReceiptsList(bookRepository.getBookList());
    }
}
