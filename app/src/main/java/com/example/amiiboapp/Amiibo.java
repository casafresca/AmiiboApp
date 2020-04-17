package com.example.amiiboapp;

public class Amiibo {
    private String Title;
    private String Series;
    private String RealeseDate;
    private int Thumbnail;

    public Amiibo() {

    }

    public Amiibo(String title, String series, String realeseDate, int thumbnail) {
        Title = title;
        Series = series;
        RealeseDate = realeseDate;
        Thumbnail = thumbnail;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSeries() {
        return Series;
    }

    public void setSeries(String series) {
        Series = series;
    }

    public String getRealeseDate() {
        return RealeseDate;
    }

    public void setRealeseDate(String realeseDate) {
        RealeseDate = realeseDate;
    }
}
