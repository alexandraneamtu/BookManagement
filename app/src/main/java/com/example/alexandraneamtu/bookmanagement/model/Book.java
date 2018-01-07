package com.example.alexandraneamtu.bookmanagement.model;


import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by alexandraneamtu on 29/10/2017.
 */

@IgnoreExtraProperties
public class Book {

    //public static final String TABLE_NAME = "book";

    //@PrimaryKey(autoGenerate = true)
    private int id;

    //@ColumnInfo(name = "title")
    private String title;
    //@ColumnInfo(name = "author")
    private String author;
    //@ColumnInfo(name = "description")
    private String description;
    //@ColumnInfo(name = "image")
    private Integer image;


    public Book() {
    }

    /*
    public Book(String title, String author, String description, Integer image) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.image = image;
    }
    */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image +
                '}';
    }
}
