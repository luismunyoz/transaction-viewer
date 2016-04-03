package com.lmunoz.transactionviewer.model;

/**
 * Created by Luis on 03/04/2016.
 */
public class Rate {
    private String from;
    private String to;
    private String rate;

    private Float rateF;

    public Rate(String from, String to, String rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Parses the rate to Float value
     * @return the rate in float or 0 if failed to parse
     */
    public Float getRate() {
        if(rateF == null){
            try {
                rateF = Float.parseFloat(rate);
            } catch (NumberFormatException e){
                return 0.0f;
            }
        }
        return rateF;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
