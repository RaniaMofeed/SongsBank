package com.example.raniamofeed.songsbank.login_Register;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.raniamofeed.songsbank.ForgetAndContact.Forget_Password;
import com.example.raniamofeed.songsbank.Home;
import com.example.raniamofeed.songsbank.R;
import com.example.raniamofeed.songsbank.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    Button login, Register, forgetpassword;
    EditText Username, Password;
    String username, password;
    AlertDialog.Builder builder;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String User_ID;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Username = (EditText) findViewById(R.id.userlogin);
        Password = (EditText) findViewById(R.id.passlogin);
        login = (Button) findViewById(R.id.logiin);
        Register = (Button) findViewById(R.id.register);
        forgetpassword = (Button) findViewById(R.id.forgetpassword);
        builder = new AlertDialog.Builder(Login.this);


        //save_data
        sharedPreferences = this.getSharedPreferences("logout", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = Username.getText().toString();
                password = Password.getText().toString();


                if (username.equals("") || password.equals("")) {
                    builder.setTitle("something went wrong.....");
                    displayaAert("Enter a valid  Username And password..");


                } else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST,Url.getUrl_Login(), new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");
                                if (code.equals("login falid")) {
                                    builder.setTitle("Login Error...");
                                    displayaAert(jsonObject.getString("message"));
                                } else {
                                    //builder.setTitle("success");
                                    User_ID = jsonObject.getString("id");
                                    editor.putBoolean("savelogin", true).commit();
                                    editor.putString("id", User_ID).commit();
                                    editor.putString("username", username).commit();
                                    editor.putString("password", password).commit();
                                    Intent intent_ID = new Intent(Login.this,Home.class);
                                    Url.setIduser(User_ID);
                                    startActivity(intent_ID);
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Login.this,"Check Internet", Toast.LENGTH_LONG).show();
                            error.printStackTrace();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("username", username);
                            params.put("password", password);
                            return params;
                        }
                    };
                    MySingleton.getmInstance(Login.this).addToRequestQueue(stringRequest);


                }
            }
        });


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Regsister.class);
                startActivity(intent);
            }
        });
        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Forget_Password.class);
                startActivity(intent);
            }
        });


    }

    public void displayaAert(String message) {
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Username.setText("");
                Password.setText("");

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}