package com.viralandroid.futbolistik;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import utils.Constants;

public class CustomAdapter extends ArrayAdapter<Items> {

    private Context context;
    private ViewHolder viewHolder;
    private List<Items> arrayList = new ArrayList<Items>();
    public CustomAdapter(Context context, List<Items> list_items) {
        super(context, R.layout.item_layout, list_items);
        this.context = context;
        this.arrayList = list_items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.item_layout, parent,false);
            viewHolder = new ViewHolder();
            viewHolder.hbrbaslik = (TextView)view.findViewById(R.id.name);
            viewHolder.hbrresim = (ImageView)view.findViewById(R.id.img);
            viewHolder.okunma = (TextView)view.findViewById(R.id.price);
            viewHolder.hbrtarih = (TextView)view.findViewById(R.id.category);
            viewHolder.yzradi = (TextView)view.findViewById(R.id.yazar);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.hbrbaslik.setText(arrayList.get(position).gethbrbaslik());
        viewHolder.okunma.setText(String.valueOf(arrayList.get(position).getokunma()).toString() + "" + " kere okundu.");
        viewHolder.hbrtarih.setText("Tarih: " + "" + String.valueOf(arrayList.get(position).gethbrtarih()).toString());
        viewHolder.yzradi.setText("Yazar: " + "" + String.valueOf(arrayList.get(position).getyzradi()).toString());
        Picasso.with(getContext()).load(Constants.URL_IMAGES+arrayList.get(position).gethbrresim()).resize(140,140).into(viewHolder.hbrresim);
        return view;
    }

    private static class ViewHolder{
        TextView hbrbaslik;
        ImageView hbrresim;
        TextView okunma;
        TextView hbrtarih;
        TextView yzradi;
    }
}