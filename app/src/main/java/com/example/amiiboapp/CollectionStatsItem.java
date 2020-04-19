package com.example.amiiboapp;

public class CollectionStatsItem {
    private int mProgressBarStatus;
    private String mGameSeriesText;
    private String mPercentageText;

    public CollectionStatsItem(int progressStatus, String gameSeriesText, String percentageText) {
        mProgressBarStatus = progressStatus;
        mGameSeriesText = gameSeriesText;
        mPercentageText = percentageText;
    }

    public int getProgressBarStatus() {
        return mProgressBarStatus;
    }

    public String getGameSeriesText() {
        return mGameSeriesText;
    }

    public String getPercentageText() {
        return mPercentageText;
    }
}
