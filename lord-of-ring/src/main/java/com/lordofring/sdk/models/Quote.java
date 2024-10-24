package com.lordofring.sdk.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    @JsonProperty("_id")
    private String id;

    @JsonProperty("dialog")
    private String dialog;

    @JsonProperty("movie")
    private String movie;

    @JsonProperty("character")
    private String character;

    public Quote(String id, String dialog, String movie, String character) {
        this.id = id;
        this.dialog = dialog;
        this.movie = movie;
        this.character = character;
    }

    public Quote() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDialog() {
        return dialog;
    }

    public void setDialog(String dialog) {
        this.dialog = dialog;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", dialog='" + dialog + '\'' +
                ", movie=" + movie +
                ", character=" + character +
                '}';
    }
}
