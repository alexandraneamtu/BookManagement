package com.example.alexandraneamtu.bookmanagement.model;

/**
 * Created by alexandraneamtu on 29/10/2017.
 */

public class Book {

    private String title;
    private String author;
    private String description;
    private Integer image;

    public Book(String title, String author, String description, Integer image) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.image = image;
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
                "title='" + title + '\'' +
                ", image=" + image +
                '}';
    }
}
