package com.example.raniamofeed.songsbank.savedsongs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.raniamofeed.songsbank.R;
import com.example.raniamofeed.songsbank.SearchDev.model.Songs;

public class ResultSaved extends AppCompatActivity {
    TextView s_name;
    TextView s_music;
    TextView s_subject;
    TextView s_verses;
    TextView s_Song_Containt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_saved);
        s_name= (TextView) findViewById(R.id.name_song2_sa);
        s_music=(TextView)findViewById(R.id.keys2_sa);
        s_subject=(TextView)findViewById(R.id.subject2_sa);
        s_verses=(TextView)findViewById(R.id.verses2_sa);
        s_Song_Containt=(TextView)findViewById(R.id.Song_Containt2_sa);
        getExtraData();
    }
    private void getExtraData(){
        Songs songs = (Songs) getIntent().getSerializableExtra("songs");
        if(songs != null){
            s_name.setText(songs.getSong_name());
            s_music.setText(songs.getMusic_keys());
            s_subject.setText(songs.getSubjects());
            s_verses.setText(songs.getVerses());
            s_Song_Containt.setText(songs.getSong_words());
        }
    }
}
