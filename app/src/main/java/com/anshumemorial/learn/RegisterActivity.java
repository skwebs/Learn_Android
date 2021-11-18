package com.anshumemorial.learn;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    // creating variables for our edit text
    private EditText etName, etEmail;

    // creating a strings for storing our values from edittext fields.
    private String name, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        // creating variable for button
        Button submitBtn = findViewById(R.id.submit_btn);

        submitBtn.setOnClickListener(v -> {
            // getting data from edittext fields.
            name = etName.getText().toString();
            email = etEmail.getText().toString();


            // validating the text fields if empty or not.
            if (TextUtils.isEmpty(name)) {
                etName.setError("Please enter Name");
                Toast.makeText(this, "Name is required", Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(email)) {
                etEmail.setError("Please enter Email");
                Toast.makeText(this, "Email is required.", Toast.LENGTH_LONG).show();
            } else {
                // calling method to add data to Firebase Firestore.
                registerUser(name, email);
            }
        });
    }

    private void registerUser(String name, String email) {
        String apiUrl = "https://anshumemorial.in/api/api/v1/user";

        // creating a new variable for our request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,apiUrl,
                response -> {
                    Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
                    Log.e("Registration Response: ", response);
                },
                error -> {
                    Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
                    Log.i("Registration Error: ", error.toString());
                }){
//            add the parameter to the request
            @Override
            protected Map<String , String> getParams() {
                // below line we are creating a map for storing
                // our values in key and value pair.
                Map<String,String > params = new HashMap<>();
                params.put("name", name);
//                params.put("first_name", name);
                params.put("email", email);
                return params;
            }
        };
        // below line is to make
        // a json object request.
        requestQueue.add(stringRequest);
    }



}