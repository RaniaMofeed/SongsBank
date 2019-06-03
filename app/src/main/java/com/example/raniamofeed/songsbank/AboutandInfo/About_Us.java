package com.example.raniamofeed.songsbank.AboutandInfo;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.raniamofeed.songsbank.R;

public class About_Us extends AppCompatActivity {
    TextView shape1,shape2,shape3,shape4;
    TextView text1 ,text2,text3,text4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__us);

        shape1=(TextView)findViewById(R.id.f1);
        shape1.setTypeface(Typeface.createFromAsset(getAssets(),"Italian Mosaic Ornaments.ttf"));

        shape2=(TextView)findViewById(R.id.f2);
        shape2.setTypeface(Typeface.createFromAsset(getAssets(),"Italian Mosaic Ornaments.ttf"));

        shape3=(TextView)findViewById(R.id.f3);
        shape3.setTypeface(Typeface.createFromAsset(getAssets(),"Italian Mosaic Ornaments.ttf"));

        shape4=(TextView)findViewById(R.id.f4);
        shape4.setTypeface(Typeface.createFromAsset(getAssets(),"Italian Mosaic Ornaments.ttf"));


        //Set Text

        text1=(TextView)findViewById(R.id.N1);
        text1.setTypeface(Typeface.createFromAsset(getAssets(),"Yekan-Bakh-EN-04-Regular.ttf"));

        text2=(TextView)findViewById(R.id.Nn2);
        text2.setTypeface(Typeface.createFromAsset(getAssets(),"Yekan-Bakh-EN-04-Regular.ttf"));

        text3=(TextView)findViewById(R.id.Nm3);
        text3.setTypeface(Typeface.createFromAsset(getAssets(),"Yekan-Bakh-EN-04-Regular.ttf"));

        text4=(TextView)findViewById(R.id.Nm4);
        text4.setTypeface(Typeface.createFromAsset(getAssets(),"Yekan-Bakh-EN-04-Regular.ttf"));
    }
}
