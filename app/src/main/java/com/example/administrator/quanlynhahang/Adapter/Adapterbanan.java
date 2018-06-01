package com.example.administrator.quanlynhahang.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.quanlynhahang.BanAn;
import com.example.administrator.quanlynhahang.DTO.banan;
import com.example.administrator.quanlynhahang.R;

import java.util.ArrayList;

public class Adapterbanan extends BaseAdapter {
    public ArrayList<banan> DanhSachBanAn;
    int resourceId;
    Context context;

    public Adapterbanan(Context context, int resourceId, ArrayList<banan> danhSachBanAn ) {
        DanhSachBanAn = danhSachBanAn;
        this.context = context;
        this.resourceId = resourceId;
    }

    @Override
    public int getCount() {
        return DanhSachBanAn.size();
    }

    @Override
    public Object getItem(int position) {
        return DanhSachBanAn.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View gridView;
            gridView = inflater.inflate(R.layout.item_ban, null);
        TextView txtTenBan = gridView.findViewById(R.id.txtTenBan);
        txtTenBan.setText(DanhSachBanAn.get(position).getTenbanan());
        TextView txtTinhTrang = gridView.findViewById(R.id.txtTinhTrang);
        txtTinhTrang.setText(DanhSachBanAn.get(position).getTinhtrang());
        ImageView imgBanAn = gridView.findViewById(R.id.imgBan);
        Glide.with(gridView).load(DanhSachBanAn.get(position).anhbanan).into(imgBanAn);
        return gridView;
    }
}
