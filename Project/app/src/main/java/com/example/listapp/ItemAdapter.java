package com.example.listapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

    public interface OnQuantityChangeListener {
        void onAddClick(int position);
        void onRemoveClick(int position);
    }

    LayoutInflater mInflater;
    Context context;
    String[] items;
    String[] prices;
    String[] descriptions;
    int[] amounts;
    OnQuantityChangeListener listener;

    public ItemAdapter(Context c, String[] i, String[] p, String[] d, int[] a, OnQuantityChangeListener listener) {
        context = c;
        items = i;
        prices = p;
        descriptions = d;
        amounts = a;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.my_listview_detail, null);
        TextView nameTextView = (TextView) v.findViewById(R.id.nameTextView);
        TextView descriptionTextView = (TextView) v.findViewById(R.id.descriptionTextView);
        TextView priceTextView = (TextView) v.findViewById(R.id.priceTextView);
        TextView amountTextView = (TextView) v.findViewById(R.id.amountTextView);
        Button addBtn = (Button) v.findViewById(R.id.addBtn);
        Button removeBtn = (Button) v.findViewById(R.id.removeBtn);
        Button infoBtn = (Button) v.findViewById(R.id.infoBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAddClick(i);
                amountTextView.setText(String.valueOf(amounts[i]));
            }
        });

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRemoveClick(i);
                amountTextView.setText(String.valueOf(amounts[i]));
            }
        });

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showDetailActivity = new Intent(context, DetailActivity.class);
                showDetailActivity.putExtra("com.example.listapp.ITEM_INDEX", i);
                context.startActivity(showDetailActivity);
            }
        });

        String name = items[i];
        String desc = descriptions[i];
        String cost = prices[i];

        nameTextView.setText(name);
        descriptionTextView.setText(desc);
        priceTextView.setText("$" + cost);
        return v;
    }
}
