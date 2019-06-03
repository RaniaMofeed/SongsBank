package com.example.raniamofeed.songsbank.savedsongs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.raniamofeed.songsbank.R;
import com.example.raniamofeed.songsbank.SearchDev.adapters.RecyclerViewAdapter;
import com.example.raniamofeed.songsbank.SearchDev.model.Songs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SavedSongs extends AppCompatActivity implements RecyclerViewAdapter.OnItemClick{
    private List<Songs> list_song;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_songs);
        recyclerView = (RecyclerView) findViewById(R.id.sv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        list_song = new ArrayList<>();
        Intent intent = getIntent();
        String response = intent.getStringExtra("response");

        try {
            JSONArray jsonArray=new JSONArray(response);
            for (int i=0;i<jsonArray.length();i++)
            {
                Songs songs =new Songs();
                JSONObject jsonObject= jsonArray.getJSONObject(i);
                songs.setID(jsonObject.getString("ID"));

                if(jsonObject.getString("song_name").equals("null"))
                {
                    songs.setSong_name(" ");
                }else{
                    songs.setSong_name(jsonObject.getString("song_name"));
                }
                if(jsonObject.getString("music_keys").equals("null"))
                {
                    songs.setMusic_keys(" ");
                }else{
                    songs.setMusic_keys(jsonObject.getString("music_keys"));

                }
                if(jsonObject.getString("subjects").equals("null"))
                {
                    songs.setSubjects(" ");
                }else{
                    songs.setSubjects(jsonObject.getString("subjects"));

                }
                if (jsonObject.getString("verses").equals("null"))
                {
                    songs.setVerses(" ");
                }else
                {
                    songs.setVerses(jsonObject.getString("verses"));
                }
                if(jsonObject.getString("song_words").equals("null"))
                {
                    songs.setSong_words(" ");
                }else{
                    songs.setSong_words(jsonObject.getString("song_words"));

                }
                list_song.add(songs);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new RecyclerViewAdapter(this, list_song);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onClick(Songs songs) {
        Intent intent = new Intent(this ,ResultSaved.class);
        intent.putExtra("songs" ,songs);
        startActivity(intent);
    }

}
