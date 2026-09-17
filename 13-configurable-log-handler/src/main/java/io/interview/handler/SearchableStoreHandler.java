package io.interview.handler;

import io.interview.HandlerResult;

import java.util.*;

public class SearchableStoreHandler extends StoreHandler{

    private Map<String, List<String>> invertedIndex = new HashMap<>();

    @Override
    public HandlerResult handle(String message) {
        messages.add(message);

        // Build an index
        String[] words = message.toLowerCase().split("\\s+");

        for(String word : words) {
            invertedIndex.computeIfAbsent(word, k -> new ArrayList<>())
                    .add(message);
        }

        return new HandlerResult(message, false);
    }

    private List<String> searchLinear(String keyword) {
        keyword = keyword.toLowerCase();

        List<String> result = new ArrayList<>();

        for (String message : messages) {
            if (message.toLowerCase().contains(keyword)) {
                result.add(message);
            }
        }

        return result;
    }

    private List<String> searchInvertedIndex(String keyword) {
        return invertedIndex.getOrDefault(keyword, Collections.emptyList());
    }

    public List<String> search(String keyword){
        // 1. Linear search
        // return searchLinear(keyword);

        // 2. Optimized approach
        return searchInvertedIndex(keyword);
    }
}
