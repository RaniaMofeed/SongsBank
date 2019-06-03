package com.example.raniamofeed.songsbank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.raniamofeed.songsbank.AboutandInfo.About_Us;
import com.example.raniamofeed.songsbank.Addnewlistactivity.AddNewList;
import com.example.raniamofeed.songsbank.ForgetAndContact.ContactUS;
import com.example.raniamofeed.songsbank.savedsongs.SavedSongs;
import com.example.raniamofeed.songsbank.SearchDev.activities.Search;
import com.example.raniamofeed.songsbank.login_Register.Login;
import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.OnBoomListener;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class Home extends AppCompatActivity {
    Button logout;
    Button About_US;
    TextView about, log, songbank;
    private RequestQueue requestQueue;
    SharedPreferences sharedPreferences;
    private BoomMenuButton bmb;
    String[] subButtonTexts;
    String[] color;
    private static int[] imageResources = new int[]{
            R.drawable.searchicon,
            R.drawable.addsong,
            R.drawable.list2,
            R.drawable.saved,
            R.drawable.saved,
            R.drawable.contactus,
    };
    private static int imageResourceIndex = 0;

    static int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //SET anim
        about = (TextView) findViewById(R.id.about_us);
        log = (TextView) findViewById(R.id.LOGOUT);
        songbank = (TextView) findViewById(R.id.songhome);
        about.setTypeface(Typeface.createFromAsset(getAssets(), "Lemonada-Bold.ttf"));
        log.setTypeface(Typeface.createFromAsset(getAssets(), "Lemonada-Bold.ttf"));
        songbank.setTypeface(Typeface.createFromAsset(getAssets(), "Lemonada-Bold.ttf"));
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.homeanimation);
        about.startAnimation(myanim);
        Animation myanim2 = AnimationUtils.loadAnimation(this, R.anim.homeanimation);
        log.startAnimation(myanim2);
        Animation myanim3 = AnimationUtils.loadAnimation(this, R.anim.homeanimation);
        songbank.startAnimation(myanim3);


        logout = (Button) findViewById(R.id.LOGOUT);
        About_US = (Button) findViewById(R.id.about_us);

        About_US.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AboutUS = new Intent(Home.this, About_Us.class);
                startActivity(AboutUS);


            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getSharedPreferences("logout", MODE_PRIVATE);
                sharedPreferences.edit().clear().apply();
                Intent logout = new Intent(Home.this, Login.class);
                startActivity(logout);
                finish();
            }
        });

        // Set BOOMMENUE
        bmb = (BoomMenuButton) findViewById(R.id.bmb);
        subButtonTexts = new String[]{"Search", "Add New Song", "Add New Lists", "Saved Songs", "User Songs", "Contact Us"};
        assert bmb != null;
        color = new String[]{"#FFE0B5F7", "#E1A1D9", "#DE7EE7", "#DE7EE7", "#BF48A9", "#603177"};
        bmb.setButtonEnum(ButtonEnum.Ham);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_6);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_6);

        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            HamButton.Builder builder = new HamButton.Builder()
                    .normalImageRes(getImageResource())
                    .normalText(subButtonTexts[i])
                    .normalColor(Color.parseColor(color[i]));
            bmb.addBuilder(builder);
        }
        bmb.setOnBoomListener(new OnBoomListener() {
            @Override
            public void onClicked(int index, BoomButton boomButton) {
                switch (index) {

                    case 0:
                        StringRequest stringRequestSarch = new StringRequest(Request.Method.POST, Url.getUrl_retrofit(), new Response.Listener<String>() {
                            public void onResponse(String response) {
                                try {
                                    String s = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                                    JSONArray jsonArray = new JSONArray(s);
                                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                                    JSONArray musicKey = jsonObject.getJSONArray("all_music_keys");
                                    JSONArray subjects_key = jsonObject.getJSONArray("all_subjects");
                                    Integer musickey = musicKey.length();
                                    Integer subjectskey = subjects_key.length();
                                    Intent Search = new Intent(Home.this, Search.class);
                                    Search.putExtra("response", s);
                                    Search.putExtra("musickey", musickey);
                                    Search.putExtra("subjectskey", subjectskey);
                                    startActivity(Search);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Home.this, "Check Internet", Toast.LENGTH_LONG).show();

                            }
                        });
                        requestQueue = Volley.newRequestQueue(Home.this);
                        requestQueue.add(stringRequestSarch);
                        break;

                    case 1:
                        StringRequest stringRequestSugest = new StringRequest(Request.Method.POST, Url.getUrl_retrofit(), new Response.Listener<String>() {
                            public void onResponse(String response) {
                                try {
                                    String s = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                                    JSONArray jsonArray = new JSONArray(s);
                                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                                    JSONArray musicKey = jsonObject.getJSONArray("all_music_keys");
                                    JSONArray subjects_key = jsonObject.getJSONArray("all_subjects");
                                    Integer musickey = musicKey.length();
                                    Integer subjectskey = subjects_key.length();
                                    Intent suggesr = new Intent(Home.this, SuggestionSongs.class);
                                    suggesr.putExtra("response", s);
                                    suggesr.putExtra("musickey", musickey);
                                    suggesr.putExtra("subjectskey", subjectskey);
                                    startActivity(suggesr);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Home.this, "Check Internet", Toast.LENGTH_LONG).show();

                            }
                        });
                        requestQueue = Volley.newRequestQueue(Home.this);
                        requestQueue.add(stringRequestSugest);
                        break;
                    case 2:
                        Intent AddnewList = new Intent(Home.this, AddNewList.class);
                        startActivity(AddnewList);
                        break;
                    case 3:
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.getUrl_savedsongsweb(), new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    String s = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                                    if (s.equals("error")) {
                                        Toast.makeText(Home.this, "لا توجد ترانيم محفوظة ", Toast.LENGTH_LONG).show();

                                    } else {
                                        Intent save = new Intent(Home.this, SavedSongs.class);
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
                                Toast.makeText(Home.this, "Check Internet", Toast.LENGTH_LONG).show();

                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("user_id", Url.getIduser());
                                return params;
                            }
                        };
                        requestQueue = Volley.newRequestQueue(Home.this);
                        requestQueue.add(stringRequest);
                        break;
                    case 4:
                        StringRequest stringRequestuser = new StringRequest(Request.Method.POST, Url.getUrl_userssong(), new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    String ss = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                                    if (ss.equals("error")) {
                                        Toast.makeText(Home.this, "لا توجد ترانيم محفوظة ", Toast.LENGTH_LONG).show();

                                    } else {
                                        Intent save = new Intent(Home.this, SavedSongs.class);
                                        save.putExtra("response", ss);
                                        startActivity(save);
                                    }


                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Home.this, "Error", Toast.LENGTH_LONG).show();

                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> param = new HashMap<String, String>();
                                param.put("user_id", Url.getIduser());
                                return param;
                            }
                        };
                        requestQueue = Volley.newRequestQueue(Home.this);
                        requestQueue.add(stringRequestuser);
                        break;
                    case 5:

                        Intent Contact = new Intent(Home.this, ContactUS.class);
                        startActivity(Contact);
                        break;

                }
            }


            @Override
            public void onBackgroundClick() {

            }

            @Override
            public void onBoomWillHide() {

            }

            @Override
            public void onBoomDidHide() {

            }

            @Override
            public void onBoomWillShow() {
            }

            @Override
            public void onBoomDidShow() {

            }
        });
    }
}
