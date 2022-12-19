package com.example.testlistview;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterThongTin extends BaseAdapter {

    private Context context;
    private int layout;
    private List<ThongTin> list;

    public AdapterThongTin(Context context, int layout, List<ThongTin> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(layout, null);
        ThongTin thongtin = list.get(i);

        TextView txtTitle = view.findViewById(R.id.txtTitle);
        TextView txtTime = view.findViewById(R.id.txtTime);
        TextView txtContent = view.findViewById(R.id.txtContent);
        ImageView img = view.findViewById(R.id.imgView);

        txtTitle.setText(thongtin.getTitle());
        txtTime.setText(thongtin.getTime());
        txtContent.setText(thongtin.getContent());
        //img.setImageResource(thongtin.getImage());
        img.setImageURI(Uri.parse(thongtin.getImage()));
        return view;
    }
}
