package com.example.raniamofeed.songsbank;

import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.raniamofeed.songsbank.AboutandInfo.Info;
import com.example.raniamofeed.songsbank.Addnewlistactivity.AddNewList;
import com.example.raniamofeed.songsbank.ForgetAndContact.ContactUS;
import com.example.raniamofeed.songsbank.savedsongs.SavedSongs;
import com.example.raniamofeed.songsbank.SearchDev.activities.Search;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SuggestionSongs extends AppCompatActivity implements View.OnClickListener {
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
    //*********************************************************

    AlertDialog.Builder builder;
    AlertDialog.Builder builder2;

    private RequestQueue requestQueue;
    EditText Song_name, Verses, Words;
    TextView Music_keys, Subjects;
    String song_name, verses, words, music_keysstring, subjects2string;
    Button upload;

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
        setContentView(R.layout.activity_suggestion_songs);
        setUpMenu();
        upload = (Button) findViewById(R.id.btn_updoad_su);
        builder = new AlertDialog.Builder(SuggestionSongs.this);
        builder2 = new AlertDialog.Builder(SuggestionSongs.this);
        Song_name = (EditText) findViewById(R.id.namesongedit_suggest);
        Music_keys = (TextView) findViewById(R.id.keytext_suggest);
        Subjects = (TextView) findViewById(R.id.subjesttext_suggest);
        Verses = (EditText) findViewById(R.id.versrsedit_suggest);
        Words = (EditText) findViewById(R.id.Wordedit_suggest);

        Intent intent = getIntent();
        String response = intent.getStringExtra("response");
        Integer musickey = intent.getExtras().getInt("musickey");
        Integer subjectskey = intent.getExtras().getInt("subjectskey");

        //Making Choices for Musical keys develop
        mOrder = (ImageButton) findViewById(R.id.keys_suggest);
        mItemSelected = (TextView) findViewById(R.id.keytext_suggest);
        mOrdersong = (ImageButton) findViewById(R.id.subjectimage_suggest);
        mItemSelectedsong = (TextView) findViewById(R.id.subjesttext_suggest);
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
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(SuggestionSongs.this);
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
                        listItemssong[i] = msupject;

                    }
                    AlertDialog.Builder mBuilderS = new AlertDialog.Builder(SuggestionSongs.this);
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


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                song_name = Song_name.getText().toString();
                music_keysstring = Music_keys.getText().toString();
                subjects2string = Subjects.getText().toString();
                verses = Verses.getText().toString();
                words = Words.getText().toString();
                if (song_name.equals("") && music_keysstring.equals("") && subjects2string.equals("") && verses.equals("") && words.equals("")) {
                    builder2.setTitle("Attention..");
                    builder2.setMessage("Please Fill  Fields");
                    builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                        }
                    });

                    AlertDialog alertDialog2 = builder2.create();
                    alertDialog2.show();

                } else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.getUrl_newsong(), new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("success")) {
                                Song_name.setText("");
                                Music_keys.setText("");
                                Subjects.setText("");
                                Verses.setText("");
                                Words.setText("");
                                builder.setTitle("saved your Song");
                                builder.setMessage("Do you want to suggest this song to be added on the site ")
                                        .setCancelable(false)
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                StringRequest stringRequestemail = new StringRequest(Request.Method.POST, Url.getUrl_suggest(), new Response.Listener<String>() {
                                                    @Override
                                                    public void onResponse(String response) {
                                                        Toast.makeText(SuggestionSongs.this, "saved your Song", Toast.LENGTH_LONG).show();

                                                    }
                                                }, new Response.ErrorListener() {
                                                    @Override
                                                    public void onErrorResponse(VolleyError error) {

                                                    }
                                                }) {
                                                    @Override
                                                    protected Map<String, String> getParams() throws AuthFailureError {
                                                        Map<String, String> paramssuggest = new HashMap<String, String>();
                                                        paramssuggest.put("user_id", Url.getIduser());
                                                        paramssuggest.put("song_name", song_name);
                                                        paramssuggest.put("music_keys", music_keysstring);
                                                        paramssuggest.put("subjects", subjects2string);
                                                        paramssuggest.put("verses", verses);
                                                        paramssuggest.put("words", words);
                                                        return paramssuggest;
                                                    }
                                                };
                                                requestQueue = Volley.newRequestQueue(SuggestionSongs.this);
                                                requestQueue.add(stringRequestemail);
                                            }
                                        })
                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Toast.makeText(SuggestionSongs.this, "saved your Song", Toast.LENGTH_LONG).show();

                                            }
                                        });
                                AlertDialog mbuilder = builder.create();
                                mbuilder.show();


                            } else if (response.equals("error")) {
                                Toast.makeText(SuggestionSongs.this, "Check Internet", Toast.LENGTH_LONG).show();

                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(SuggestionSongs.this, "Error in Connection", Toast.LENGTH_LONG).show();
                            error.printStackTrace();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("user_id", Url.getIduser());
                            params.put("song_name", song_name);
                            params.put("music_keys", music_keysstring);
                            params.put("subjects", subjects2string);
                            params.put("verses", verses);
                            params.put("words", words);
                            return params;

                        }
                    };
                    requestQueue = Volley.newRequestQueue(SuggestionSongs.this);
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
            Intent Home = new Intent(SuggestionSongs.this, Home.class);
            startActivity(Home);
        } else if (view == itemSearch) {
            Intent itemSearch = new Intent(SuggestionSongs.this, Search.class);
            startActivity(itemSearch);
        } else if (view == itemAddNewList) {
            Intent itemAddNewList = new Intent(SuggestionSongs.this, AddNewList.class);
            startActivity(itemAddNewList);
        } else if (view == itemInfo) {
            Intent itemInfo = new Intent(SuggestionSongs.this, Info.class);
            startActivity(itemInfo);

        } else if (view == itemAddnewSong) {
            Intent itemAddnewSong = new Intent(SuggestionSongs.this, SuggestionSongs.class);
            startActivity(itemAddnewSong);
        } else if (view == itemSavedSongd) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.getUrl_savedsongsweb(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        String s = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                        if (s.equals("error")) {
                            Toast.makeText(SuggestionSongs.this, "لا توجد ترانيم محفوظة ", Toast.LENGTH_LONG).show();

                        } else {
                            Intent save = new Intent(SuggestionSongs.this, SavedSongs.class);
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
                    Toast.makeText(SuggestionSongs.this, "Error", Toast.LENGTH_LONG).show();

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("user_id", Url.getIduser());
                    return params;
                }
            };
            requestQueue = Volley.newRequestQueue(SuggestionSongs.this);
            requestQueue.add(stringRequest);

        } else if (view == itemUserSongs) {
            StringRequest stringRequestuser = new StringRequest(Request.Method.POST, Url.getUrl_userssong(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        String s = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                        if (s.equals("error")) {
                            Toast.makeText(SuggestionSongs.this, "لا توجد ترانيم محفوظه", Toast.LENGTH_LONG).show();

                        } else {
                            Intent save = new Intent(SuggestionSongs.this, SavedSongs.class);
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
                    Toast.makeText(SuggestionSongs.this, "Error", Toast.LENGTH_LONG).show();

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("user_id", Url.getIduser());
                    return params;
                }
            };
            requestQueue = Volley.newRequestQueue(SuggestionSongs.this);
            requestQueue.add(stringRequestuser);
        } else if (view == itemContactUs) {
            Intent itemContactUs = new Intent(SuggestionSongs.this, ContactUS.class);
            startActivity(itemContactUs);
        }


        resideMenu.closeMenu();
    }
}

