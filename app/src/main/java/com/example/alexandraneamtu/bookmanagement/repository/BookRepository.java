package com.example.alexandraneamtu.bookmanagement.repository;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.alexandraneamtu.bookmanagement.R;
import com.example.alexandraneamtu.bookmanagement.model.Book;
import com.example.alexandraneamtu.bookmanagement.db.AppDatabase;

import java.util.List;

import static android.media.CamcorderProfile.get;

/**
 * Created by alexandraneamtu on 29/10/2017.
 */

public class BookRepository {

    //private List<Book> bookList;
    private final AppDatabase db;

    public BookRepository(Context context) {

        //this.bookList = bookList;
        Book book1 = new Book();
        book1.setTitle("The Hunger Games 1");
        book1.setAuthor("Suzanne Collins");
        book1.setDescription("The nation of Panem, formed from a post-apocalyptic North America, is a country that consists of a wealthy Capitol region surrounded by 12 poorer districts. Early in its history, a rebellion led by a 13th district against the Capitol resulted in its destruction and the creation of an annual televised event known as the Hunger Games. In punishment, and as a reminder of the power and grace of the Capitol, each district must yield one boy and one girl between the ages of 12 and 18 through a lottery system to participate in the games. The 'tributes' are chosen during the annual Reaping and are forced to fight to the death, leaving only one survivor to claim victory.\n" +
                "\n" +
               "When 16-year-old Katniss's young sister, Prim, is selected as District 12's female representative, Katniss volunteers to take her place. She and her male counterpart Peeta, are pitted against bigger, stronger representatives, some of whom have trained for this their whole lives. , she sees it as a death sentence. But Katniss has been close to death before. For her, survival is second nature.");
        book1.setImage(R.drawable.the_hunger_games);



        db = Room.
                databaseBuilder(context, AppDatabase.class, "bookManagement-database")
                // allow queries on the main thread.
                // Don't do this on a real app! See PersistenceBasicSample for an example.
                .allowMainThreadQueries()
                .build();

        db.bookDao().deleteAll();
        db.bookDao().insert(book1);

    }


    public Book findOne(int ID){
        //System.out.println("repooooooo"+ID);
        return db.bookDao().findByID(ID);
    }

    public List<Book> getBookList() {
        return db.bookDao().getAll();
    }


    public void updateBook (Book book){
        //System.out.println("MARE UPDATE------------------------------"+book);
        db.bookDao().update(book);
    }
}
