package com.joker;

import java.util.ArrayList;
import java.util.List;

public class Joke {

    private String joke;

    public Joke(String joke) {
        setData(joke);
    }

    public String getData() {
        return joke;
    }

    public void setData(String joke) {
        this.joke = joke;
    }

    public static List<Joke> addJokes(String... jokes) {
        List<Joke> jokesList = new ArrayList<>();

        for(String joke : jokes) {
            jokesList.add(new Joke(joke));
        }

        return jokesList;
    }
}