package com.example.project_2.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_2.R;
import com.example.project_2.model.Category;

public class CategoryList extends ArrayAdapter{
    //Adapter for main activity listview of categories
    private Activity context;

    public CategoryList(Activity context) {
        super(context, R.layout.category_card, Category.getNamesList());
        this.context=context;
    }

    //populates each category card gui with data.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View category =convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            //R.layout.category_card is the layout used for each category
            category = inflater.inflate(R.layout.category_card,null,true);
        TextView textViewName = (TextView) category.findViewById(R.id.category_textview);
        ImageView imageView = (ImageView) category.findViewById(R.id.category_imageview);

        //gets category info from list of categories in Category class.
        textViewName.setText(Category.categories.get(position).getName());
        imageView.setImageResource(Category.categories.get(position).getImageID());
        return category;
    }
}
