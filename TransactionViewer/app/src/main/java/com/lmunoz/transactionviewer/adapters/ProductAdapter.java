package com.lmunoz.transactionviewer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lmunoz.transactionviewer.R;
import com.lmunoz.transactionviewer.model.Product;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Luis on 03/04/2016.
 */
public class ProductAdapter extends RecyclerView.Adapter {

    private ArrayList<Product> products;
    private ProductAdapterCallback callback;

    public ProductAdapter(ArrayList<Product> products, ProductAdapterCallback callback) {
        this.products = products;
        this.callback = callback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product, parent, false);

        return new ProductViewHolder(v, parent.getContext());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Product product = products.get(position);
        ProductViewHolder productViewHolder = (ProductViewHolder) holder;
        productViewHolder.render(product);
        ((ProductViewHolder) holder).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(callback != null){
                    callback.onProductClicked(product.getSku());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return products != null ? products.size() : 0;
    }

    public void setProducts(List<Product> products) {
        this.products.clear();
        this.products.addAll(products);
        notifyDataSetChanged();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "ProductViewHolder";

        @Bind(R.id.product_sku)
        TextView productSku;
        @Bind(R.id.product_count)
        TextView productCount;

        private Context context;
        private View view;

        public ProductViewHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
            this.context = context;
        }

        public void render(Product product) {
            productSku.setText(product.getSku().toUpperCase());
            productCount.setText(String.format(context.getString(R.string.transactions), product.getNumberOfTransactions().toString()));
        }
    }

    public interface ProductAdapterCallback {
        void onProductClicked(String sku);
    }
}
