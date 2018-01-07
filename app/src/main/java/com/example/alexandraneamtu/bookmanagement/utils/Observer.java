package com.example.alexandraneamtu.bookmanagement.utils;

import com.example.alexandraneamtu.bookmanagement.repository.BookRepository;

/**
 * Created by alexandraneamtu on 08/01/2018.
 */

public abstract class Observer {
    protected BookRepository repository;
    public abstract void update();
}