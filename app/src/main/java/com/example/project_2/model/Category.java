package com.example.project_2.model;

import java.util.ArrayList;

public class Category {
    //class to hold all the information about categories

    private String name;
    private int imageID;

    //every category has a unique id, which is useful for when selecting a category from the listview
    private static int currentID = 0;
    private int id;
    private String description;

    //list of all categories that have been created.
    public static ArrayList<Category> categories = new ArrayList<Category>();


    public Category(String name, int imageID, String description){
        this.name=name;
        this.imageID=imageID;
        this.description=description;
        //unique id.
        this.id = currentID++;
        if (getCategory(name)==null){
            //check if category already exists and if not add it to the list of categories.
            categories.add(this);
        }
    }

    public String getName(){
        return name;
    }

    public int getImageID(){
        return imageID;
    }

    public int getID(){
        return id;
    }

    public String getDescription(){
        return description;
    }


    //useful function for getting a category given only it's name
    public static Category getCategory(String name){
        for (Category c : categories) {
            if(c.getName() == name){
                return c;
            }
        }
        return null;
    }

    //get the category from it's ID, this is used most often
    public static Category getCategory(int id){
        return categories.get(id);
    }

    //get all the category names. used for populating listview
    public static String[] getNamesList(){
        String[] list = new String[categories.size()];
        for(int i = 0; i < categories.size(); i++){
            list[i] = categories.get(i).getName();
        }
        return list;
    }
}