package com.lmunoz.transactionviewer.model;

import com.lmunoz.transactionviewer.util.Util;

import java.math.BigDecimal;

/**
 * Created by Luis on 03/04/2016.
 */
public class Transaction {
    private String amount;
    private String sku;
    private String currency;

    private Float amountF;
    private Float amountGBP;

    public Transaction(String amount, String sku, String currency) {
        this.amount = amount;
        this.sku = sku;
        this.currency = currency;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getAmount() {
        return amount;
    }

    /**
     * Parses the rate to Float value
    * @return the rate in float or 0 if failed to parse
    */
    public Float getAmountF() {
        if(amountF == null){
            try {
                amountF = Float.parseFloat(amount);
            } catch (NumberFormatException e){
                return 0.0f;
            }
        }
        return amountF;
    }

    public String getCurrency() {
        return currency;
    }

    public Float getAmountGBP() {
        if(amountGBP != null) {
            //We convert it to two decimal float. It is not needed, only to match the results with the example in the PDF given
            return Util.toTwoDecimalPlaces(amountGBP);
        }
        return amountGBP;
    }

    public void setAmountGBP(Float amountGBP) {
        this.amountGBP = amountGBP;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
