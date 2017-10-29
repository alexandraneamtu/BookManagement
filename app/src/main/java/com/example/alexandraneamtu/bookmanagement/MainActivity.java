package com.example.alexandraneamtu.bookmanagement;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.alexandraneamtu.bookmanagement.model.Book;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    List<Book> books = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.prepareBook);
        button.setOnClickListener((v)->{
            Intent intent = new Intent(MainActivity.this,PrepareBookActivity.class);
            startActivity(intent);
        });
        populateList();
        onBookSelected();
    }


    public void populateList(){

        Book book1 = new Book("The Hunger Games 1","Suzanne Collins","The nation of Panem, formed from a post-apocalyptic North America, is a country that consists of a wealthy Capitol region surrounded by 12 poorer districts. Early in its history, a rebellion led by a 13th district against the Capitol resulted in its destruction and the creation of an annual televised event known as the Hunger Games. In punishment, and as a reminder of the power and grace of the Capitol, each district must yield one boy and one girl between the ages of 12 and 18 through a lottery system to participate in the games. The 'tributes' are chosen during the annual Reaping and are forced to fight to the death, leaving only one survivor to claim victory.\n" +
                "\n" +
                "When 16-year-old Katniss's young sister, Prim, is selected as District 12's female representative, Katniss volunteers to take her place. She and her male counterpart Peeta, are pitted against bigger, stronger representatives, some of whom have trained for this their whole lives. , she sees it as a death sentence. But Katniss has been close to death before. For her, survival is second nature.",
                R.drawable.the_hunger_games);

        Book book2 = new Book("Pride and Prejudice","Jane Austen","It is a truth universally acknowledged, that a single man in possession of a good fortune must be in want of a wife.” So begins Pride and Prejudice, Jane Austen’s witty comedy of manners—one of the most popular novels of all time—that features splendidly civilized sparring between the proud Mr. Darcy and the prejudiced Elizabeth Bennet as they play out their spirited courtship in a series of eighteenth-century drawing-room intrigues. Renowned literary critic and historian George Saintsbury in 1894 declared it the “most perfect, the most characteristic, the most eminently quintessential of its author’s works,” and Eudora Welty in the twentieth century described it as “irresistible and as nearly flawless as any fiction could be.",
                R.drawable.pride_and_prejudice);

        Book book3 = new Book("Gone with the Wind"," Margaret Mitchell","Gone with the Wind is a novel written by Margaret Mitchell, first published in 1936. The story is set in Clayton County, Georgia, and Atlanta during the American Civil War and Reconstruction era. It depicts the struggles of young Scarlett O'Hara, the spoiled daughter of a well-to-do plantation owner, who must use every means at her disposal to claw her way out of the poverty she finds herself in after Sherman's March to the Sea. A historical novel, the story is a Bildungsroman or coming-of-age story, with the title taken from a poem written by Ernest Dowson. \n" +
                "\n" +
                "Gone with the Wind was popular with American readers from the onset and was the top American fiction bestseller in the year it was published and in 1937. As of 2014, a Harris poll found it to be the second favorite book of American readers, just behind the Bible. More than 30 million copies have been printed worldwide.",R.drawable.gone_with_the_wind);

        books.add(book1);
        books.add(book2);
        books.add(book3);

        BookListAdapter bookListAdapter;
        bookListAdapter = new BookListAdapter(this,R.layout.list_item,books);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(bookListAdapter);

        /*
        ArrayAdapter<Book> adapter = new ArrayAdapter<Book>(this, R.layout.list_item,R.id.text,books);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);


        AdapterPerson adbPerson;
        ArrayList<Person> myListItems  = new ArrayList<Person>();

//then populate myListItems

        adbPerson= new AdapterPerson (youractivity.this, 0, myListItems);
        listview.setAdapter(adbPerson);



        String[] myItems = {"Blue","Green","Red"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item,R.id.text,myItems);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        */
    }

    public void onBookSelected(){
        ListView listView = (ListView) findViewById(R.id.listView);
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                intent.putExtra("title",books.get(position).getTitle());
                intent.putExtra("author",books.get(position).getAuthor());
                intent.putExtra("description",books.get(position).getDescription());
                intent.putExtra("image",books.get(position).getImage());
                startActivity(intent);
            }
        };
        listView.setOnItemClickListener(listener);
    }



}
