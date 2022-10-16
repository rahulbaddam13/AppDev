package com.example.numad22fa_rahulreddybaddam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button aboutMe;
    private Button LinkCollector;
    private Button PrimeActButton;
    private Button Loc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button4);
        aboutMe = (Button) findViewById(R.id.button3);
        LinkCollector = (Button) findViewById(R.id.LinkCollector);
        PrimeActButton = (Button) findViewById(R.id.PrimeButton);
        Loc = (Button) findViewById(R.id.Location) ;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openClicky();
            }
        });
        aboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAboutMe();
            }
        });
        LinkCollector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLinkCollector();
            }
        });
        PrimeActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPrime();
            }
        });
        Loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLocation();
            }
        });
    }

    private void openLocation() {
        Intent intent = new Intent(this, LocationNew.class);
        startActivity(intent);
    }

    private void openPrime() {
        Intent intent = new Intent(this, PrimeDirective.class);
        startActivity(intent);
    }


    public void openClicky() {
        Intent intent = new Intent(this, ClickyClick.class);
        startActivity(intent);
    }
    public void openAboutMe() {
        Intent intent = new Intent(this, AboutMee.class);
        startActivity(intent);
    }
    public void openLinkCollector() {
        Intent intent = new Intent(this, LinkCollector.class);
        startActivity(intent);
    }
}