package com.example.project_2.model;

import com.example.project_2.R;

import java.util.ArrayList;
import java.util.List;

public class Item {
    //contains all the information for items.

    //each item is given a unique ID.
    private static int currentID = 0;
    private int id;
    private Category category;
    private String title;
    private ArrayList<Integer> imageIDs;
    private String description;
    private double price;
    private int popularity;

    public static ArrayList<Item> items = new ArrayList<Item>();

    public Item(Category category, String title, ArrayList<Integer> imageIDs, String description, double price, int popularity) {
        this.category = category;
        this.title=title;
        this.imageIDs=imageIDs;
        this.description=description;
        this.price=price;
        this.popularity = popularity;
        //ID is given here, it's important that the ID here matches up with the position of the item in the list, which is why Items can only be initialized from within the DataProvider method.
        this.id = currentID++;
    }

    public String getTitle(){
        return title;
    }

    public ArrayList<Integer> getImageIDs() {
        return imageIDs;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice(){
        return price;
    }

    public Category getCategory(){
        return category;
    }

    public int getPopularity(){
        return popularity;
    }

    public void incPopularity(){
        popularity++;
    }

    public int getID() {
        return id;
    }

    public void addImageID(int imageID){
        imageIDs.add(imageID);
    }

    public static Item getItem(int id){
        return items.get(id);
    }

    //returns a list of the item titles
    public static String[] getTitlesList(){
        String[] list = new String[items.size()];
        for(int i = 0; i < items.size(); i++){
            list[i] = items.get(i).getTitle();
        }
        return list;
    }

    //returns a list of the item titles from the list of item ids supplied.
    public static String[] getTitlesList(int[] ids){
        String[] list = new String[ids.length];
        for(int i = 0; i < ids.length; i++){
            list[i] = items.get(i).getTitle();
        }
        return list;
    }

    //returns a list of all item prices
    public static double[] getPricesList(){
        double[] list = new double[items.size()];
        for(int i = 0; i < items.size(); i++){
            list[i] = items.get(i).getPrice();
        }
        return list;
    }

    //returns a list of only the prices of items with id specified in ids.
    public static double[] getPricesList(int[] ids){
        double[] list = new double[ids.length];
        for(int i = 0; i < ids.length; i++){
            list[i] = items.get(i).getPrice();
        }
        return list;
    }

    //adds item to list, should only be called from method in DataProvider to ensure that IDs match up with position in list.
    public static void addItem(Item item){
        String[] titles = getTitlesList();
        for (int i = 0; i < titles.length; i++) {
            if (item.getTitle() == titles[i]) {
                return;
            }
        }
        items.add(item);
    }
}
