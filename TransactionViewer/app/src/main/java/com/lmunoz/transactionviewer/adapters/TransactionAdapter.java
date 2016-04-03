package com.lmunoz.transactionviewer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lmunoz.transactionviewer.R;
import com.lmunoz.transactionviewer.model.Product;
import com.lmunoz.transactionviewer.model.Transaction;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Luis on 03/04/2016.
 */
public class TransactionAdapter extends RecyclerView.Adapter {

    private ArrayList<Transaction> transactions;

    public TransactionAdapter(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_transaction, parent, false);

        return new TransactionViewHolder(v, parent.getContext());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Transaction transaction = transactions.get(position);
        TransactionViewHolder productViewHolder = (TransactionViewHolder) holder;
        productViewHolder.render(transaction);
    }

    @Override
    public int getItemCount() {
        return transactions != null ? transactions.size() : 0;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions.clear();
        this.transactions.addAll(transactions);
        notifyDataSetChanged();
    }

    class TransactionViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "TransactionViewHolder";

        @Bind(R.id.transaction_amount)
        TextView transactionAmount;
        @Bind(R.id.transaction_gbp)
        TextView transactionGBP;

        private Context context;
        private View view;

        public TransactionViewHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
            this.context = context;
        }

        public void render(Transaction transaction) {
            transactionAmount.setText(String.format("%s %s", transaction.getCurrency().toUpperCase(), transaction.getAmount()));
            transactionGBP.setText(String.format("£%s", transaction.getAmountGBP().toString()));
        }
    }
}
