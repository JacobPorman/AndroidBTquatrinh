package com.example.testlistview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ThongTinActivity extends AppCompatActivity {

    EditText edTitle, edContent;
    ImageView imgView;
    Button btnAdd, btnBack;
    int SELECT_IMAGE_CODE=1;
    Uri uri, imgOld;
    public static boolean save = false;
    private boolean btnADD_isClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin);

        edTitle = (EditText) findViewById(R.id.edtTitle_inTB);
        edContent = (EditText) findViewById(R.id.edtContent_inTB);
        imgView = (ImageView) findViewById(R.id.imgAdd_inTB);
        btnAdd = (Button) findViewById(R.id.btnHuyTB);
        btnBack = (Button) findViewById(R.id.btnBack_inTT);

        //Neu o phai them thi no la sua?, show du lieu cua dong da click ben MainActivity
        if(MainActivity.create == false){
            show();
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Chọn ảnh mới = true
                btnADD_isClick = true;
                //requestPermissions();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "image"), SELECT_IMAGE_CODE);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                MainActivity.update=false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            uri = data.getData();
            imgView.setImageURI(uri);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_thongtin, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Lấy ngày hiện tại và định dạng cho nó
        java.util.Date date = new java.util.Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date);

        if(item.getItemId()==R.id.menu_save){
            //Kiểm tra nếu là một thao tác update
            if(MainActivity.update==true){
                //Xóa một note cũ tại vị trí đã chọn
                MainActivity.arrayList.remove(MainActivity.viTriNote);
                Toast.makeText(this,"update", Toast.LENGTH_SHORT).show();
            }
            //Ngược lại là thao tác thêm
            else Toast.makeText(this,"save", Toast.LENGTH_SHORT).show();

            Intent getCTinfo = new Intent(getApplicationContext(), MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("titleInfor", edTitle.getText().toString());
            bundle.putString("timeInfor", time);
            bundle.putString("contentInfor", edContent.getText().toString());
            //Xử lý Uri hình ảnh khi Save
            //Neu btnAdd dc click thi thuc hien lay gia tri binh thuong
            if(btnADD_isClick==true) bundle.putString("imageInfor", uri.toString());
            //Ngược lại, lấy giá trị là Uri cũ
            else bundle.putString("imageInfor", imgOld.toString());
            getCTinfo.putExtras(bundle);

            startActivity(getCTinfo);
            //Gán giá trị để gọi bên MainActivity
            save = true;
            MainActivity.update=false;

        }
        return super.onOptionsItemSelected(item);
    }

    private void show(){
        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString("titleKey");
        String time = bundle.getString("timeKey");
        String content = bundle.getString("contentKey");
        String img = bundle.getString("imageKey");

        edTitle.setText(title);
        edContent.setText(content);
        imgView.setImageURI(Uri.parse(img));
        //Luu uri cũ để xử lí khi save
        imgOld = Uri.parse(img);
    }
}