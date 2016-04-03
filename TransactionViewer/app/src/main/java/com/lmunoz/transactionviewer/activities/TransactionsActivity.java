package com.lmunoz.transactionviewer.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.lmunoz.transactionviewer.R;
import com.lmunoz.transactionviewer.TransactionViewerApplication;
import com.lmunoz.transactionviewer.adapters.TransactionAdapter;
import com.lmunoz.transactionviewer.model.Transaction;
import com.lmunoz.transactionviewer.util.Util;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TransactionsActivity extends AppCompatActivity {

    public static final String ARG_SKU = "SKU";

    @Bind(R.id.transactions_list)
    RecyclerView transactionsList;
    @Bind(R.id.transactions_total)
    TextView transactionsTotal;

    private TransactionAdapter adapter;
    private String sku;
    private ArrayList<Transaction> transactions;
    private Float total = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        ButterKnife.bind(this);

        sku = getIntent().getStringExtra(ARG_SKU);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(String.format(getString(R.string.transactions_for), sku));

        transactions = TransactionViewerApplication.getTransactionStorage().getTransactionsByProductInGBP(sku);

        adapter = new TransactionAdapter(transactions);
        transactionsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        transactionsList.setAdapter(adapter);

        for(Transaction transaction : transactions){
            total += transaction.getAmountGBP();
        }

        transactionsTotal.setText(String.format(getString(R.string.total), Util.toTwoDecimalPlaces(total)));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
