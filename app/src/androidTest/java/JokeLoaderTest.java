import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.joker.viewer.JokeActivity;
import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.Callable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.util.concurrent.TimeUnit.SECONDS;
import static junit.framework.TestCase.assertEquals;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(AndroidJUnit4.class)
public class JokeLoaderTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void testAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.udacity.gradle.builditbigger", appContext.getPackageName());
    }

    private Callable<Boolean> jokeLoaded() {
        return new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return mActivityRule.getActivity().isJokeLoaded();
            }
        };
    }

    @Test
    public void testIntentBuilding() throws Exception {
        onView(withId(R.id.loadJoke)).perform(click());
        await().atMost(5, SECONDS).until(jokeLoaded(), equalTo(true));

        intended(hasExtra(equalTo(JokeActivity.JOKE), notNullValue()));
    }

    @Test
    public void testJokeActivityLoading() throws Exception {
        onView(withId(R.id.loadJoke)).perform(click());
        await().atMost(5, SECONDS).until(jokeLoaded(), equalTo(true));

        onView(withId(R.id.jokeView)).check(matches(withText(not(JokeActivity.EMPTY_STRING))));
    }

}