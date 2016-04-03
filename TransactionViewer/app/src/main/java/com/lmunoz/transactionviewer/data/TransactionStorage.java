package com.lmunoz.transactionviewer.data;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lmunoz.transactionviewer.TransactionViewerApplication;
import com.lmunoz.transactionviewer.model.Product;
import com.lmunoz.transactionviewer.model.Transaction;
import com.lmunoz.transactionviewer.util.Util;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Luis on 03/04/2016.
 */
public class TransactionStorage {

    private ArrayList<Transaction> transactions;
    private HashMap<Product, ArrayList<Transaction>> storage;

    public TransactionStorage(){
        String jsonTrans = Util.loadJSONFromAsset(TransactionViewerApplication.getInstance(), "transactions.json");
        if(jsonTrans != null){
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Transaction>>(){}.getType();
            transactions = gson.fromJson(jsonTrans, listType);
        } else {
            transactions = new ArrayList<>();
        }

        storage = new HashMap<>();

        //Calculate the products
        for(Transaction transaction : transactions){
            Product product = new Product(transaction.getSku());
            if(!storage.containsKey(product)){
                ArrayList<Transaction> transactions = new ArrayList<>();
                transactions.add(transaction);
                storage.put(product, transactions);
            } else {
                storage.get(product).add(transaction);
            }
        }
    }

    /**
     * Returns all the products with the count of transactions on each one
     * @return array with the products
     */
    public ArrayList<Product> getProductsWithCount(){
        ArrayList<Product> products = new ArrayList<>();
        for(Product product : storage.keySet()){
            product.setNumberOfTransactions(storage.get(product).size());
            products.add(product);
        }
        return products;
    }

    /**
     * Returns all the transactions by SKU and calculates the amount in GBP for each one
     * @param sku sku of the product
     * @return array with the transations
     */
    public ArrayList<Transaction> getTransactionsByProductInGBP(String sku){
        Product product = new Product(sku);
        ArrayList<Transaction> transactions = storage.get(product);
        for(Transaction transaction : transactions){
            if(transaction.getAmountGBP() == null){
                Float amount = TransactionViewerApplication.getCoinConverter().convert(transaction.getCurrency(), "GBP", transaction.getAmountF());
                transaction.setAmountGBP(amount);
            }
        }
        return transactions;
    }
}
