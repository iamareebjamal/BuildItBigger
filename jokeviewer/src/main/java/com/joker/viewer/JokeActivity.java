package com.joker.viewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE = "jokeviewer.JOKE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        String joke = getIntent().getStringExtra(JOKE);
        if (joke != null) {
            TextView jokeText = (TextView) findViewById(R.id.jokeView);
            jokeText.setText(joke);
        }
    }
}
