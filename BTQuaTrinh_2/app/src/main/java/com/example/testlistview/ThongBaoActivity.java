package com.example.testlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class ThongBaoActivity extends AppCompatActivity {

    EditText edTT, edCT;
    Button btnHuy, btnHenGio;
    ImageView imgView;
    TextView txtTime;
    TimePicker timePK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao);

        edTT = (EditText) findViewById(R.id.edtTitle_inTB);
        edCT = (EditText) findViewById(R.id.edtContent_inTB);
        btnHuy = (Button) findViewById(R.id.btnHuyTB);
        btnHenGio = (Button) findViewById(R.id.btnHenGio);
        imgView = (ImageView) findViewById(R.id.imgAdd_inTB);
        txtTime = (TextView) findViewById(R.id.txtHenGio);
        timePK = (TimePicker) findViewById(R.id.timepicker);

        show();

        //bao thuc
        //Set notificationId & text
        Intent intent = new Intent(ThongBaoActivity.this, AlarmReceiver.class);
        intent.putExtra("notificationId", 1);
        intent.putExtra("todo", edTT.getText().toString());

        //getBradcast(context, requestCode, intent, flags)
        PendingIntent alarmIntent = PendingIntent.getBroadcast(ThongBaoActivity.this,
                0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);

        btnHenGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = timePK.getCurrentHour();
                int minute = timePK.getCurrentMinute();

                //Create time
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();
                //set alarm
                //set(type, milliseconds, intent)
                alarm.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);
                txtTime.setText("Đã hẹn giờ");
                Toast.makeText(ThongBaoActivity.this, "Hẹn giờ thành công", Toast.LENGTH_SHORT).show();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarm.cancel(alarmIntent);
                Toast.makeText(ThongBaoActivity.this, "Đã hủy hẹn giờ", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void show(){
        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString("titleKey");
        String time = bundle.getString("timeKey");
        String content = bundle.getString("contentKey");
        String img = bundle.getString("imageKey");

        edTT.setText(title);
        edCT.setText(content);
        imgView.setImageURI(Uri.parse(img));
    }
}