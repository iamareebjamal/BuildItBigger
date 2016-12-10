package com.joker.viewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE = "jokeviewer.JOKE";
    public static final String EMPTY_STRING = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        String joke = getIntent().getStringExtra(JOKE);
        TextView jokeText = (TextView) findViewById(R.id.jokeView);
        if (joke != null) {
            jokeText.setText(joke);
        } else {
            jokeText.setText(EMPTY_STRING);
        }
    }
}
