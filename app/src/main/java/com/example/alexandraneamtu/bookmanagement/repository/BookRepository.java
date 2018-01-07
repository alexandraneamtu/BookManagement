package com.example.alexandraneamtu.bookmanagement.repository;

import android.content.Context;

import com.example.alexandraneamtu.bookmanagement.MainActivity;
import com.example.alexandraneamtu.bookmanagement.R;
import com.example.alexandraneamtu.bookmanagement.model.Book;
import com.example.alexandraneamtu.bookmanagement.utils.Observer;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import static android.media.CamcorderProfile.get;

/**
 * Created by alexandraneamtu on 29/10/2017.
 */

public class BookRepository extends Observer{

    private int id=1;
    private List<Observer> observers = new ArrayList<Observer>();
    private List<Book> bookList = new ArrayList<Book>();
    //private final AppDatabase db;

    private DatabaseReference db;

    public BookRepository(DatabaseReference db) {

        //this.bookList = bookList;
        Book book1 = new Book();
        book1.setTitle("The Hunger Games 1");
        book1.setAuthor("Suzanne Collins");
        book1.setDescription("The nation of Panem, formed from a post-apocalyptic North America, is a country that consists of a wealthy Capitol region surrounded by 12 poorer districts. Early in its history, a rebellion led by a 13th district against the Capitol resulted in its destruction and the creation of an annual televised event known as the Hunger Games. In punishment, and as a reminder of the power and grace of the Capitol, each district must yield one boy and one girl between the ages of 12 and 18 through a lottery system to participate in the games. The 'tributes' are chosen during the annual Reaping and are forced to fight to the death, leaving only one survivor to claim victory.\n" +
                "\n" +
               "When 16-year-old Katniss's young sister, Prim, is selected as District 12's female representative, Katniss volunteers to take her place. She and her male counterpart Peeta, are pitted against bigger, stronger representatives, some of whom have trained for this their whole lives. , she sees it as a death sentence. But Katniss has been close to death before. For her, survival is second nature.");
        book1.setImage(R.drawable.the_hunger_games);
        book1.setId(id);

        //id++;
        Book book2 = new Book();
        book2.setTitle("Pride and Prejudice");
        book2.setAuthor("Jane Austen");
        book2.setDescription("It is a truth universally acknowledged, that a single man in possession of a good fortune must be in want of a wife.” So begins Pride and Prejudice, Jane Austen’s witty comedy of manners—one of the most popular novels of all time—that features splendidly civilized sparring between the proud Mr. Darcy and the prejudiced Elizabeth Bennet as they play out their spirited courtship in a series of eighteenth-century drawing-room intrigues. Renowned literary critic and historian George Saintsbury in 1894 declared it the “most perfect, the most characteristic, the most eminently quintessential of its author’s works,” and Eudora Welty in the twentieth century described it as “irresistible and as nearly flawless as any fiction could be.");
        book2.setImage(R.drawable.pride_and_prejudice);
        book2.setId(id);


        //id++;
        Book book3 = new Book();
        book3.setTitle("Gone with the wind");
        book3.setAuthor("Margaret Mitchell");
        book3.setDescription("Gone with the Wind is a novel written by Margaret Mitchell, first published in 1936. The story is set in Clayton County, Georgia, and Atlanta during the American Civil War and Reconstruction era. It depicts the struggles of young Scarlett O'Hara, the spoiled daughter of a well-to-do plantation owner, who must use every means at her disposal to claw her way out of the poverty she finds herself in after Sherman's March to the Sea. A historical novel, the story is a Bildungsroman or coming-of-age story, with the title taken from a poem written by Ernest Dowson. \n" +
                "\n" +
                "Gone with the Wind was popular with American readers from the onset and was the top American fiction bestseller in the year it was published and in 1937. As of 2014, a Harris poll found it to be the second favorite book of American readers, just behind the Bible. More than 30 million copies have been printed worldwide.");
        book3.setImage(R.drawable.gone_with_the_wind);
        book3.setId(id);


        //id++;
        Book book4 = new Book();
        book4.setTitle("1984");
        book4.setAuthor("George Orwell");
        book4.setDescription("The year book_1984 has come and gone, but George Orwell's prophetic, nightmarish vision in 1949 of the world we were becoming is timelier than ever. book_1984 is still the great modern classic of \"negative utopia\" -a startlingly original and haunting novel that creates an imaginary world that is completely convincing, from the first sentence to the last four words. No one can deny the novel's hold on the imaginations of whole generations, or the power of its admonitions -a power that seems to grow, not lessen, with the passage of time.");
        book4.setImage(R.drawable.book_1984);
        book4.setId(id);

        //id++;
        Book book5 = new Book();
        book5.setTitle("The Great Gatsby");
        book5.setAuthor("F. Scott Fitzgerald");
        book5.setDescription("THE GREAT GATSBY, F. Scott Fitzgerald’s third book, stands as the supreme achievement of his career. This exemplary novel of the Jazz Age has been acclaimed by generations of readers. The story of the fabulously wealthy Jay Gatsby and his love for the beautiful Daisy Buchanan, of lavish parties on Long Island at a time when The New York Times noted “gin was the national drink and sex the national obsession,” is an exquisitely crafted tale of America in the 1920s.");
        book5.setImage(R.drawable.the_great_gatsby);
        book5.setId(id);


        this.db = db;

        //this.db.child(String.valueOf(book1.getId())).setValue(book1);
        //db.child(String.valueOf(book2.getId())).setValue(book2);
        //db.child(String.valueOf(book3.getId())).setValue(book3);
        //db.child(String.valueOf(book4.getId())).setValue(book4);
        //db.child(String.valueOf(book5.getId())).setValue(book5);

        //db2.child("users").child("PGBKVWlQ1eTYWhOLEUO0Me7ZuyK2").setValue("user");
        //db2.child("users").child("npbPDicEkRd0TcwiwAzuEkbL2YD3").setValue("admin");

        this.attach(this);
    }

    public void addBook(Book book){
        //this.bookList.clear();
        this.bookList.add(book);
        //notifyAllObservers();
    }

    public void clear(){
        this.bookList.clear();
    }

    public void setId(int id){
        this.id = id;
    }

    public Book findOne(int ID){
        return bookList.get(ID);
    }

    public void insert(String title,String author, String description){
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setDescription(description);
        book.setImage(R.drawable.book_ex);
        book.setId(id);
        id++;
        System.out.println("###############################IN REPO##############"+book);
        db.child(String.valueOf(book.getId())).setValue(book);
//        db.bookDao().insert(book);
        notifyAllObservers();
    }

    public List<Book> getBookList() {
        //return db.child("books");
        return this.bookList;
    }


    public void updateBook(Book book){
        db.child(String.valueOf(book.getId())).setValue(book);
        notifyAllObservers();
    }

    public void delete(Book book){
//        db.bookDao().delete(book);
        db.child(String.valueOf(book.getId())).removeValue();
        notifyAllObservers();
    }


    public void attach(Observer observer){
                observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }

    @Override
    public void update() {
        MainActivity.showRepositoryUpdated();
    }
}
