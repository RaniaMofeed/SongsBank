package com.example.raniamofeed.songsbank.AboutandInfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.raniamofeed.songsbank.Addnewlistactivity.AddNewList;
import com.example.raniamofeed.songsbank.ForgetAndContact.ContactUS;
import com.example.raniamofeed.songsbank.Home;
import com.example.raniamofeed.songsbank.R;
import com.example.raniamofeed.songsbank.Url;
import com.example.raniamofeed.songsbank.savedsongs.SavedSongs;
import com.example.raniamofeed.songsbank.SearchDev.activities.Search;
import com.example.raniamofeed.songsbank.SuggestionSongs;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Info extends AppCompatActivity implements View.OnClickListener {
    TextView Totalsong,savedsong,yoursong;
    private RequestQueue requestQueue;
    private ResideMenu resideMenu;
    //        Items of menu
    private ResideMenuItem itemHome;
    private ResideMenuItem itemSearch;
    private ResideMenuItem itemAddNewList;
    private ResideMenuItem itemInfo;
    private ResideMenuItem itemAddnewSong;
    private ResideMenuItem itemSavedSongd;
    private ResideMenuItem itemUserSongs;
    private ResideMenuItem itemContactUs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        setUpMenu();
        Totalsong=(TextView)findViewById(R.id.songweb2);
        savedsong=(TextView)findViewById(R.id.songsave2);
        yoursong=(TextView)findViewById(R.id.songuser2);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url.getUrl_info(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONArray jsonArray =jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    Totalsong.setText(jsonObject.getString("allsong"));
                    savedsong.setText(jsonObject.getString("savesong"));
                    yoursong.setText(jsonObject.getString("usersong"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id",Url.getIduser());
                return params;
            }
        };
        requestQueue= Volley.newRequestQueue(Info.this);
        requestQueue.add(stringRequest);
    }
    private void setUpMenu() {



        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(this);
        resideMenu.setScaleValue(0.6f);

        // create menu items;
        itemHome        = new ResideMenuItem(this, R.drawable.icon_home, "Home");
        itemSearch      = new ResideMenuItem(this, R.drawable.searc32, "Search");
        itemAddNewList  = new ResideMenuItem(this, R.drawable.listt2,     "Add New Lists");
        itemInfo        = new ResideMenuItem(this, R.drawable.info,     "Info");
        itemAddnewSong  =new ResideMenuItem(this,R.drawable.addnewsong,"Add New Song");
        itemSavedSongd  = new ResideMenuItem(this, R.drawable.cloud, "Saved Songs");
        itemUserSongs   = new ResideMenuItem(this, R.drawable.cloud,  "User Songs");
        itemContactUs   = new ResideMenuItem(this, R.drawable.sms, "Contact Us");



        itemHome.setOnClickListener(this);
        itemSearch.setOnClickListener(this);
        itemAddNewList.setOnClickListener(this);
        itemInfo.setOnClickListener(this);

        itemAddnewSong.setOnClickListener(this);
        itemSavedSongd.setOnClickListener(this);
        itemUserSongs.setOnClickListener(this);
        itemContactUs.setOnClickListener(this);

        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemSearch, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemAddNewList, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemInfo,ResideMenu.DIRECTION_LEFT);

        resideMenu.addMenuItem(itemAddnewSong, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemSavedSongd, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemUserSongs, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemContactUs, ResideMenu.DIRECTION_RIGHT);

        // You can disable a direction by setting ->
        // resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {

        if (view == itemHome) {
            Intent Home = new Intent(Info.this, Home.class);
            startActivity(Home);
        } else if (view == itemSearch) {
            Intent itemSearch = new Intent(Info.this, Search.class);
            startActivity(itemSearch);
        } else if (view == itemAddNewList) {
            Intent itemAddNewList = new Intent(Info.this, AddNewList.class);
            startActivity(itemAddNewList);
        } else if (view == itemInfo) {
            Intent itemInfo = new Intent(Info.this, Info.class);
            startActivity(itemInfo);

        } else if (view == itemAddnewSong) {
            Intent itemAddnewSong = new Intent(Info.this, SuggestionSongs.class);
            startActivity(itemAddnewSong);
        } else if (view == itemSavedSongd) {
            StringRequest stringRequest =new StringRequest(Request.Method.POST, Url.getUrl_savedsongsweb(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        String s=new String(response.getBytes("ISO-8859-1"),"UTF-8");
                        if(s.equals("error"))
                        {
                            Toast.makeText(Info.this,"لا توجد ترانيم محفوظة ",Toast.LENGTH_LONG).show();

                        }else{
                            Intent save = new Intent(Info.this, SavedSongs.class);
                            save.putExtra("response", s);
                            startActivity(save);
                        }


                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Info.this,"Error",Toast.LENGTH_LONG).show();

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("user_id",Url.getIduser());
                    return params;
                }
            };
            requestQueue= Volley.newRequestQueue(Info.this);
            requestQueue.add(stringRequest);

        } else if (view == itemUserSongs) {
            StringRequest stringRequestuser =new StringRequest(Request.Method.POST,Url.getUrl_userssong(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        String s=new String(response.getBytes("ISO-8859-1"),"UTF-8");
                        if(s.equals("error"))
                        {
                            Toast.makeText(Info.this,"لا توجد ترانيم محفوظة ",Toast.LENGTH_LONG).show();

                        }else{
                            Intent save = new Intent(Info.this, SavedSongs.class);
                            save.putExtra("response", s);
                            startActivity(save);
                        }


                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Info.this,"Error",Toast.LENGTH_LONG).show();

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("user_id",Url.getIduser());
                    return params;
                }
            };
            requestQueue= Volley.newRequestQueue(Info.this);
            requestQueue.add(stringRequestuser);
        }else if (view == itemContactUs) {
            Intent itemContactUs = new Intent(Info.this, ContactUS.class);
            startActivity(itemContactUs);
        }
        resideMenu.closeMenu();
    }
}
