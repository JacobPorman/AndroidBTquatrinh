package com.example.btquatrinh_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

public class TableActivity extends AppCompatActivity {

    TextView txt1, txt2, txt3, txt4, txt5;
    SeekBar sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        txt3 = (TextView) findViewById(R.id.txt3);
        txt4 = (TextView) findViewById(R.id.txt4);
        txt5 = (TextView) findViewById(R.id.txt5);
        sb = (SeekBar) findViewById(R.id.seekBarTableLayout);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(i>0 && i<=5){
                    txt1.setBackgroundColor(Color.RED);
                    txt2.setBackgroundColor(Color.BLACK);
                    txt3.setBackgroundColor(Color.BLUE);
                    txt4.setBackgroundColor(Color.YELLOW);
                    txt5.setBackgroundColor(Color.GREEN);
                }
                else if(i>5 && i<=10){
                    txt1.setBackgroundColor(Color.GREEN);
                    txt2.setBackgroundColor(Color.YELLOW);
                    txt3.setBackgroundColor(Color.BLACK);
                    txt4.setBackgroundColor(Color.RED);
                    txt5.setBackgroundColor(Color.BLUE);
                }
                else if(i>10 && i<=15){
                    txt1.setBackgroundColor(Color.CYAN);
                    txt2.setBackgroundColor(Color.GRAY);
                    txt3.setBackgroundColor(Color.MAGENTA);
                    txt4.setBackgroundColor(Color.TRANSPARENT);
                    txt5.setBackgroundColor(Color.RED);
                }
                else if(i>15 && i<=20){
                    txt1.setBackgroundColor(Color.BLUE);
                    txt2.setBackgroundColor(Color.GREEN);
                    txt3.setBackgroundColor(Color.RED);
                    txt4.setBackgroundColor(Color.YELLOW);
                    txt5.setBackgroundColor(Color.BLACK);
                }
                else if(i>20 && i<25){
                    txt1.setBackgroundColor(Color.BLACK);
                    txt2.setBackgroundColor(Color.CYAN);
                    txt3.setBackgroundColor(Color.BLUE);
                    txt4.setBackgroundColor(Color.GREEN);
                    txt5.setBackgroundColor(Color.MAGENTA);
                }
                else if(i==25){
                    txt1.setBackgroundColor(Color.YELLOW);
                    txt2.setBackgroundColor(Color.RED);
                    txt3.setBackgroundColor(Color.CYAN);
                    txt4.setBackgroundColor(Color.BLUE);
                    txt5.setBackgroundColor(Color.BLACK);
                }
                else {
                    txt1.setBackgroundColor(Color.parseColor("#AFB42B"));
                    txt2.setBackgroundColor(Color.parseColor("#388E3C"));
                    txt3.setBackgroundColor(Color.parseColor("#C2185B"));
                    txt4.setBackgroundColor(Color.parseColor("#303F9F"));
                    txt5.setBackgroundColor(Color.parseColor("#E64A19"));
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