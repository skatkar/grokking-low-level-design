package io.currency;

import java.util.*;

public class CurrencyConverter {

    // Build the adjacency list
    private static Map<String, List<Edge>> buildGraph(
            List<ExchangeRate> rates) {
        Map<String, List<Edge>> graph = new HashMap<>();
        for(ExchangeRate rate : rates) {
            graph.computeIfAbsent(rate.getSource(), k-> new ArrayList<>())
                    .add(new Edge(rate.getTarget(), rate.getRate()));
            graph.computeIfAbsent(rate.getTarget(), k -> new ArrayList<>())
                    .add(new Edge(rate.getSource(), 1 / rate.getRate()));
        }

        return graph;
    }

    public List<Double> convertCurrency(
            List<ExchangeRate> rates,
            List<ConversionRequest> queries) {
        Map<String, List<Edge>> graph = buildGraph(rates);
        List<Double> results = new ArrayList<>();
        for(ConversionRequest query : queries){
            double result = convertUsingBFS(graph,
                    query.getSource(),
                    query.getTarget(),
                    query.getAmount());
            results.add(result);
        }
        return results;
    }

    // Using BFS approach
    private double convertUsingBFS(Map<String, List<Edge>> graph,
                                   String source,
                                   String target,
                                   double amount) {
        if(!graph.containsKey(source) && !graph.containsKey(target)){
            return -1.0;
        }

        // Consider each currency as a node of a graph
        Queue<String> queue = new LinkedList<>();
        Map<String, Double> visited = new HashMap<>();
        queue.add(source);
        visited.put(source, amount);

        while(!queue.isEmpty()) {
            String currSource = queue.poll();
            double currValue = visited.get(currSource);

            if(currSource != null && currSource.equals(target)) {
                return visited.get(currSource);
            }
            for(Edge edge : graph.get(currSource)) {
                if(visited.containsKey(edge.getCurrency())){
                    continue;
                }
                double convertedValue = currValue * edge.getRate();

                visited.put(edge.getCurrency(), convertedValue);
                queue.add(edge.getCurrency());
            }
        }
        return -1.0;
    }

    private double convertUsingDFS(Map<String, List<Edge>> graph,
                                   String source,
                                   String target,
                                   double amount) {

        if(!graph.containsKey(source) && !graph.containsKey(target)) {
            return -1.0;
        }
        Set<String> visited = new HashSet<>();
        return dfs(graph, source, target, amount, visited);
    }

    private double dfs(Map<String, List<Edge>> graph, String current, String target, double amount, Set<String> visited) {
        if(current.equals(target)) {
            return amount;
        }

        visited.add(current);
        double maxAmount = -1.0;
        for(Edge edge : graph.get(current)) {
            if(visited.contains(edge.getCurrency())){
                continue;
            }

            double convertedAmount = amount * edge.getRate();
            double result = dfs(graph, edge.getCurrency(), target, convertedAmount, visited);

            maxAmount = Math.max(maxAmount, result);
        }

        visited.remove(current);
        return maxAmount;
    }
}
