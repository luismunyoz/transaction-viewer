package com.lmunoz.transactionviewer.data;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lmunoz.transactionviewer.TransactionViewerApplication;
import com.lmunoz.transactionviewer.model.Edge;
import com.lmunoz.transactionviewer.model.Rate;
import com.lmunoz.transactionviewer.model.Vertex;
import com.lmunoz.transactionviewer.util.Util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Luis on 03/04/2016.
 */
public class CoinConverter {
    private HashMap<Vertex, ArrayList<Edge>> graph;
    private ArrayList<Rate> rates;

    private ArrayList<Vertex> visitedVertices;
    private ArrayList<Edge> newEdges;

    public CoinConverter() {
        graph = new HashMap<>();

        String jsonRates = Util.loadJSONFromAsset(TransactionViewerApplication.getInstance(), "rates.json");
        if(jsonRates != null){
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Rate>>(){}.getType();
            rates = gson.fromJson(jsonRates, listType);
        } else {
            rates = new ArrayList<>();
        }

        for(Rate rate : rates){
            if(rate.getRate() != null && !rate.getRate().equals(0f)) {
                Vertex origin = new Vertex(rate.getFrom());
                Vertex destination = new Vertex(rate.getTo());
                Edge originToDestination = new Edge(origin, destination, rate.getRate());
                Edge destinationToOrigin = new Edge(destination, origin, 1/rate.getRate());

                if(!graph.containsKey(origin)){
                    graph.put(origin, new ArrayList<Edge>());
                }
                if(!graph.containsKey(destination)){
                    graph.put(destination, new ArrayList<Edge>());
                }

                graph.get(origin).add(originToDestination);
                graph.get(destination).add(destinationToOrigin);
            }
        }
    }

    /**
     * Recursive funcion that iterates over the graph recursively and finds the convertion
     * @param from
     * @param originVertex
     * @param targetCoin
     * @param initialQuantity
     * @param accumulatedRate
     * @return
     */
    private Float calculate(Vertex from, Vertex originVertex, String targetCoin, Float initialQuantity, Float accumulatedRate){
        visitedVertices.add(from);

        //If it is the coin we're looking for, return it
        if(from.getCoin().equals(targetCoin)){
            return accumulatedRate*initialQuantity;
        }

        ArrayList<Edge> edges = graph.get(from);

        //First we search for direct children, in case we've updated the graph
        for(Edge edge : edges){
            if(edge.getDestination().getCoin().equals(targetCoin)){
                return calculate(edge.getDestination(), originVertex, targetCoin, initialQuantity, accumulatedRate * edge.getRate());
            }
        }

        //If it's not a direct child then we start to explore on depth
        for(Edge edge : edges){
            //Avoid visit a currency if it has already been visited
            if(!visitedVertices.contains(edge.getDestination())){
                Float newRate = accumulatedRate * edge.getRate();
                Log.e("INSPECTING", edge.getDestination().getCoin() + " with rate accumulation " + newRate);

                Float quantity = calculate(edge.getDestination(), originVertex, targetCoin, initialQuantity, newRate);

                //Create new edges for the graph
                Edge originToDestination = new Edge(originVertex, from, newRate);
                Edge destinationToOrigin = new Edge(from, originVertex, 1/newRate);

                if(!graph.get(originVertex).contains(originToDestination)){
                    newEdges.add(originToDestination);
                }
                if(!graph.get(from).contains(destinationToOrigin)){
                    newEdges.add(destinationToOrigin);
                }
                if(quantity != 0.0f){
                    return quantity;
                }
            }
        }
        return 0.0f;
    }

    /**
     * Converts a quantity from 'from' currency to 'to' currency
     * @param from current currency
     * @param to target currency
     * @param quantity amount
     * @return
     */
    public Float convert(String from, String to, Float quantity){
        visitedVertices = new ArrayList<>();
        newEdges = new ArrayList<>();

        Vertex fromVertex = new Vertex(from);
        Log.i("CONVERT", quantity.toString() + " " + from + " to " + to);
        Float converted = calculate(fromVertex, fromVertex, to, quantity, 1.0f);
        Log.i("FOUND", quantity.toString() + " " + from + " are " + converted.toString() + " " + to);

        //Update edges in the graph with new rates
        for(Edge edge : newEdges){
            if(!graph.get(edge.getOrigin()).contains(edge)){
                Log.i("UPDATING GRAPH", "New Edge " + edge.getOrigin().getCoin() + " to " + edge.getDestination().getCoin());
                graph.get(edge.getOrigin()).add(edge);
            }
        }

        return converted;
    }
}
