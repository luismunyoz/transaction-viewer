package com.lmunoz.transactionviewer;

import android.app.Application;

import com.lmunoz.transactionviewer.data.CoinConverter;
import com.lmunoz.transactionviewer.data.TransactionStorage;
import com.lmunoz.transactionviewer.model.Product;
import com.lmunoz.transactionviewer.model.Transaction;

import java.util.ArrayList;

/**
 * Created by Luis on 03/04/2016.
 */
public class TransactionViewerApplication extends Application {
    private static TransactionViewerApplication instance;
    private static CoinConverter coinConverter;
    private static TransactionStorage transactionStorage;

    @Override
    public void onCreate() {
        super.onCreate();

        if(instance == null){
            instance = this;
        }
    }

    public static CoinConverter getCoinConverter() {
        if(coinConverter == null){
            coinConverter = new CoinConverter();
        }
        return coinConverter;
    }

    public static TransactionStorage getTransactionStorage() {
        if(transactionStorage == null){
            transactionStorage = new TransactionStorage();
        }
        return transactionStorage;
    }

    public static TransactionViewerApplication getInstance(){
        return instance;
    }


}
