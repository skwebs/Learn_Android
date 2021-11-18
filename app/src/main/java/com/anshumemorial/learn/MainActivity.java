package com.anshumemorial.learn;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        Button goToRegPage = findViewById(R.id.go_to_reg_page);
        Button userList = findViewById(R.id.user_list_btn);

        getData.setOnClickListener(v -> getData());

        goToSecondPage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
            startActivity(intent);
        });

        goToRegPage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        userList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, UserListActivity.class);
            startActivity(intent);
        });
    }

    private void getData() {
        String url ="https://naitaknik.com/ci4_ama_api/api/v1/student/1";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    try {
                        CharSequence res = response.getInt("id") + "\n" + response.getString("first_name") + "\n" + response.getString("email");
                        Toast.makeText(this, res, Toast.LENGTH_LONG).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                },error -> {
                    Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
                    Log.i("Registration Error: ", error.toString());
                });

        requestQueue.add(jsonObjectRequest);

// Access the RequestQueue through your singleton class.
//        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}