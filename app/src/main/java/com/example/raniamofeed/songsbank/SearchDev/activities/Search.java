package com.example.raniamofeed.songsbank.SearchDev.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.example.raniamofeed.songsbank.AboutandInfo.Info;
import com.example.raniamofeed.songsbank.R;
import com.example.raniamofeed.songsbank.SuggestionSongs;
import com.example.raniamofeed.songsbank.Url;
import com.example.raniamofeed.songsbank.savedsongs.SavedSongs;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Search extends AppCompatActivity implements View.OnClickListener {
    //Making Choices for Musical keys
    String mKey;
    ImageButton mOrder;
    TextView mItemSelected;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();

    //Making Choices for Theme song
    String msupject;
    ImageButton mOrdersong;
    TextView mItemSelectedsong;
    String[] listItemssong;
    boolean[] checkedItemssong;
    ArrayList<Integer> mUserItemssong = new ArrayList<>();
    //*****************************************************************
    EditText Song_name, Verses, Words;
    TextView Music_keys, Subjects;
    String song_name, verses, words, music_keysstring, subjects2string;
    Button se_but;
    AlertDialog.Builder builder;
    private RequestQueue requestQueue;
    //*************************************************************
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

    //*****************************************
    TextView songtext1, songtext2, songtext3, songtext4;
    TextView shape1, shape2, shape3, shape4, shape5;

    //*****************************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setUpMenu();

        Intent intent = getIntent();
        String response = intent.getStringExtra("response");
        Integer musickey = intent.getExtras().getInt("musickey");
        Integer subjectskey = intent.getExtras().getInt("subjectskey");

        //Making Choices for Musical keys develop
        mOrder = (ImageButton) findViewById(R.id.Musical);
        mItemSelected = (TextView) findViewById(R.id.TextMusical);
        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    JSONArray musicKey = jsonObject.getJSONArray("all_music_keys");
                    listItems = new String[musicKey.length()];
                    for (int i = 0; i < musicKey.length(); i++) {
                        JSONObject key = musicKey.getJSONObject(i);
                        mKey = key.getString("music_key");
                        listItems[i] = mKey;
                    }
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(Search.this);
                    mBuilder.setTitle(R.string.dialog_titleM);
                    mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                            if (isChecked) {
                                mUserItems.add(position);
                            } else {
                                mUserItems.remove((Integer.valueOf(position)));
                            }
                        }
                    });
                    mBuilder.setCancelable(false);
                    mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            String item = "";
                            for (int i = 0; i < mUserItems.size(); i++) {
                                item = item + listItems[mUserItems.get(i)];
                                if (i != mUserItems.size() - 1) {
                                    item = item + ", ";
                                }
                            }
                            mItemSelected.setText(item);
                        }
                    });

                    mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

                    mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            for (int i = 0; i < checkedItems.length; i++) {
                                checkedItems[i] = false;
                                mUserItems.clear();
                                mItemSelected.setText("");
                            }
                        }
                    });

                    AlertDialog mDialog = mBuilder.create();
                    mDialog.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        checkedItems = new boolean[musickey];

        //Making Choices for Theme song develop
        mOrdersong = (ImageButton) findViewById(R.id.Songs);
        mItemSelectedsong = (TextView) findViewById(R.id.TextSongs);
        mOrdersong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    JSONArray sub_key = jsonObject.getJSONArray("all_subjects");
                    listItemssong = new String[sub_key.length()];
                    for (int i = 0; i < sub_key.length(); i++) {
                        JSONObject key = sub_key.getJSONObject(i);
                        msupject = key.getString("subject");
                        listItemssong[i]=msupject;

                    }
                    AlertDialog.Builder mBuilderS = new AlertDialog.Builder(Search.this);
                    mBuilderS.setTitle(R.string.dialog_titleS);
                    mBuilderS.setMultiChoiceItems(listItemssong, checkedItemssong, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                            if (isChecked) {
                                mUserItemssong.add(position);
                            } else {
                                mUserItemssong.remove((Integer.valueOf(position)));
                            }
                        }
                    });

                    mBuilderS.setCancelable(false);
                    mBuilderS.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            String item = "";
                            for (int i = 0; i < mUserItemssong.size(); i++) {
                                item = item + listItemssong[mUserItemssong.get(i)];
                                if (i != mUserItemssong.size() - 1) {
                                    item = item + ",";

                                }
                            }
                            mItemSelectedsong.setText(item);
                        }
                    });

                    mBuilderS.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

                    mBuilderS.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            for (int i = 0; i < checkedItems.length; i++) {
                                checkedItems[i] = false;
                                mUserItems.clear();
                                mItemSelected.setText("");
                            }
                        }
                    });

                    AlertDialog mDialog = mBuilderS.create();
                    mDialog.show();


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        checkedItemssong = new boolean[subjectskey];


//*****************************************************************
        //setText
        songtext1 = (TextView) findViewById(R.id.search1);
        songtext1.setTypeface(Typeface.createFromAsset(getAssets(), "Yekan-Bakh-EN-04-Regular.ttf"));
        shape1 = (TextView) findViewById(R.id.text1shape);
        shape1.setTypeface(Typeface.createFromAsset(getAssets(), "Italian Mosaic Ornaments.ttf"));

        songtext2 = (TextView) findViewById(R.id.search2);
        songtext2.setTypeface(Typeface.createFromAsset(getAssets(), "Yekan-Bakh-EN-04-Regular.ttf"));
        shape2 = (TextView) findViewById(R.id.text2shape);
        shape2.setTypeface(Typeface.createFromAsset(getAssets(), "Italian Mosaic Ornaments.ttf"));

        songtext3 = (TextView) findViewById(R.id.search3);
        songtext3.setTypeface(Typeface.createFromAsset(getAssets(), "Yekan-Bakh-EN-04-Regular.ttf"));
        shape3 = (TextView) findViewById(R.id.text3shape);
        shape3.setTypeface(Typeface.createFromAsset(getAssets(), "Italian Mosaic Ornaments.ttf"));

        songtext4 = (TextView) findViewById(R.id.search4);
        songtext4.setTypeface(Typeface.createFromAsset(getAssets(), "Yekan-Bakh-EN-04-Regular.ttf"));
        shape4 = (TextView) findViewById(R.id.text4shape);
        shape4.setTypeface(Typeface.createFromAsset(getAssets(), "Italian Mosaic Ornaments.ttf"));

        shape5 = (TextView) findViewById(R.id.text5shape);
        shape5.setTypeface(Typeface.createFromAsset(getAssets(), "Italian Mosaic Ornaments.ttf"));
        //   EditText Song_name,Music_keys,Subjects,Verses,Words;

        Song_name = (EditText) findViewById(R.id.editText1);
        Music_keys = (TextView) findViewById(R.id.TextMusical);
        Subjects = (TextView) findViewById(R.id.TextSongs);
        Verses = (EditText) findViewById(R.id.editText4);
        Words = (EditText) findViewById(R.id.editText);
        se_but = (Button) findViewById(R.id.btn_search);
        builder = new AlertDialog.Builder(Search.this);
        Music_keys.setClickable(false);
        se_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                song_name = Song_name.getText().toString();
                music_keysstring = Music_keys.getText().toString();
                subjects2string = Subjects.getText().toString();
                verses = Verses.getText().toString();
                words = Words.getText().toString();
                if (song_name.equals("") && music_keysstring.equals("") && subjects2string.equals("") && verses.equals("") && words.equals("")) {

                    builder.setTitle("something went wrong.....");
                    displayaAert("Enter at least one field to search");

                } else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.getUrl_Search(), new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                String s = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                                if (s.equals("error")) {
                                    Toast.makeText(Search.this, "لا توجد نتائج", Toast.LENGTH_LONG).show();

                                } else {
                                    Intent intent = new Intent(Search.this, Search_Recycleview.class);
                                    intent.putExtra("response", s);
                                    startActivity(intent);
                                }

                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Search.this, "Error", Toast.LENGTH_LONG).show();
                            error.printStackTrace();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("song_name", song_name);
                            params.put("music_keys", music_keysstring);
                            params.put("subjects", subjects2string);
                            params.put("verses", verses);
                            params.put("words", words);
                            return params;

                        }
                    };
                    requestQueue = Volley.newRequestQueue(Search.this);
                    requestQueue.add(stringRequest);
                }
            }
        });

    }

    private void setUpMenu() {

        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(this);
        resideMenu.setScaleValue(0.6f);

        itemHome = new ResideMenuItem(this, R.drawable.icon_home, "Home");
        itemSearch = new ResideMenuItem(this, R.drawable.searc32, "Search");
        itemAddNewList = new ResideMenuItem(this, R.drawable.listt2, "Add New Lists");
        itemInfo = new ResideMenuItem(this, R.drawable.info, "Info");
        itemAddnewSong = new ResideMenuItem(this, R.drawable.addnewsong, "Add New Song");
        itemSavedSongd = new ResideMenuItem(this, R.drawable.cloud, "Saved Songs");
        itemUserSongs = new ResideMenuItem(this, R.drawable.cloud, "User Songs");
        itemContactUs = new ResideMenuItem(this, R.drawable.sms, "Contact Us");


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
        resideMenu.addMenuItem(itemInfo, ResideMenu.DIRECTION_LEFT);

        resideMenu.addMenuItem(itemAddnewSong, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemSavedSongd, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemUserSongs, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemContactUs, ResideMenu.DIRECTION_RIGHT);


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
            Intent Home = new Intent(Search.this, Home.class);
            startActivity(Home);
        } else if (view == itemSearch) {
            Intent itemSearch = new Intent(Search.this, Search.class);
            startActivity(itemSearch);
        } else if (view == itemAddNewList) {
            Intent itemAddNewList = new Intent(Search.this, AddNewList.class);
            startActivity(itemAddNewList);
        } else if (view == itemInfo) {
            Intent itemInfo = new Intent(Search.this, Info.class);
            startActivity(itemInfo);

        } else if (view == itemAddnewSong) {
            Intent itemAddnewSong = new Intent(Search.this, SuggestionSongs.class);
            startActivity(itemAddnewSong);
        } else if (view == itemSavedSongd) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.getUrl_savedsongsweb(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        String s = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                        if (s.equals("error")) {
                            Toast.makeText(Search.this, "لا توجد ترانيم محفوظة ", Toast.LENGTH_LONG).show();

                        } else {
                            Intent save = new Intent(Search.this, SavedSongs.class);
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
                    Toast.makeText(Search.this, "Error", Toast.LENGTH_LONG).show();

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("user_id", Url.getIduser());
                    return params;
                }
            };
            requestQueue = Volley.newRequestQueue(Search.this);
            requestQueue.add(stringRequest);

        } else if (view == itemUserSongs) {
            StringRequest stringRequestuser = new StringRequest(Request.Method.POST, Url.getUrl_userssong(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        String s = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                        if (s.equals("error")) {
                            Toast.makeText(Search.this, "لا توجد ترانيم محفوظه", Toast.LENGTH_LONG).show();

                        } else {
                            Intent save = new Intent(Search.this, SavedSongs.class);
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
                    Toast.makeText(Search.this, "Error", Toast.LENGTH_LONG).show();

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("user_id", Url.getIduser());
                    return params;
                }
            };
            requestQueue = Volley.newRequestQueue(Search.this);
            requestQueue.add(stringRequestuser);
        } else if (view == itemContactUs) {
            Intent itemContactUs = new Intent(Search.this, ContactUS.class);
            startActivity(itemContactUs);
        }

        resideMenu.closeMenu();
    }

    public void displayaAert(String message) {
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}

