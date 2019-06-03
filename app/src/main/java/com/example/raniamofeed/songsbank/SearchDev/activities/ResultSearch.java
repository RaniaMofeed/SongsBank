package com.example.raniamofeed.songsbank.SearchDev.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
import com.example.raniamofeed.songsbank.ForgetAndContact.ContactUS;
import com.example.raniamofeed.songsbank.R;
import com.example.raniamofeed.songsbank.SearchDev.model.Songs;
import com.example.raniamofeed.songsbank.Url;
import com.example.raniamofeed.songsbank.login_Register.Login;
import com.example.raniamofeed.songsbank.login_Register.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class ResultSearch extends AppCompatActivity {
    Button savebutton;
    TextView s_name;
    TextView s_music;
    TextView s_subject;
    TextView s_verses;
    TextView s_Song_Containt;
    String id;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_search);
         s_name= (TextView) findViewById(R.id.name_song2);
         s_music=(TextView)findViewById(R.id.keys2);
         s_subject=(TextView)findViewById(R.id.subject2);
         s_verses=(TextView)findViewById(R.id.verses2);
         s_Song_Containt=(TextView)findViewById(R.id.Song_Containt2);
         getExtraData();
        savebutton=(Button)findViewById(R.id.saveButton);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest =new StringRequest(Request.Method.POST, Url.getUrl_SaveSong(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            String s=new String(response.getBytes("ISO-8859-1"),"UTF-8");
                            if (s.equals("song is saved")) {
                                Toast.makeText(ResultSearch.this, "saved Song", Toast.LENGTH_LONG).show();

                            }
                            else if (s.equals("this song saved before")){
                                Toast.makeText(ResultSearch.this, "Song Already Exist", Toast.LENGTH_LONG).show();
                            }
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ResultSearch.this, "Error in the Connection", Toast.LENGTH_LONG).show();

                    }
                })
                  {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params= new HashMap<String,String>();
                        params.put("number", id);
                        params.put("user_id",Url.getIduser());
                        return params;
                    }
                  };
                requestQueue= Volley.newRequestQueue(ResultSearch.this);
                requestQueue.add(stringRequest);
            }
        });
    }

    private void getExtraData(){
        Songs songs = (Songs) getIntent().getSerializableExtra("songs");
        if(songs != null){
            s_name.setText(songs.getSong_name());
            s_music.setText(songs.getMusic_keys());
            s_subject.setText(songs.getSubjects());
            s_verses.setText(songs.getVerses());
            s_Song_Containt.setText(songs.getSong_words());
            id=songs.getID();
        }
    }
}
