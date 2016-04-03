package com.lmunoz.transactionviewer.model;

/**
 * Created by Luis on 03/04/2016.
 */
public class Vertex {
    String coin;

    public Vertex(String coin) {
        this.coin = coin;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        return coin != null ? coin.equals(vertex.coin) : vertex.coin == null;

    }

    @Override
    public int hashCode() {
        return coin != null ? coin.hashCode() : 0;
    }
}
