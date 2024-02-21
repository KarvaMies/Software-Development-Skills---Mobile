package com.example.listapp;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ItemAdapter.OnQuantityChangeListener {

    ListView myListView;
    String[] items;
    String[] prices;
    String[] descriptions;
    int[] amounts;
    double totalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        ListView myListView = (ListView) findViewById(R.id.myListView);
        TextView totalCostTextView = (TextView) findViewById(R.id.totalCostTextView);
        TextView totalCostInfoTextView = (TextView) findViewById(R.id.totalCostInfoTextView);
        items = res.getStringArray(R.array.items);
        prices = res.getStringArray(R.array.prices);
        descriptions = res.getStringArray(R.array.descriptions);
        amounts = res.getIntArray(R.array.amounts);

        ItemAdapter itemAdapter = new ItemAdapter(this, items, prices, descriptions, amounts, this);
        myListView.setAdapter(itemAdapter);

        if (totalCost == 0) {
            totalCostTextView.setText(null);
            totalCostInfoTextView.setText(null);
        } else {
            totalCostTextView.setText(String.valueOf(totalCost));
            totalCostInfoTextView.setText(getResources().getString(R.string.total_cost_info));
        }
    }

    @Override
    public void onAddClick(int i) {
        amounts[i]++;
        if (amounts[i] > 99) {
            amounts[i] = 99;
        }
        updateTotalCost();
    }

    @Override
    public void onRemoveClick(int i) {
        amounts[i]--;
        if (amounts[i] < 0) {
            amounts[i] = 0;
        }
        updateTotalCost();
    }

    private void updateTotalCost() {
        TextView totalCostTextView = (TextView) findViewById(R.id.totalCostTextView);
        TextView totalCostInfoTextView = (TextView) findViewById(R.id.totalCostInfoTextView);
        double cost = 0.0;
        for (int i = 0; i < items.length; i++) {
            cost += amounts[i] * Float.parseFloat(prices[i]);
            totalCost = (int) (cost * 100) / 100.0;
        }
        Log.d("TotalCost", "Total Cost: $" + totalCost);
        if (totalCost == 0) {
            totalCostTextView.setText(null);
            totalCostInfoTextView.setText(null);
        } else {
            totalCostTextView.setText("$" + String.valueOf(totalCost));
            totalCostInfoTextView.setText(getResources().getString(R.string.total_cost_info));
        }
    }
}