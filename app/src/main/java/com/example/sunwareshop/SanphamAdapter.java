package com.example.sunwareshop;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sunwareshop.Database.Product;

import java.util.List;

public class SanphamAdapter extends ArrayAdapter<Product> {
    public SanphamAdapter(@NonNull Context context, int resource, List<Product> items) {
        super(context, resource, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.listview_sanpham, null);
        }
        Product p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView nameProduct = view.findViewById(R.id.nameProduct);
            TextView priceProduct = view.findViewById(R.id.priceProduct);
            ImageView img = view.findViewById(R.id.imageView) ;
            nameProduct.setText(p.getTen_san_pham());
            priceProduct.setText(p.getGia_ban()+" VNĐ");
            img.setImageResource(R.drawable.avatar);
        }
        return view;
    }
}
