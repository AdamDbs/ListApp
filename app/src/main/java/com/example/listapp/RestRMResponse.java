package com.example.listapp;

import java.util.List;

public class RestRMResponse {
    private Integer count;
    private String next;
    private List<RickandMorty> results;

    public Integer getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public List<RickandMorty> getResults() {
        return results;
    }
}
