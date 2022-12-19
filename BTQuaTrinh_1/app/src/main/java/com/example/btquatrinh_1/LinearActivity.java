package com.example.btquatrinh_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class LinearActivity extends AppCompatActivity {

    LinearLayout ln1,ln2, ln3, ln4, ln5;
    SeekBar sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);

        ln1 = (LinearLayout) findViewById(R.id.linear1);
        ln2 = (LinearLayout) findViewById(R.id.linear2);
        ln3 = (LinearLayout) findViewById(R.id.linear3);
        ln4 = (LinearLayout) findViewById(R.id.linear4);
        ln5 = (LinearLayout) findViewById(R.id.linear5);
        sb= (SeekBar) findViewById(R.id.seekBar);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(i>0 && i<=5){
                    ln1.setBackgroundColor(Color.RED);
                    ln2.setBackgroundColor(Color.BLACK);
                    ln3.setBackgroundColor(Color.BLUE);
                    ln4.setBackgroundColor(Color.YELLOW);
                    ln5.setBackgroundColor(Color.GREEN);
                }
                else if(i>5 && i<=10){
                    ln1.setBackgroundColor(Color.GREEN);
                    ln2.setBackgroundColor(Color.YELLOW);
                    ln3.setBackgroundColor(Color.BLACK);
                    ln4.setBackgroundColor(Color.RED);
                    ln5.setBackgroundColor(Color.BLUE);
                }
                else if(i>10 && i<=15){
                    ln1.setBackgroundColor(Color.CYAN);
                    ln2.setBackgroundColor(Color.GRAY);
                    ln3.setBackgroundColor(Color.MAGENTA);
                    ln4.setBackgroundColor(Color.TRANSPARENT);
                    ln5.setBackgroundColor(Color.RED);
                }
                else if(i>15 && i<=20){
                    ln1.setBackgroundColor(Color.BLUE);
                    ln2.setBackgroundColor(Color.GREEN);
                    ln3.setBackgroundColor(Color.RED);
                    ln4.setBackgroundColor(Color.YELLOW);
                    ln5.setBackgroundColor(Color.BLACK);
                }
                else if(i>20 && i<25){
                    ln1.setBackgroundColor(Color.BLACK);
                    ln2.setBackgroundColor(Color.CYAN);
                    ln3.setBackgroundColor(Color.BLUE);
                    ln4.setBackgroundColor(Color.GREEN);
                    ln5.setBackgroundColor(Color.MAGENTA);
                }
                else if(i==25){
                    ln1.setBackgroundColor(Color.YELLOW);
                    ln2.setBackgroundColor(Color.RED);
                    ln3.setBackgroundColor(Color.CYAN);
                    ln4.setBackgroundColor(Color.BLUE);
                    ln5.setBackgroundColor(Color.BLACK);
                }
                else {
                    ln1.setBackgroundColor(Color.parseColor("#C2185B"));
                    ln2.setBackgroundColor(Color.parseColor("#7B1FA2"));
                    ln3.setBackgroundColor(Color.parseColor("#1976D2"));
                    ln4.setBackgroundColor(Color.parseColor("#0097A7"));
                    ln5.setBackgroundColor(Color.parseColor("#388E3C"));
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