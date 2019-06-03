package com.example.raniamofeed.songsbank.SearchDev.model;


import java.io.Serializable;

/**
 * Created by Rania Mofeed on 2/6/2019.
 */

public class Songs implements Serializable {
    private String ID;
    private String song_name;
    private String music_keys;
    private String subjects;
    private String verses;
    private String song_words;
    public Songs(){


     }
    public Songs(String ID, String song_name, String music_keys, String subjects, String verses, String song_words) {
        this.ID = ID;
        this.song_name = song_name;
        this.music_keys = music_keys;
        this.subjects = subjects;
        this.verses = verses;
        this.song_words = song_words;
    }
    public String getID() {
        return ID;
    }

    public String getSong_name() {
        return song_name;
    }

    public String getMusic_keys() {
        return music_keys;
    }

    public String getSubjects() {
        return subjects;
    }

    public String getVerses() {
        return verses;
    }

    public String getSong_words() {
        return song_words;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public void setMusic_keys(String music_keys) {
        this.music_keys = music_keys;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public void setVerses(String verses) {
        this.verses = verses;
    }

    public void setSong_words(String song_words) {
        this.song_words = song_words;
    }

}


