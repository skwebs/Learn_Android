package com.anshumemorial.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button Btn1 = findViewById(R.id.btn1);

        Btn1.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
            startActivity(intent);
        });

        //            Toast
        Context context = getApplicationContext();
        CharSequence text = getString(R.string.welcome_to_page_2);
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


    }
}