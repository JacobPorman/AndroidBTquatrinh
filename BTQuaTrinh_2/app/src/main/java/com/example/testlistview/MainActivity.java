package com.example.testlistview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static boolean create = false;
    public static boolean update = false;

    ListView listView;
    static ArrayList<ThongTin> arrayList = new ArrayList<>();
    AdapterThongTin adapterThongTin;
    public static int viTriNote=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listviewthongtin);

        /*
        arrayList.add(new ThongTin("hung", "3120410193", "12h", R.drawable.hung_001));
         */

        adapterThongTin = new AdapterThongTin(this, R.layout.layout_thongtin, arrayList);
        listView.setAdapter(adapterThongTin);

        if(ThongTinActivity.save==true){
            try {
                show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else readFile();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                create = false;
                update = true;

                ThongTin thongTin = arrayList.get(i);

                Intent getCTinfo = new Intent(getApplicationContext(), ThongTinActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("titleKey", thongTin.getTitle());
                bundle.putString("timeKey", thongTin.getTime());
                bundle.putString("contentKey", thongTin.getContent());
                bundle.putString("imageKey", thongTin.getImage());
                getCTinfo.putExtras(bundle);

                startActivity(getCTinfo);

                //set vi tri da click
                viTriNote=i;
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                viTriNote=i; //lay vi tri notes da chon

                AlertDialog.Builder alerdialog = new AlertDialog.Builder(MainActivity.this);
                alerdialog.setMessage("Hãy chọn thao tác");
                //Positive la khang dinh hanh dong Xoa
                alerdialog.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Xoa notes da chon
                        arrayList.remove(viTriNote);
                        //cap nhat lai list notes
                        adapterThongTin.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this,"Đã xóa", Toast.LENGTH_SHORT).show();

                        writeFile();
                    }
                });
                //nega la phu dinh hanh dong xoa => lam hanh dong khac
                alerdialog.setNegativeButton("Hẹn giờ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"Hẹn giờ", Toast.LENGTH_SHORT).show();
                        henGio();
                    }
                });
                alerdialog.show();
                return false;
            }
        });

    }

    private void show() throws IOException {
        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString("titleInfor");
        String time = bundle.getString("timeInfor");
        String content = bundle.getString("contentInfor");
        String image = bundle.getString("imageInfor");

        arrayList.add(new ThongTin(title,content,time,image));

//        //Luu data vao file
//        File file = new File("C:/Users/Admin/AndroidStudioProjects/BTQuaTrinh_2/app/src/main/res/raw/data.txt");
//        OutputStream outputStream = new FileOutputStream(file, true); // true de ghi du lieu vao cuoi file
//        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
//        for (int i=0;i<arrayList.size();i++){
//            ThongTin thongTin = arrayList.get(i);
//            String []data = {thongTin.getTitle(), thongTin.getTime(), thongTin.getContent(), thongTin.getImage()};
//
//            for (String item: data){
//                outputStreamWriter.write(item);
//                // Dùng để xuống hàng
//                outputStreamWriter.write("\n");
//            }
//            // Đây là phương thức quan trọng!
//            // Nó sẽ bắt chương trình chờ ghi dữ liệu xong thì mới kết thúc chương trình.
//            outputStreamWriter.flush();
//        }

        //Ghi vao file data
       writeFile();

        adapterThongTin.notifyDataSetChanged();
    }

    private void readFile(){
//        FileInputStream fileInputStream = null;
//        BufferedReader bufferedReader = null;
//        try {
//            fileInputStream = openFileInput("dataNotes");
//            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
//            String line = bufferedReader.readLine();
//            while (line != null) {
//                Toast.makeText(MainActivity.this, line, Toast.LENGTH_SHORT).show();
//                line = bufferedReader.readLine();
//            }
//        } catch (FileNotFoundException ex) {
//
//        } catch (IOException ex) {
//
//        } finally {
//            try {
//                bufferedReader.close();
//                fileInputStream.close();
//            } catch (IOException ex) {
//
//            }
//        }

        try (FileInputStream fin = openFileInput("dataNotes")) {
            int row = 1;
            int data = fin.read();
            StringBuilder line = new StringBuilder();
            String set_title="", set_time="", set_content="", set_image="";
            ArrayList<String> dong = new ArrayList<>();
            while (data != -1) {
                if (((char) data == '\n') || ((char) data == '\r')) {
                    //Toast.makeText(MainActivity.this, line.toString(), Toast.LENGTH_SHORT).show();
                    dong.add(line.toString());
                    line.delete(0, line.length());
                    data = fin.read();
                    continue;
                }
                line.append((char) data);
                data = fin.read();
            }
            for (int d=0; d<dong.size();d++){
                if (row==1){
                    set_title = dong.get(d);
                    row++;
                }else if (row==2){
                    set_time = dong.get(d);
                    row++;
                }
                else if (row==3){
                    set_content = dong.get(d);
                    row++;
                }
                else {
                    set_image = dong.get(d);
                    row = 1;
                    arrayList.add(new ThongTin(set_title, set_content, set_time, set_image));
                    adapterThongTin.notifyDataSetChanged();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFile(){
        try {
            String dataNotes="";
            for (int i=0;i<arrayList.size();i++){
                ThongTin thongTin = arrayList.get(i);
                String []data = {thongTin.getTitle(), thongTin.getTime(), thongTin.getContent(), thongTin.getImage()};
                for (String item : data){
                    dataNotes += item + "\n";
                }
            }
            //MODE_APPEND: viet lien file truoc do, con private la viet de len
            FileOutputStream fout = openFileOutput("dataNotes", MODE_PRIVATE);
            fout.write(dataNotes.getBytes(StandardCharsets.UTF_8));
            fout.close();
        }catch (Exception ex){

        }
    }

    private void henGio(){
                ThongTin thongTin = arrayList.get(viTriNote);
                Intent getCTinfo = new Intent(getApplicationContext(), ThongBaoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("titleKey", thongTin.getTitle());
                bundle.putString("timeKey", thongTin.getTime());
                bundle.putString("contentKey", thongTin.getContent());
                bundle.putString("imageKey", thongTin.getImage());
                getCTinfo.putExtras(bundle);

                startActivity(getCTinfo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_create){
            Toast.makeText(this,"create", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), ThongTinActivity.class);
            startActivity(intent);

            create = true;
        }
        else if (item.getItemId()==R.id.menu_deleteAll){
            Toast.makeText(this,"deleteAll", Toast.LENGTH_SHORT).show();
            arrayList.clear();
            adapterThongTin.notifyDataSetChanged();

            writeFile();
        }
        else {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_thong_tin_nhom);
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}