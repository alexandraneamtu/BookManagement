package com.example.alexandraneamtu.bookmanagement;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexandraneamtu.bookmanagement.model.Book;

import java.util.List;

/**
 * Created by alexandraneamtu on 29/10/2017.
 */

public class BookListAdapter extends ArrayAdapter<Book> {

    private Activity activity;
    private List<Book> books;
    private static LayoutInflater inflater = null;


    public BookListAdapter(Activity activity, int resource, List<Book> bookList) {
        super(activity, resource, bookList);
        try{
            this.activity = activity;
            this.books = bookList;

            inflater = activity.getLayoutInflater();
        }
        catch (Exception  e){}
    }


    @Override
    public int getCount() {
        return books.size();
    }


    public Book getItem(Book position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public static class ViewHolder {
        public TextView display_name;
        public ImageView display_image;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.list_item, null);
                holder = new ViewHolder();

                holder.display_name = (TextView) vi.findViewById(R.id.booktitle);
                holder.display_image = (ImageView) vi.findViewById(R.id.bookimage);


                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }



            holder.display_name.setText(books.get(position).getTitle());
            holder.display_image.setImageResource(books.get(position).getImage());


        } catch (Exception e) {


        }
        return vi;
    }
}
