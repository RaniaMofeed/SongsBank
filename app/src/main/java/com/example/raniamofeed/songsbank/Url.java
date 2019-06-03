package com.example.raniamofeed.songsbank;

import java.io.Serializable;

/**
 * Created by Rania Mofeed on 4/6/2019.
 */

public class Url implements Serializable {
    //for login layout;
     static String url_Login="https://app.wrwpraises.com/SongsBankFiles/php/login.php";
    //for Register layout
    static String url_Register="https://app.wrwpraises.com/SongsBankFiles/php/register.php";
    //for forget password
    static String url_forget="https://app.wrwpraises.com/SongsBankFiles/php/forget.php";
    //for contact us
    static String url_contact="https://app.wrwpraises.com/SongsBankFiles/php/Contact_Us.php";
    //for Search layout
    static String url_Search="https://app.wrwpraises.com/SongsBankFiles/php/search.php";
     //for save song
     static String url_SaveSong="https://app.wrwpraises.com/SongsBankFiles/php/save.php";
    //for   get songs that user saved from web
    static String url_savedsongsweb="https://app.wrwpraises.com/SongsBankFiles/php/getusersongs.php";
    //for get users saved song private
    static String url_userssong="https://app.wrwpraises.com/SongsBankFiles/php/userssong.php";
    //for suggest
    static String url_suggest="https://app.wrwpraises.com/SongsBankFiles/php/Suggest.php";
    //for add new song
    static String url_newsong="https://app.wrwpraises.com/SongsBankFiles/php/addnewsong.php";
    //for retrofit key and subjects
    static String url_retrofit="https://app.wrwpraises.com/SongsBankFiles/php/RetriveSearch2.php";
    //for get name of songs users and song name from web
    static String url_allusersongsname="https://app.wrwpraises.com/SongsBankFiles/php/getusersongsname.1.php";
    //for information
     static String url_info="https://app.wrwpraises.com/SongsBankFiles/php/info.php";
    static String Iduser;



    public Url(){


    }

    public static String getUrl_Login() {
        return url_Login;
    }

    public static String getUrl_Register() {
        return url_Register;
    }

    public  static String getUrl_forget() {
        return url_forget;
    }

    public static String getUrl_contact() {
        return url_contact;
    }

    public  static String getUrl_Search() {
        return url_Search;
    }

    public static String getUrl_SaveSong() {
        return url_SaveSong;
    }

    public static String getUrl_savedsongsweb() {
        return url_savedsongsweb;
    }

    public static String getUrl_userssong() {
        return url_userssong;
    }

    public  static String getUrl_suggest() {
        return url_suggest;
    }

    public static String getUrl_newsong() {
        return url_newsong;
    }

    public static String getUrl_retrofit() {
        return url_retrofit;
    }

    public static String getUrl_allusersongsname() {
        return url_allusersongsname;
    }

    public static String getUrl_info() {
        return url_info;
    }

    public static String getIduser() {
        return Iduser;
    }

    public static void setIduser(String iduser) {
        Iduser = iduser;
    }






}
