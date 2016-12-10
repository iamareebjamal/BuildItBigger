package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.joker.backend.jokerAPI.JokerAPI;
import com.joker.viewer.JokeActivity;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private AdPresenter adPresenter;
    private boolean isJokeLoaded = false;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        adPresenter = (AdPresenter) getSupportFragmentManager().findFragmentById(R.id.fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        progressBar.setVisibility(View.VISIBLE);
        adPresenter.showAd();
        new JokeLoader().execute(this);
    }

    public boolean isJokeLoaded() {
        return isJokeLoaded;
    }

    public class JokeLoader extends AsyncTask<Context, Void, String> {
        private JokerAPI jokerAPI = null;
        private Context context;

        @Override
        protected String doInBackground(Context... params) {
            context = params[0];

            if (jokerAPI == null) {
                JokerAPI.Builder builder = new JokerAPI.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        .setRootUrl("https://joker-backend.appspot.com/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });

                jokerAPI = builder.build();
            }

            try {
                return jokerAPI.getJoke().execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            isJokeLoaded = true;
            progressBar.setVisibility(View.INVISIBLE);
            Intent jokeIntent = new Intent(context, JokeActivity.class);
            jokeIntent.putExtra(JokeActivity.JOKE, result);
            startActivity(jokeIntent);
        }
    }

}
