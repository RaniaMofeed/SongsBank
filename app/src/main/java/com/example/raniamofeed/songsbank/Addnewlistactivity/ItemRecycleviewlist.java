package com.example.raniamofeed.songsbank.Addnewlistactivity;

/**
 * Created by Rania Mofeed on 5/23/2019.
 */

public class ItemRecycleviewlist {

    private int mresourceimage;
    private String mname;
    private String mdate;

    ItemRecycleviewlist(int resourceimage, String name, String date)
    {
        mresourceimage=resourceimage;
        mname=name;
        mdate=date;
    }
    public int getMresourceimage()
    {
        return mresourceimage;

    }
    public String getMname()
    {
        return mname;
    }
    public String getMdate()
    {
        return mdate;
    }
}


