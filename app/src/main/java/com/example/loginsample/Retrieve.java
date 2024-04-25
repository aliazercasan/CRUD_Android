package com.example.loginsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Retrieve extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);



        EditText txt_id = findViewById(R.id.userId);
        TextView lbl_uname = findViewById(R.id.txt_uname);
        TextView lbl_pword = findViewById(R.id.txt_pword);
        TextView lbl_email = findViewById(R.id.txt_email);
        Button btn_retrieve = findViewById(R.id.btn_retrieve);

        btn_retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  String userId = txt_id.getText().toString();
//                String uname = txt_uname.getText().toString();
//                String pword = txt_pword.getText().toString();
//                String email = txt_email.getText().toString();

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.1.5/sample/retrieve.php";



                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try{
                                    JSONArray jsonArray = new JSONArray(response);
                                    for(int i = 0; i < jsonArray.length();i++){
                                        JSONObject jsonObject =  jsonArray.getJSONObject(i);
                                        String user_name = jsonObject.getString("username");
                                        String user_pass = jsonObject.getString("password");
                                        String user_email = jsonObject.getString("email");

                                        lbl_uname.setText("Username: " + user_name);
                                        lbl_pword.setText("Password: " + user_pass);
                                        lbl_email.setText("Email: " + user_email);

                                    }
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Retrieve.this,error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("id",userId);

                        return paramV;
                    }
                };

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });
    }

}
