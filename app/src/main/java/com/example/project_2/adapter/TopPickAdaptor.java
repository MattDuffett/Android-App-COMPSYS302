package com.example.project_2.adapter;

import android.content.Context;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_2.R;
import com.example.project_2.model.Item;

import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2.R;
import com.example.project_2.model.Item;

import java.util.List;

public class TopPickAdaptor extends RecyclerView.Adapter<TopPickAdaptor.ViewHolder> {
    //adapter for top picks recycler view.

    private onItemListener onItemListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //implements onclickListener so that we can click on items and switch to view item activity

        public TextView itemTitle;
        public TextView itemPrice;
        public ImageView imageView;

        //instance of interface
        onItemListener onItemListener;

        public ViewHolder(View item, onItemListener onItemListener) {
            super(item);
            this.onItemListener=onItemListener;
            //initialize views
            itemTitle = (TextView) item.findViewById(R.id.item_title);
            itemPrice = (TextView) item.findViewById(R.id.item_price);
            imageView = (ImageView) item.findViewById(R.id.item_imageview);

            item.setOnClickListener(this);
        }

        //onclick listener - calls interface method
        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }

    private List<Integer> mItems;
    private Context mContext;

    public TopPickAdaptor(List<Integer> topPicks, onItemListener onItemListener) {
        //inputs list of item IDs which are in the order that it will display
        this.onItemListener=onItemListener;
        mItems = topPicks;
    }

    @Override
    public TopPickAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);

        //R.layout.item_card is the layout used for each item
        View itemView = inflater.inflate(R.layout.item_card, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(itemView, onItemListener);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(TopPickAdaptor.ViewHolder holder, int position) {
        // Get the data model based on position
        Item item = Item.getItem(mItems.get(position));

        // Set item views based on your views and data model
        TextView itemTitle = holder.itemTitle;
        itemTitle.setText(item.getTitle());
        TextView itemPrice = holder.itemPrice;
        itemPrice.setText("$" + String.format("%.2f",item.getPrice()));
        ImageView imageView = holder.imageView;
        imageView.setImageResource(item.getImageIDs().get(0));

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    //interface so that onclick listener can be implemented
    public interface onItemListener{
        void onItemClick(int position);
    }

}
