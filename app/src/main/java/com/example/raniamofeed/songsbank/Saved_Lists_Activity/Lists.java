package com.example.raniamofeed.songsbank.Saved_Lists_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.raniamofeed.songsbank.Home;
import com.example.raniamofeed.songsbank.R;
import com.example.raniamofeed.songsbank.Saved_Lists_Activity.Data.DatabaseHandler;
import com.example.raniamofeed.songsbank.Saved_Lists_Activity.Model.MyWish;
import com.example.raniamofeed.songsbank.SearchDev.activities.Search;
import com.example.raniamofeed.songsbank.Url;
import com.example.raniamofeed.songsbank.savedsongs.SavedSongs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lists extends Activity {
    String[] Saved_Song;
    PopupMenu popup;
    String msongname;
    //**********************
    private EditText title;
    private EditText content;
    private Button saveButton;
    private DatabaseHandler dba;
    private RequestQueue requestQueue;


    //*************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);

        dba = new DatabaseHandler(Lists.this);
        title = (EditText) findViewById(R.id.titleEditText);
        content = (EditText) findViewById(R.id.wishEditText);
        saveButton = (Button) findViewById(R.id.save_notes);
        saveButton.setOnClickListener(v -> saveToDB());

        content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.getUrl_allusersongsname(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            String s = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                            JSONArray jsonArray = new JSONArray(s);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            JSONArray songname = jsonObject.getJSONArray("songsname");
                            Saved_Song = new String[songname.length()];
                            for (int i = 0; i < songname.length(); i++) {
                                JSONObject key = songname.getJSONObject(i);
                                msongname = key.getString("songname");
                                Saved_Song[i] = msongname;
                            }


                            if (editable.toString().endsWith("@")) {
                                popup = new PopupMenu(Lists.this, content);
                                for (String k : Saved_Song) {
                                    popup.getMenu().add(k);
                                }
                                popup.show();
                                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                    @Override
                                    public boolean onMenuItemClick(MenuItem menuItem) {
                                        String col = (String) menuItem.getTitle();
                                        if (editable.toString().contains("@")) {
                                            Editable ab = new SpannableStringBuilder(editable.toString().replace("@", col));
                                            editable.replace(0, editable.length(), ab);
                                        }
                                        return true;
                                    }
                                });
                            }

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Lists.this, "Check Internet", Toast.LENGTH_LONG).show();


                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("user_id", Url.getIduser());
                        return params;
                    }
                };
                requestQueue = Volley.newRequestQueue(Lists.this);
                requestQueue.add(stringRequest);
            }
        });
    }

    private void saveToDB() {

        MyWish wish = new MyWish();
        wish.setTitle(title.getText().toString().trim());
        wish.setContent(content.getText().toString().trim());
        dba.addWishes(wish);
        dba.close();
        title.setText("");
        content.setText("");
        Intent i = new Intent(Lists.this, com.example.raniamofeed.songsbank.Saved_Lists_Activity.Display_S_notes.class);
        startActivity(i);


    }
}





