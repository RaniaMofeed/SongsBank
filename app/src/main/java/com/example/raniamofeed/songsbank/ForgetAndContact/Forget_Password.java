package com.example.raniamofeed.songsbank.ForgetAndContact;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.raniamofeed.songsbank.SearchDev.activities.ResultSearch;
import com.example.raniamofeed.songsbank.SearchDev.activities.Search;
import com.example.raniamofeed.songsbank.Url;
import com.example.raniamofeed.songsbank.login_Register.Login;
import com.example.raniamofeed.songsbank.login_Register.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Forget_Password extends AppCompatActivity {
    EditText Email;
    String email;
    Button submit;
    RequestQueue requestQueue;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget__password);
        Email=(EditText)findViewById(R.id.emailaddress);
        submit=(Button)findViewById(R.id.Reset);
        builder = new AlertDialog.Builder(Forget_Password.this);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=Email.getText().toString();
                if(email.equals("")){
                    builder.setTitle("Some Thing Error...");
                    displayaAert("please Enter your Email");

                }else {

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.getUrl_forget(), new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray jsonArray =  jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");

                                if (code.equals("falid")) {
                                    builder.setTitle("some Thing Error.");
                                    displayaAert(jsonObject.getString("message"));
                                }
                                else if (code.equals("Success")){
                                    builder.setTitle("will receive a message");
                                    displayaAert(jsonObject.getString("message"));

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }



                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Forget_Password.this, "Error in the Connection ", Toast.LENGTH_LONG).show();

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("mail", email);
                            return params;
                        }
                    };
                    requestQueue= Volley.newRequestQueue(Forget_Password.this);
                    requestQueue.add(stringRequest);

                }

            }
        });


    }
    public void displayaAert(String message) {
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            if(message.equals("Check your email"))
            {
                Intent intent =new Intent(Forget_Password.this, Login.class);
                startActivity(intent);
                finish();
            }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
