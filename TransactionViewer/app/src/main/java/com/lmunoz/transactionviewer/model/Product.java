package com.lmunoz.transactionviewer.model;

import java.util.ArrayList;

/**
 * Created by Luis on 03/04/2016.
 */
public class Product {
    private String sku;

    private Integer numberOfTransactions;

    public Product(String sku) {
        this.sku = sku;
        numberOfTransactions = 0;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getNumberOfTransactions() {
        if(numberOfTransactions == 0){
            numberOfTransactions = 0;
        }
        return numberOfTransactions;
    }

    public void setNumberOfTransactions(Integer numberOfTransactions) {
        this.numberOfTransactions = numberOfTransactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return sku != null ? sku.equals(product.sku) : product.sku == null;

    }

    @Override
    public int hashCode() {
        return sku != null ? sku.hashCode() : 0;
    }
}
