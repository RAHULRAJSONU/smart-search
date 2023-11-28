package io.github.rahulrajsonu.smartsearch.controller;

import io.github.rahulrajsonu.smartsearch.service.SmartSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/search")
public class SmartSearchController {

    private final SmartSearchService service;

    public SmartSearchController(SmartSearchService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> search(@RequestParam String searchTerm) throws IOException {
        return ResponseEntity.ok(service.filterStrings(searchTerm));
    }
}
