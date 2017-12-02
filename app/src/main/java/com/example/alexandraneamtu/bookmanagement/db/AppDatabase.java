package com.example.alexandraneamtu.bookmanagement.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.alexandraneamtu.bookmanagement.model.Book;
import com.example.alexandraneamtu.bookmanagement.model.BookDao;

/**
 * Created by alexandraneamtu on 01/12/2017.
 */

@Database(entities = {Book.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    //private static AppDatabase INSTANCE;

    public abstract BookDao bookDao();

    /*
    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.
                            databaseBuilder(context.getApplicationContext(), AppDatabase.class, "bookManagement-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
            //INSTANCE.populateInitialData();
        }
        return INSTANCE;
    }


    private void populateInitialData() {
        if(bookDao().count() == 0){

            Book book1 = new Book();
            book1.setTitle("The Hunger Games 1");
            book1.setAuthor("Suzanne Collins");
            book1.setDescription("The nation of Panem, formed from a post-apocalyptic North America, is a country that consists of a wealthy Capitol region surrounded by 12 poorer districts. Early in its history, a rebellion led by a 13th district against the Capitol resulted in its destruction and the creation of an annual televised event known as the Hunger Games. In punishment, and as a reminder of the power and grace of the Capitol, each district must yield one boy and one girl between the ages of 12 and 18 through a lottery system to participate in the games. The 'tributes' are chosen during the annual Reaping and are forced to fight to the death, leaving only one survivor to claim victory.\n" +
                    "\n" +
                    "When 16-year-old Katniss's young sister, Prim, is selected as District 12's female representative, Katniss volunteers to take her place. She and her male counterpart Peeta, are pitted against bigger, stronger representatives, some of whom have trained for this their whole lives. , she sees it as a death sentence. But Katniss has been close to death before. For her, survival is second nature.");
            book1.setImage(R.drawable.the_hunger_games);

            //Book book = new Book();
            beginTransaction();
            try{
                bookDao().insert(book1);
                //bookDao().insert(book2);
                //bookDao().insert(book3);
                setTransactionSuccessful();
            }finally {
                endTransaction();
            }
        }
    }


    public static void destroyInstance() {
        INSTANCE = null;
    }

    */
}
