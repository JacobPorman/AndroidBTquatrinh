package com.example.btquatrinh_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

public class RelativeActivity extends AppCompatActivity {

    RelativeLayout rl1, rl2, rl3, rl4, rl5;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative);

        rl1 = (RelativeLayout) findViewById(R.id.RL1);
        rl2 = (RelativeLayout) findViewById(R.id.RL2);
        rl3 = (RelativeLayout) findViewById(R.id.RL3);
        rl4 = (RelativeLayout) findViewById(R.id.RL4);
        rl5 = (RelativeLayout) findViewById(R.id.RL5);
        seekBar = (SeekBar) findViewById(R.id.seekBarRelative);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(i>0 && i<=5){
                    rl1.setBackgroundColor(Color.RED);
                    rl2.setBackgroundColor(Color.BLACK);
                    rl3.setBackgroundColor(Color.BLUE);
                    rl4.setBackgroundColor(Color.YELLOW);
                    rl5.setBackgroundColor(Color.GREEN);
                }
                else if(i>5 && i<=10){
                    rl1.setBackgroundColor(Color.GREEN);
                    rl2.setBackgroundColor(Color.YELLOW);
                    rl3.setBackgroundColor(Color.BLACK);
                    rl4.setBackgroundColor(Color.RED);
                    rl5.setBackgroundColor(Color.BLUE);
                }
                else if(i>10 && i<=15){
                    rl1.setBackgroundColor(Color.CYAN);
                    rl2.setBackgroundColor(Color.GRAY);
                    rl3.setBackgroundColor(Color.MAGENTA);
                    rl4.setBackgroundColor(Color.TRANSPARENT);
                    rl5.setBackgroundColor(Color.RED);
                }
                else if(i>15 && i<=20){
                    rl1.setBackgroundColor(Color.BLUE);
                    rl2.setBackgroundColor(Color.GREEN);
                    rl3.setBackgroundColor(Color.RED);
                    rl4.setBackgroundColor(Color.YELLOW);
                    rl5.setBackgroundColor(Color.BLACK);
                }
                else if(i>20 && i<25){
                    rl1.setBackgroundColor(Color.BLACK);
                    rl2.setBackgroundColor(Color.CYAN);
                    rl3.setBackgroundColor(Color.BLUE);
                    rl4.setBackgroundColor(Color.GREEN);
                    rl5.setBackgroundColor(Color.MAGENTA);
                }
                else if(i==25){
                    rl1.setBackgroundColor(Color.YELLOW);
                    rl2.setBackgroundColor(Color.RED);
                    rl3.setBackgroundColor(Color.CYAN);
                    rl4.setBackgroundColor(Color.BLUE);
                    rl5.setBackgroundColor(Color.BLACK);
                }
                else {
                    rl1.setBackgroundColor(Color.parseColor("#D32F2F"));
                    rl2.setBackgroundColor(Color.parseColor("#1976D2"));
                    rl3.setBackgroundColor(Color.parseColor("#7B1FA2"));
                    rl4.setBackgroundColor(Color.parseColor("#388E3C"));
                    rl5.setBackgroundColor(Color.parseColor("#FBC02D"));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_custom);
        dialog.show();
        return super.onOptionsItemSelected(item);
    }
}