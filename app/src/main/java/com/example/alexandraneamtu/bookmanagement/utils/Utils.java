package com.example.alexandraneamtu.bookmanagement.utils;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by alexandraneamtu on 06/01/2018.
 */

public class Utils {
    private static FirebaseDatabase mDatabase;

    public static FirebaseDatabase getDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);
        }
        return mDatabase;
    }

}