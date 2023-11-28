package io.github.rahulrajsonu.smartsearch.domain;

import lombok.Data;

import java.util.List;

@Data
public class SearchResult {
    private String kind;
    private Url url;
    private Queries queries;
    private Context context;
    private SearchInformation searchInformation;
    private List<Item> items;

    @Data
    public static class Url {
        private String type;
        private String template;
    }

    @Data
    public static class Queries {
        private List<Request> request;
    }

    @Data
    public static class Request {
        private String title;
        private String totalResults;
        private String searchTerms;
        private int count;
        private int startIndex;
        private String inputEncoding;
        private String outputEncoding;
        private String safe;
        private String cx;
    }

    @Data
    public static class Context {
        private String title;
    }

    @Data
    public static class SearchInformation {
        private double searchTime;
        private String formattedSearchTime;
        private String totalResults;
        private String formattedTotalResults;
    }

    @Data
    public static class Item {
        private String kind;
        private String title;
        private String htmlTitle;
        private String link;
        private String displayLink;
        private String snippet;
        private String htmlSnippet;
        private String cacheId;
        private String formattedUrl;
        private String htmlFormattedUrl;
        private PageMap pagemap;
    }

    @Data
    public static class PageMap {
        private List<CseThumbnail> cseThumbnail;
        private List<Metatag> metatags;
        private List<CseImage> cseImage;
    }

    @Data
    public static class CseThumbnail {
        private String src;
        private String width;
        private String height;
    }

    @Data
    public static class Metatag {
        private String viewport;
    }

    @Data
    public static class CseImage {
        private String src;
    }
}
