package com.example.numad22fa_rahulreddybaddam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openClicky();
            }
        });
    }

    public void openClicky() {
        Intent intent = new Intent(this, ClickyClick.class);
        startActivity(intent);
    }

    public void ShowMsg(View view) {
        Toast.makeText(getApplicationContext(), "Name: Rahul Reddy \nEmail: baddam.ra@northeastern.edu", Toast.LENGTH_LONG).show();
    }
}