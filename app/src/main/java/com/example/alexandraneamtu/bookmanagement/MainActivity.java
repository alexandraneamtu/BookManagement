package com.example.alexandraneamtu.bookmanagement;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.MotionEvent;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alexandraneamtu.bookmanagement.model.Book;
import com.example.alexandraneamtu.bookmanagement.repository.BookRepository;

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
        System.out.println("deleteeeeee " + position);
        Book book = new Book();
        int id  = bookRepository.findOne(position).getId();
        book.setId(id);
        //String title = bookRepository.findOne(position).getTitle();
        //String author = bookRepository.findOne(position).getAuthor();
        //Str
        bookRepository.delete(book);
        bookListAdapter.updateReceiptsList(bookRepository.getBookList());
    }
}
