package com.example.numad22fa_rahulreddybaddam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void ShowMsg(View view) {
        Toast.makeText(getApplicationContext(), "Name: Rahul Reddy \nEmail: baddam.ra@northeastern.edu", Toast.LENGTH_LONG).show();
    }
}