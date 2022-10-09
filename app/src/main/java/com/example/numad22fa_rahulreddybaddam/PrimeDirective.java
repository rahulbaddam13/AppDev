package com.example.numad22fa_rahulreddybaddam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PrimeDirective extends AppCompatActivity {

    Button b;
    Button but;
    int max = 5000;
    TextView vTextView;
    TextView tv;
    private Handler handler = new Handler();
    int j = 3;
    boolean isTerminated = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime_directive);
        b = (Button) findViewById(R.id.FindPrime);
        but = (Button) findViewById(R.id.Terminate);
        vTextView = (TextView) findViewById(R.id.PrimeTextView);
        tv = (TextView) findViewById(R.id.RunningView);

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setTitle("EXIT?");
        build.setMessage("Do you wanna exit")
                .setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = build.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        int width = (int) (getResources().getDisplayMetrics().widthPixels*0.7);
        int height = (int) (getResources().getDisplayMetrics().heightPixels*0.32);
        alertDialog.getWindow().setLayout(width,height);

    }

    public void runOnRunnableThread(View view) {
        isTerminated = false;
        runnableThread runnableThread = new runnableThread();
        new Thread(runnableThread).start();
    }

    public void terminateThread(View view) {
        isTerminated = true;
}
    class runnableThread implements Runnable {

        @SuppressLint("SetTextI18n")
        @Override
        public void run() {

            final int[] z = {3};
            for (int i = j; i <= max; i++) {

                if (isTerminated) {
                    tv.setText("Latest Prime " + z[0]);
                    return;
                }
                int finalI = i;
                handler.post(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        vTextView.setText("Current Number Searching " + finalI);
                        if (isPrime(finalI)) {
                            vTextView.setText("Prime Number Found " + finalI);
                            z[0] = finalI;
                            j= finalI +1;
                        }
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private boolean isPrime(int num) {

        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }


    }


