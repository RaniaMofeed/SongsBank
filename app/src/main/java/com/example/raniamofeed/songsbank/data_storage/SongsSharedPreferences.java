package com.example.raniamofeed.songsbank.data_storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.raniamofeed.songsbank.Addnewlistactivity.ItemRecycleviewlist;
import com.example.raniamofeed.songsbank.Saved_Lists_Activity.Data.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rania Mofeed on 6/2/2019.
 */

public class SongsSharedPreferences {
    private SharedPreferences mPreference;

    /**
     * constructor for creating instance of SharedPreference class for saving data
     */
    public SongsSharedPreferences(Context context) {

        mPreference = context.getSharedPreferences(Constants.PREF_NAME, context.MODE_PRIVATE);
    }

    /**
     * save String value in sp
     */
    public void saveStringToSharedPreference(String key, String value) {
        SharedPreferences.Editor mEditor = mPreference.edit();
        mEditor.putString(key, value);
        mEditor.apply();
    }

    /**
     * save boolean value in sp
     */
    public void saveBooleanToSharedPreference(String key, boolean value) {

        SharedPreferences.Editor mEditor = mPreference.edit();
        mEditor.putBoolean(key, value);
        mEditor.apply();
    }

    public void saveIntToSharedPreference(String key, int value) {
        SharedPreferences.Editor mEditor = mPreference.edit();
        mEditor.putInt(key, value);
        mEditor.apply();
    }

    /**
     * retrieve stored String value in sp
     */
    public String retrieveStringFromSharedPreference(String key) {
        return mPreference.getString(key, "");
    }

    public int retrieveIntFromSharedPreference(String key) {
        return mPreference.getInt(key, -1);
    }

    /**
     * retrieve stored boolean value in sp
     */
    public boolean retrieveBooleanFromSharedPreference(String key) {

        return mPreference.getBoolean(key, false);
    }

    /**
     * save array of String Values in sp
     */
    public void saveArrayToSharedPreference(String superKey, String subKey,
                                            ArrayList<String> givenList) {
        /** superKey : is the ArrayListSize wanted To be stored
         *  subKey : is The item of This ArrayList
         *  I stored each item of arrayList in a shared Preference ,
         *  then i made a for Loop To save each item according to array size
         *  */
        SharedPreferences.Editor mEditor = mPreference.edit();
        mEditor.putInt(superKey, givenList.size());
        /** Saving the size of required ArrayList*/
        for (int i = 0; i < givenList.size(); i++) {
            mEditor.putString((subKey + i), givenList.get(i));
        }
        mEditor.apply();
    }

    /**
     * retrieve stored array of String Values from sp
     */
    public ArrayList<String> retrieveArrayFromSharedPreference(String superKey, String subKey) {

        /** Retrieve this list items with same scenario ,
         * Loop for each item according the ArrayList Size
         * */

        int listSize = mPreference.getInt(superKey, 0);

        ArrayList<String> finalList = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            finalList.add(mPreference.getString((subKey + i), null));
        }
        return finalList;
    }

//    public void setDataFromSharedPreferences(ItemRecycleviewlist itemRecycleviewlist){
//      //  Gson gson = new Gson();
//       // String jsonCurProduct = gson.toJson(itemRecycleviewlist);
//        SharedPreferences.Editor mEditor = mPreference.edit();
//        mEditor.putString("listexample", jsonCurProduct);
//        mEditor.commit();
//    }

    public List<ItemRecycleviewlist> getDataFromSharedPreferences(){
        Gson gson = new Gson();
        List<ItemRecycleviewlist> productFromShared;
        String jsonPreferences = mPreference.getString("listexample", "");

        Type type = new TypeToken<List<ItemRecycleviewlist>>() {}.getType();
        productFromShared = gson.fromJson(jsonPreferences, type);

        return productFromShared;
    }

    /**
     * delete shared preferences stored in the app sp file
     */
    public void removeSharedPreference(String key) {
        SharedPreferences.Editor mEditor = mPreference.edit();
        mEditor.remove(key);
        mEditor.apply();
    }

    /**
     * clear all shared preferences stored in the app sp file
     */
    public void clearSharedPreferences() {

        SharedPreferences.Editor mEditor = mPreference.edit();
        mEditor.clear();
        mEditor.apply();
    }
}
