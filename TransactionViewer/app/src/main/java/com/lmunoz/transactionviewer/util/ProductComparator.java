package com.lmunoz.transactionviewer.util;

import com.lmunoz.transactionviewer.model.Product;

import java.util.Comparator;

/**
 * Created by Luis on 03/04/2016.
 */
public class ProductComparator implements Comparator<Product> {
    @Override
    public int compare(Product lhs, Product rhs) {
        if(lhs != null && rhs != null){
            return lhs.getSku().compareTo(rhs.getSku());
        }
        return 0;
    }
}
