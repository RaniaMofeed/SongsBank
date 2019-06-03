package com.example.raniamofeed.songsbank.login_Register;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
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
import com.example.raniamofeed.songsbank.Home;
import com.example.raniamofeed.songsbank.R;
import com.example.raniamofeed.songsbank.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Regsister extends AppCompatActivity{
    Button Reg;
    EditText Name ,Email,Password,Repeatepass;

    String name ,email,password,repeatepass;
    AlertDialog.Builder builder;
    //Valid Email.....
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    //..................

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regsister);
        Reg =(Button)findViewById(R.id.Submit);
        Email=(EditText)findViewById(R.id.mail);
        Name=(EditText)findViewById(R.id.username);
        Password=(EditText)findViewById(R.id.password);
        Repeatepass=(EditText)findViewById(R.id.reppassword);
        builder=new AlertDialog.Builder(Regsister.this);
        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=Email.getText().toString();
                name=Name.getText().toString();
                password=Password.getText().toString();
                repeatepass=Repeatepass.getText().toString();
                if(email.equals("")||name.equals("")||password.equals("")||repeatepass.equals(""))
                {
                    builder.setTitle("something went wrong.....");
                    builder.setMessage("Please fill all fields..");
                    displayaAert("input_error");

                }
                else
                {
                    if(!(password.equals(repeatepass)))
                    {
                        builder.setTitle("something went wrong.....");
                        builder.setMessage("Your password are not matching..");
                        displayaAert("input_error");
                    }
                    else
                        if (!isEmailValid(email)){
                            Toast.makeText(Regsister.this, "Your Email Id is Invalid.", Toast.LENGTH_LONG).show();

                        }
                        else
                            {
                                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url.getUrl_Register(), new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONArray jsonArray=new JSONArray(response);
                                            JSONObject jsonObject=jsonArray.getJSONObject(0);
                                            String code=jsonObject.getString("code");
                                            String message=jsonObject.getString("message");
                                            builder.setTitle("Server Response");
                                            builder.setMessage(message);
                                            displayaAert(code);

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }


                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(Regsister.this, "Error in connection", Toast.LENGTH_LONG).show();


                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        Map<String,String> params= new HashMap<String,String>();
                                        params.put("email",email);
                                        params.put("username",name);
                                        params.put("password",password);
                                        return params;
                                    }
                                };
                                MySingleton.getmInstance(Regsister.this).addToRequestQueue(stringRequest);
                            }
                }

            }
        });

    }
    public void displayaAert(final String code)
    {
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(code.equals("input_error"))
                {
                    Password.setText("");
                    Repeatepass.setText("");
                }
                else if(code.equals("reg success"))
                {
                    Intent intent=new Intent(Regsister.this,Login.class);
                    startActivity(intent);
                    finish();
                }
                else if(code.equals("reg falid"))
                {
                    Name .setText("");
                    Email .setText("");
                    Password .setText("");
                    Repeatepass.setText("");
                }

            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
        boolean isEmailValid(CharSequence emaill) {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(emaill).matches();
        }
}
