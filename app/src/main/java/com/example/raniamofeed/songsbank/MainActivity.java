package com.example.raniamofeed.songsbank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.raniamofeed.songsbank.Saved_Lists_Activity.Lists;
import com.example.raniamofeed.songsbank.SearchDev.activities.Search;
import com.example.raniamofeed.songsbank.login_Register.Login;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    TextView song;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        song =(TextView)findViewById(R.id.song);
        sharedPreferences = getSharedPreferences("logout", Context.MODE_PRIVATE);

        song.setTypeface(Typeface.createFromAsset(getAssets(),"Lemonada-Bold.ttf"));
        Animation myanim= AnimationUtils.loadAnimation(this,R.anim.mytransition);
        song.startAnimation(myanim);
        final Intent intent=new Intent(this,Login.class);
        Thread timer=new Thread(){
            public void run()
            {
                try
                {
                    sleep(5000);
                }catch (InterruptedException e)
                {
                    e.printStackTrace();

                }
                finally {
//
                    if ( sharedPreferences.getBoolean("savelogin", false)){
                        HashMap<String, String> postData1 = new HashMap<>();
                        postData1.put("username", sharedPreferences.getString("username", ""));
                        postData1.put("password", sharedPreferences.getString("password", ""));
                        Intent homeIntent = new Intent(MainActivity.this, Home.class);
                         //homeIntent.putExtra("contact", sharedPreferences.getString("id", ""));
                         Url.setIduser(sharedPreferences.getString("id", ""));
                        startActivity(homeIntent);
                        finish();
                    }else
                        startActivity(intent);
                     //   startActivity(new Intent(MainActivity.this,Login.class));
                    finish();
                }
            }

        };
        timer.start();

    }
}
