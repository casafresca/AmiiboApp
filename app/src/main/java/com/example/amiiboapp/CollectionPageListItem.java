package com.example.amiiboapp;

public class CollectionPageListItem {
    private String amiiboImage;
    private String amiiboName;
    private String dateBought;
    private String price;

    public CollectionPageListItem(String amiiboImage, String amiiboName) {
        this.amiiboImage = amiiboImage;
        this.amiiboName = amiiboName;
    }

    public String getAmiiboImage() {
        return amiiboImage;
    }

    public String getAmiiboName() {
        return amiiboName;
    }

    public String getDateBought() {
        return dateBought;
    }

    public void setDateBought(String newDate) {
        this.dateBought = newDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String newPrice) {
        this.price = newPrice;
    }
}
