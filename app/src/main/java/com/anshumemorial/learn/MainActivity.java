package com.anshumemorial.learn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button getData = findViewById(R.id.get_data);
        Button goToSecondPage = findViewById(R.id.go_to_second_page);
        Button showToastBtn = findViewById(R.id.show_toast_btn);

        getData.setOnClickListener(v -> getData());

        goToSecondPage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        });

        showToastBtn.setOnClickListener(v -> Toast.makeText(this,"Welcome in First Page", Toast.LENGTH_LONG).show());
//                Toast
        Context context = getApplicationContext();
        CharSequence text = getString(R.string.welcome_to_page_1);
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


    }

    private void getData() {
        String url ="https://anshumemorial.in/api/api/v1/user/2";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    try {
                        CharSequence res = response.getInt("id") + "\n" + response.getString("name") + "\n" + response.getString("email");
                        Toast.makeText(this, res, Toast.LENGTH_LONG).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, System.out::print);

        requestQueue.add(jsonObjectRequest);

// Access the RequestQueue through your singleton class.
//        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}