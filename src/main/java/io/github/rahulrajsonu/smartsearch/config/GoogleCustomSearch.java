package io.github.rahulrajsonu.smartsearch.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.rahulrajsonu.smartsearch.domain.SearchResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleCustomSearch {

    private static final String API_KEY = "xxxxx";
    private static final String CX = "xxxxx";
    private static final String SEARCH_URL = "https://www.googleapis.com/customsearch/v1";

    public SearchResult search(String query) {
        String url = SEARCH_URL + "?key=" + API_KEY + "&cx=" + CX + "&q=" + query;

        RestTemplate restTemplate = new RestTemplate();
        SearchResult response = restTemplate.getForObject(url, SearchResult.class);

        // Process the JSON response here
        try {
            System.out.println(new ObjectMapper().writeValueAsString(response));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response;
    }
}
