package io.github.rahulrajsonu.smartsearch.service;

import io.github.rahulrajsonu.smartsearch.config.GoogleCustomSearch;
import io.github.rahulrajsonu.smartsearch.domain.SearchResult;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SmartSearchService {

    private final GoogleCustomSearch googleCustomSearch;
    private final OpenNLPService openNLPService;

    public SmartSearchService(GoogleCustomSearch googleCustomSearch, OpenNLPService openNLPService) {
        this.googleCustomSearch = googleCustomSearch;
        this.openNLPService = openNLPService;
    }

    public String filterStrings(String query) throws IOException {
        List<String> queryTokens = openNLPService.tokenize(query.toLowerCase());
        Map<String, List<SearchResult.Item>> searchMap = googleCustomSearch.search(query).getItems().stream()
                .collect(Collectors.groupingBy(SearchResult.Item::getSnippet));
        List<String> contextualMatchs = searchMap.keySet().stream()
                .map(s -> new MatchResult(s, calculateContextualMatch(s.toLowerCase(), queryTokens)))
                .sorted(Comparator.reverseOrder())
                .map(MatchResult::getString)
                .collect(Collectors.toList());
        return searchMap.get(contextualMatchs.get(0)).get(0).getLink();
    }

    private double calculateContextualMatch(String string, List<String> queryTokens) {
        List<String> stringTokens = openNLPService.tokenize(string);
        long commonTokens = stringTokens.stream().filter(queryTokens::contains).count();
        return (double) commonTokens / queryTokens.size();
    }

    private static class MatchResult implements Comparable<MatchResult> {
        private final String string;
        private final double matchScore;

        public MatchResult(String string, double matchScore) {
            this.string = string;
            this.matchScore = matchScore;
        }

        public String getString() {
            return string;
        }

        @Override
        public int compareTo(MatchResult other) {
            return Double.compare(other.matchScore, this.matchScore);
        }
    }
}
