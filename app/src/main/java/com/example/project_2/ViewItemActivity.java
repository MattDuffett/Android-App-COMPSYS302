package com.example.project_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_2.adapter.ImageAdapter;
import com.example.project_2.model.Category;
import com.example.project_2.model.DataProvider;
import com.example.project_2.model.Item;

public class ViewItemActivity extends AppCompatActivity {

    class ViewHolder {
        //holds all the views

        //current activity is useful
        Activity context;

        TextView title;
        TextView price;
        TextView description;
        TextView category_description;
        CardView home_button;
        ImageButton left_nav;
        ImageButton right_nav;
        ViewPager viewPager;
        ImageAdapter imageAdapter;

        String item_ID;

        public ViewHolder(Activity context) {
            this.context = context;
            initializeItems();
            setListeners();
            getIntents();

            //make sure intent information is given - this is to avoid errors on app startup
            if (item_ID != null) {
                //increment popularity of item when it is visited
                DataProvider.incPopularity(Integer.parseInt(item_ID));

                //set item information
                title.setText(Item.getItem(Integer.parseInt(item_ID)).getTitle());
                price.setText("$" + Item.getItem(Integer.parseInt(item_ID)).getPrice());
                description.setText(Item.getItem(Integer.parseInt(item_ID)).getDescription());
                category_description.setText(Item.getItem(Integer.parseInt(item_ID)).getCategory().getDescription());

                //connect view pager to adapter
                imageAdapter = new ImageAdapter(context, Item.getItem(Integer.parseInt(item_ID)).getImageIDs());
                viewPager.setAdapter(imageAdapter);

                //add some padding between images
                viewPager.setClipToPadding(false);
                viewPager.setPageMargin(40);
                viewPager.setPadding(16, 0, 16, 0);
            }
        }

        public void initializeItems() {
            //find all the views
            price = findViewById(R.id.view_item_price);
            description = findViewById(R.id.view_item_description);
            category_description = findViewById(R.id.view_item_category_description);
            title = findViewById(R.id.view_item_title);
            home_button = findViewById(R.id.view_home_button);
            left_nav = findViewById(R.id.viewpager_left_nav);
            right_nav = findViewById(R.id.viewpager_right_nav);
            viewPager = findViewById(R.id.view_item_viewpager);
        }

        public void setListeners() {
            //set all the listeners

            //left image button
            left_nav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.arrowScroll(View.FOCUS_LEFT);
                }
            });
            //right image button
            right_nav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.arrowScroll(View.FOCUS_RIGHT);
                }
            });
            //listener needs to be here even though they are empty
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });

            home_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //when home button is clicked go to mainActivity
                    Intent mainActivity = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(mainActivity);
                    //slide animation
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                }
            });
        }

        //get intent information
        public void getIntents(){
            //item ID is supplied which makes things easy
            item_ID = getIntent().getStringExtra("ITEM_ID");
        }
    }

    ViewHolder vh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_view_item);

        vh = new ViewHolder(this);
    }

    @Override
    public void finish() {
        super.finish();
        //slide animation when back button pressed
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}