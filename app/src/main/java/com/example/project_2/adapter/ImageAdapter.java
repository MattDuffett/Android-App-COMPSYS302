package com.example.project_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.project_2.R;

import java.util.ArrayList;

public class ImageAdapter extends PagerAdapter {
    //Adapter to populate viewpager in view item activity.

    private Context context;
    //list of images to display, note it can be any length and not all items need to have the same number of images.
    private ArrayList<Integer> imageList;

    public ImageAdapter(Context context, ArrayList<Integer> imageList){
        this.context = context;
        this.imageList=imageList;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //R.layout.image_view_pager_item is the layout used for each image
        View view = LayoutInflater.from(context).inflate(R.layout.image_view_pager_item, container, false);

        //gets the imageview from the layout
        ImageView imageView = view.findViewById(R.id.view_item_imageview);

        imageView.setImageResource(imageList.get(position));

        //Makes sure that there is no out of bounds exception
        container.addView(view, container.getChildCount() > position ? position : container.getChildCount());

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
