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

        Book book2 = new Book();
        book2.setTitle("Pride and Prejudice");
        book2.setAuthor("Jane Austen");
        book2.setDescription("It is a truth universally acknowledged, that a single man in possession of a good fortune must be in want of a wife.” So begins Pride and Prejudice, Jane Austen’s witty comedy of manners—one of the most popular novels of all time—that features splendidly civilized sparring between the proud Mr. Darcy and the prejudiced Elizabeth Bennet as they play out their spirited courtship in a series of eighteenth-century drawing-room intrigues. Renowned literary critic and historian George Saintsbury in 1894 declared it the “most perfect, the most characteristic, the most eminently quintessential of its author’s works,” and Eudora Welty in the twentieth century described it as “irresistible and as nearly flawless as any fiction could be.");
        book2.setImage(R.drawable.pride_and_prejudice);

        Book book3 = new Book();
        book3.setTitle("Gone with the wind");
        book3.setAuthor("Margaret Mitchell");
        book3.setDescription("Gone with the Wind is a novel written by Margaret Mitchell, first published in 1936. The story is set in Clayton County, Georgia, and Atlanta during the American Civil War and Reconstruction era. It depicts the struggles of young Scarlett O'Hara, the spoiled daughter of a well-to-do plantation owner, who must use every means at her disposal to claw her way out of the poverty she finds herself in after Sherman's March to the Sea. A historical novel, the story is a Bildungsroman or coming-of-age story, with the title taken from a poem written by Ernest Dowson. \n" +
                "\n" +
                "Gone with the Wind was popular with American readers from the onset and was the top American fiction bestseller in the year it was published and in 1937. As of 2014, a Harris poll found it to be the second favorite book of American readers, just behind the Bible. More than 30 million copies have been printed worldwide.");
        book3.setImage(R.drawable.gone_with_the_wind);


        Book book4 = new Book();
        book4.setTitle("1984");
        book4.setAuthor("George Orwell");
        book4.setDescription("The year book_1984 has come and gone, but George Orwell's prophetic, nightmarish vision in 1949 of the world we were becoming is timelier than ever. book_1984 is still the great modern classic of \"negative utopia\" -a startlingly original and haunting novel that creates an imaginary world that is completely convincing, from the first sentence to the last four words. No one can deny the novel's hold on the imaginations of whole generations, or the power of its admonitions -a power that seems to grow, not lessen, with the passage of time.");
        book4.setImage(R.drawable.book_1984);


        Book book5 = new Book();
        book5.setTitle("The Great Gatsby");
        book5.setAuthor("F. Scott Fitzgerald");
        book5.setDescription("THE GREAT GATSBY, F. Scott Fitzgerald’s third book, stands as the supreme achievement of his career. This exemplary novel of the Jazz Age has been acclaimed by generations of readers. The story of the fabulously wealthy Jay Gatsby and his love for the beautiful Daisy Buchanan, of lavish parties on Long Island at a time when The New York Times noted “gin was the national drink and sex the national obsession,” is an exquisitely crafted tale of America in the 1920s.");
        book5.setImage(R.drawable.the_great_gatsby);




        db = Room.
                databaseBuilder(context, AppDatabase.class, "bookManagement-database")
                // allow queries on the main thread.
                // Don't do this on a real app! See PersistenceBasicSample for an example.
                .allowMainThreadQueries()
                .build();

        //db.bookDao().deleteAll();
        //db.bookDao().insert(book1);
        //db.bookDao().insert(book2);
        //db.bookDao().insert(book3);
        //db.bookDao().insert(book4);
        //db.bookDao().insert(book5);

    }


    public Book findOne(int ID){
        //System.out.println("repooooooo"+ID);
        return db.bookDao().findByID(ID);
    }

    public void insert(String title,String author, String description){
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setDescription(description);
        book.setImage(R.drawable.book_1984);
        System.out.println("###############################IN REPO##############"+book);
        db.bookDao().insert(book);
    }

    public List<Book> getBookList() {
        return db.bookDao().getAll();
    }


    public void updateBook (Book book){
        db.bookDao().update(book);
    }

    public void delete(Book book){
        db.bookDao().delete(book);
    }
}
