package com.example.raniamofeed.songsbank.Saved_Lists_Activity.Model;

/**
 * Created by Rania Mofeed on 10/27/2018.
 */

public class MyWish {
    public String title;
    public String content;
    public String recordDate;
    public int itemId;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }
}
