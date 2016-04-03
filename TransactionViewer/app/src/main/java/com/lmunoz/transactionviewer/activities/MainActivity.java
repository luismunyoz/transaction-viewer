package com.lmunoz.transactionviewer.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lmunoz.transactionviewer.R;
import com.lmunoz.transactionviewer.TransactionViewerApplication;
import com.lmunoz.transactionviewer.adapters.ProductAdapter;
import com.lmunoz.transactionviewer.model.Product;
import com.lmunoz.transactionviewer.util.ProductComparator;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ProductAdapter.ProductAdapterCallback {

    @Bind(R.id.productsList)
    RecyclerView productsList;

    private ProductAdapter adapter;
    private ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        products = TransactionViewerApplication.getTransactionStorage().getProductsWithCount();
        Collections.sort(products, new ProductComparator());

        adapter = new ProductAdapter(products, this);
        productsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        productsList.setAdapter(adapter);
    }

    @Override
    public void onProductClicked(String sku) {
        Intent i = new Intent(MainActivity.this, TransactionsActivity.class);
        i.putExtra(TransactionsActivity.ARG_SKU, sku);
        startActivity(i);
    }
}
