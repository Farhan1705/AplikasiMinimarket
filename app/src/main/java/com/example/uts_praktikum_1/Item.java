package com.example.uts_praktikum_1;

public class Item {
    private String name;
    private String price;
    private String description;
    private int photo;
    private String category;

    public Item(String name, String description, int photo, String category, String price) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.photo = photo;
        this.category = category;
    }

    public String getName() { return name; }
    public String getPrice() { return price; }
    public String getDescription() { return description; }
    public int getPhoto() { return photo; }
    public String getCategory() { return category; }
}