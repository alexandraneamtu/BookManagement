package com.example.alexandraneamtu.bookmanagement;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.alexandraneamtu.bookmanagement.model.Book;
import com.example.alexandraneamtu.bookmanagement.repository.BookRepository;


public class MainActivity extends AppCompatActivity {
    //public BookRepository getBookRepository() {
        //return bookRepository;
    //}

    private BookRepository bookRepository;// = new BookRepository(getApplicationContext());

    private static BookListAdapter bookListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookRepository = new BookRepository(getApplicationContext());

        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.prepareBook);
        button.setOnClickListener((v)->{
            Intent intent = new Intent(MainActivity.this,PrepareBookActivity.class);
            startActivity(intent);
        });
        bookListAdapter = new BookListAdapter(this,R.layout.list_item, bookRepository.getBookList());
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(bookListAdapter);
        onBookSelected();
    }


    public void onBookSelected(){
        ListView listView = (ListView) findViewById(R.id.listView);
        //List<Book> books = bookRepository.getBookList();
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("-----------"+Integer.toString(position));
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
                bookListAdapter.notifyDataSetChanged();
            }
        }
    }





}
