package com.example.btquatrinh_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arr_danhMuc;
    public static String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listviewMain);
        arr_danhMuc = new ArrayList<>();

        arr_danhMuc.add("Thế giới");
        arr_danhMuc.add("Thời sự");
        arr_danhMuc.add("Kinh doanh");
        arr_danhMuc.add("Du lịch");
        arr_danhMuc.add("Giải trí");
        arr_danhMuc.add("Thể thao");
        arr_danhMuc.add("Pháp luật");
        arr_danhMuc.add("Giáo dục");
        arr_danhMuc.add("Sức khỏe");
        //Lay du lieu tu arraylist va do ra list view
        //khoi tao
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arr_danhMuc);
        //do du lieu
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                switch (i){
                    case 0:
                        Toast.makeText(MainActivity.this, "Thế giới", Toast.LENGTH_SHORT).show();
                        url = "https://vnexpress.net/rss/the-gioi.rss";
                        intent = new Intent(MainActivity.this, ItemActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "Thời sự", Toast.LENGTH_SHORT).show();
                        url = "https://vnexpress.net/rss/thoi-su.rss";
                        intent = new Intent(MainActivity.this, ItemActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "Kinh doanh", Toast.LENGTH_SHORT).show();
                        url = "https://vnexpress.net/rss/kinh-doanh.rss";
                        intent = new Intent(MainActivity.this, ItemActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this, "Du lịch", Toast.LENGTH_SHORT).show();
                        url = "https://vnexpress.net/rss/du-lich.rss";
                        intent = new Intent(MainActivity.this, ItemActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        Toast.makeText(MainActivity.this, "Giải trí", Toast.LENGTH_SHORT).show();
                        url = "https://vnexpress.net/rss/giai-tri.rss";
                        intent = new Intent(MainActivity.this, ItemActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        Toast.makeText(MainActivity.this, "Thể thao", Toast.LENGTH_SHORT).show();
                        url = "https://vnexpress.net/rss/the-thao.rss";
                        intent = new Intent(MainActivity.this, ItemActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        Toast.makeText(MainActivity.this, "Pháp luật", Toast.LENGTH_SHORT).show();
                        url = "https://vnexpress.net/rss/phap-luat.rss";
                        intent = new Intent(MainActivity.this, ItemActivity.class);
                        startActivity(intent);
                        break;
                    case 7:
                        Toast.makeText(MainActivity.this, "Giáo dục", Toast.LENGTH_SHORT).show();
                        url = "https://vnexpress.net/rss/giao-duc.rss";
                        intent = new Intent(MainActivity.this, ItemActivity.class);
                        startActivity(intent);
                        break;
                    case 8:
                        Toast.makeText(MainActivity.this, "Sức khỏe", Toast.LENGTH_SHORT).show();
                        url = "https://vnexpress.net/rss/suc-khoe.rss";
                        intent = new Intent(MainActivity.this, ItemActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}