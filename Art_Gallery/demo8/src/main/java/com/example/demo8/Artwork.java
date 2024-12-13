package com.example.demo8;

public class Artwork {
    private String title;
    private String description;
    private String artist;
    private String price;
    private String imageUrl;
    private int stockAvailable;

    public Artwork(String title, String description, String artist, String price, String imageUrl, int artworkAvailable) {
        this.title = title;
        this.description = description;
        this.artist = artist;
        this.price = price;
        this.imageUrl = imageUrl;
        this.stockAvailable = artworkAvailable;
    }

    public String getTitle() {
        return title;
    }

    public int getStockAvailable() {
        return stockAvailable;
    }

    public String getDescription() {
        return description;
    }

    public String getArtist() {
        return artist;
    }

    public String getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}