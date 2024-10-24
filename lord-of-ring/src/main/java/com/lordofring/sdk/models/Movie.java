package com.lordofring.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie {

    @JsonProperty("_id")
    private String id;

    private String name;

    @JsonProperty("runtimeInMinutes")
    private int runtimeInMinutes;

    @JsonProperty("budgetInMillions")
    private double budgetInMillions;

    @JsonProperty("boxOfficeRevenueInMillions")
    private double boxOfficeRevenueInMillions;

    @JsonProperty("academyAwardNominations")
    private int academyAwardNominations;

    @JsonProperty("academyAwardWins")
    private int academyAwardWins;

    @JsonProperty("rottenTomatoesScore")
    private double rottenTomatoesScore;

    public Movie(String id, String name, int runtimeInMinutes, double budgetInMillions, double boxOfficeRevenueInMillions, int academyAwardNominations, int academyAwardWins, double rottenTomatoesScore) {
        this.id = id;
        this.name = name;
        this.runtimeInMinutes = runtimeInMinutes;
        this.budgetInMillions = budgetInMillions;
        this.boxOfficeRevenueInMillions = boxOfficeRevenueInMillions;
        this.academyAwardNominations = academyAwardNominations;
        this.academyAwardWins = academyAwardWins;
        this.rottenTomatoesScore = rottenTomatoesScore;
    }

    public Movie(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRuntimeInMinutes() {
        return runtimeInMinutes;
    }

    public void setRuntimeInMinutes(int runtimeInMinutes) {
        this.runtimeInMinutes = runtimeInMinutes;
    }

    public double getBudgetInMillions() {
        return budgetInMillions;
    }

    public void setBudgetInMillions(double budgetInMillions) {
        this.budgetInMillions = budgetInMillions;
    }

    public double getBoxOfficeRevenueInMillions() {
        return boxOfficeRevenueInMillions;
    }

    public void setBoxOfficeRevenueInMillions(double boxOfficeRevenueInMillions) {
        this.boxOfficeRevenueInMillions = boxOfficeRevenueInMillions;
    }

    public int getAcademyAwardNominations() {
        return academyAwardNominations;
    }

    public void setAcademyAwardNominations(int academyAwardNominations) {
        this.academyAwardNominations = academyAwardNominations;
    }

    public int getAcademyAwardWins() {
        return academyAwardWins;
    }

    public void setAcademyAwardWins(int academyAwardWins) {
        this.academyAwardWins = academyAwardWins;
    }

    public double getRottenTomatoesScore() {
        return rottenTomatoesScore;
    }

    public void setRottenTomatoesScore(double rottenTomatoesScore) {
        this.rottenTomatoesScore = rottenTomatoesScore;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", runtimeInMinutes=" + runtimeInMinutes +
                ", budgetInMillions=" + budgetInMillions +
                ", boxOfficeRevenueInMillions=" + boxOfficeRevenueInMillions +
                ", academyAwardNominations=" + academyAwardNominations +
                ", academyAwardWins=" + academyAwardWins +
                ", rottenTomatoesScore=" + rottenTomatoesScore +
                '}';
    }
}
