package com.example.btquatrinh_3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {

    ListView lvItem;
    ArrayList<String> arrayList_title, arrayList_link, arrayList_content, arrayList_image;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        lvItem = (ListView) findViewById(R.id.listviewItem);
        arrayList_title = new ArrayList<>();
        arrayList_link = new ArrayList<>();
        arrayList_content = new ArrayList<>();
        arrayList_image = new ArrayList<>();
        adapter = new ArrayAdapter(ItemActivity.this, android.R.layout.simple_list_item_1, arrayList_title);
        lvItem.setAdapter(adapter);

        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dialog dialog = new Dialog(ItemActivity.this);
                dialog.setContentView(R.layout.dialog_custom);

                TextView tvTen = dialog.findViewById(R.id.textViewTen);
                TextView tvNoiDung = dialog.findViewById(R.id.textViewNoiDung);
                ImageView imgView = dialog.findViewById(R.id.imageLink);
                Button btnCancel = dialog.findViewById(R.id.buttonCancel);
                Button btnMore = dialog.findViewById(R.id.buttonMore);

                tvTen.setText(arrayList_title.get(i));
                tvNoiDung.setText(arrayList_content.get(i));
                Picasso.get().load(arrayList_image.get(i)).into(imgView);

                dialog.show();

                btnMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(arrayList_link.get(i)));
                        startActivity(intent);
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        new ItemActivity.readRSS().execute(MainActivity.url);
    }
    private class readRSS extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {

            StringBuilder content = new StringBuilder();

            try {
                URL url = new URL(strings[0]);

                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line ="";
                while ((line = bufferedReader.readLine())!=null){
                    content.append(line);
                }

                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");

            String description="";
            String image="";
            String title="";
            String link ="";
            String descContent="";
            for(int i=0; i<nodeList.getLength();i++){
                Element element = (Element) nodeList.item(i);

                title = parser.getValue(element, "title");
                link = parser.getValue(element, "link");

                description = parser.getValueDesc(element, "description");
                image = parser.getImageLink(description);
                descContent = parser.getDescContent(description);

                arrayList_title.add(title);
                arrayList_link.add(link);
                arrayList_content.add(descContent);
                arrayList_image.add(image);
            }
            adapter.notifyDataSetChanged();
        }
    }
}