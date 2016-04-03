package com.lmunoz.transactionviewer.model;

/**
 * Created by Luis on 03/04/2016.
 */
public class Edge {
    private Vertex origin;
    private Vertex destination;
    private Float rate;

    public Edge(Vertex origin, Vertex destination, Float cost) {
        this.origin = origin;
        this.destination = destination;
        this.rate = cost;
    }

    public Vertex getOrigin() {
        return origin;
    }

    public void setOrigin(Vertex origin) {
        this.origin = origin;
    }

    public Vertex getDestination() {
        return destination;
    }

    public void setDestination(Vertex destination) {
        this.destination = destination;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (origin != null ? !origin.equals(edge.origin) : edge.origin != null) return false;
        return destination != null ? destination.equals(edge.destination) : edge.destination == null;

    }

    @Override
    public int hashCode() {
        int result = origin != null ? origin.hashCode() : 0;
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        return result;
    }
}
