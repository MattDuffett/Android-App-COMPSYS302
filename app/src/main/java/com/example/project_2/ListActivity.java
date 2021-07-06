package com.example.project_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_2.adapter.ItemList;
import com.example.project_2.model.Category;
import com.example.project_2.model.DataProvider;

public class ListActivity extends AppCompatActivity {

    //this will eventually go and be replaced by data from the intent.
    int[] itemIDs;

    class ViewHolder {
        //holds all the views for the activity

        //current activity is useful for initializing things.
        Activity context;
        Category category;

        ListView item_listview;
        ItemList item_list;
        TextView title_text;
        SearchView search_view;
        CardView home_button;
        String category_intent;
        String search_intent;

        public ViewHolder(Activity context) {
            this.context=context;
            initializeViews();
            setListeners();
            getIntents();


            //logic for changing the title for the list activity
            String title = "There are no results";
            category = null;
            if(category_intent==null){
                category_intent = "all";
            } else {
                category = Category.categories.get(Integer.parseInt(category_intent));
                category_intent = category.getName();
            }

            if(search_intent==null){
                search_intent="";
                if(category_intent=="all"){
                    itemIDs = new int[0];
                } else {
                    itemIDs = DataProvider.getCategory(category.getID());
                }
            } else {
                if(category_intent=="all"){
                    itemIDs=DataProvider.searchResults(search_intent,null);
                } else {
                    itemIDs = DataProvider.searchResults(search_intent,category);
                }
                search_intent = "for " + search_intent + ":";
            }
            title = "Showing " + category_intent + " results " + search_intent;

            title_text.setText(title);
            if(itemIDs!=null){
                item_list = new ItemList(context,itemIDs);
                item_listview.setAdapter(item_list);
            }
        }

        public void initializeViews(){
            //get all the views
            item_listview = findViewById(R.id.item_list_view);
            title_text = findViewById(R.id.list_title_text);
            search_view = findViewById(R.id.list_search_view);
            home_button = findViewById(R.id.list_home_button);
        }

        public void setListeners(){
            //set all the listeners
            search_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    search_view.setIconified(false);
                }
            });
            search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    //start a new list activity
                    Intent listActivity = new Intent(getBaseContext(), ListActivity.class);
                    //send the search term to new activity
                    listActivity.putExtra("SEARCH_TERM", query);
                    if(category != null){
                        //allows searches from within the list activity to carry forward the category filter
                        listActivity.putExtra("CATEGORY", Integer.toString(category.getID()));
                    }
                    startActivity(listActivity);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    return false;
                }
            });
            home_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //go to main activity
                    Intent mainActivity = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(mainActivity);
                    //slide animation
                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                }
            });
            item_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent ViewActivity = new Intent(getBaseContext(), ViewItemActivity.class);
                    ViewActivity.putExtra("ITEM_ID", Integer.toString(itemIDs[position]));
                    startActivity(ViewActivity);
                    //slide animation
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                }
            });
        }

        public void getIntents(){
            //intent information
            category_intent = getIntent().getStringExtra("CATEGORY");
            search_intent = getIntent().getStringExtra("SEARCH_TERM");
        }
    }

    ViewHolder vh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_list);

        vh = new ViewHolder(this);

        //idk if its okay to have this method in MainActivity if it's also called here? Should probably be in a separate class somewhere
        MainActivity.setListViewHeightBasedOnChildren(vh.item_listview);
    }

    @Override
    public void finish() {
        super.finish();
        //slide transition for back button
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}