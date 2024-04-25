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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button button2;

    private Button button3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    button = (Button) findViewById(R.id.button);

     button.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            openUpdate();
         }
     });

     button2 = (Button)findViewById(R.id.button2);

     button2.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {openRetrieve();}
     });

     button3 = (Button)findViewById(R.id.button3);
     button3.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            openDelete();
         }
     });

        EditText txt_uname = findViewById(R.id.txt_uname);
        EditText txt_pword = findViewById(R.id.txt_pword);
        EditText txt_email = findViewById(R.id.txt_email);
        Button btn_save = findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = txt_uname.getText().toString();
                String pword = txt_pword.getText().toString();
                String email = txt_email.getText().toString();

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.137.56/sample/create.php";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("Success")){
                                    Toast.makeText(MainActivity.this,"Data Added",Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(MainActivity.this,"Data failed to add to database", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("uname", uname);
                        params.put("pword", pword);
                        params.put("email", email);
                        return params;
                    }
                };

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });
    }
    public void openUpdate(){
        Intent intent = new Intent(this, Update.class);
        startActivity(intent);
    }

    public void openRetrieve(){
        Intent intent = new Intent(this, Retrieve.class);
        startActivity(intent);
    }

    public void openDelete(){
        Intent intent = new Intent(this,Delete.class);
        startActivity(intent);
    }
}
