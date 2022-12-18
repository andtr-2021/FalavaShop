package com.example.falavashop.Model;


import java.util.ArrayList;

public class ResultsSearch{

    public ArrayList<Results> getResults() {
        return results;
    }

    public void setResults(ArrayList<Results> results) {
        this.results = results;
    }

    boolean success;
    public ArrayList<Results> results;
    public String status;
}
