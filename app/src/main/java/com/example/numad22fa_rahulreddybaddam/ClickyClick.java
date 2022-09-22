package com.example.numad22fa_rahulreddybaddam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClickyClick extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky_click);
        pressedA();
        pressedB();
        pressedC();
        pressedD();
        pressedE();
        pressedF();
    }
    public void pressedA(){
        TextView changingText = (TextView) findViewById(R.id.textView3);
        Button change = (Button) findViewById(R.id.button);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changingText.setText("Pressed : A");
            }
        });
    }
    public void pressedB(){
        TextView changingText = (TextView) findViewById(R.id.textView3);
        Button change = (Button) findViewById(R.id.button2);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changingText.setText("Pressed : B");
            }
        });
    }
    public void pressedC(){
        TextView changingText = (TextView) findViewById(R.id.textView3);
        Button change = (Button) findViewById(R.id.button5);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changingText.setText("Pressed : C");
            }
        });
    }
    public void pressedD(){
        TextView changingText = (TextView) findViewById(R.id.textView3);
        Button change = (Button) findViewById(R.id.button6);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changingText.setText("Pressed : D");
            }
        });
    }
    public void pressedE(){
        TextView changingText = (TextView) findViewById(R.id.textView3);
        Button change = (Button) findViewById(R.id.button7);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changingText.setText("Pressed : E");
            }
        });
    }
    public void pressedF(){
        TextView changingText = (TextView) findViewById(R.id.textView3);
        Button change = (Button) findViewById(R.id.button8);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changingText.setText("Pressed : F");
            }
        });
    }

}
