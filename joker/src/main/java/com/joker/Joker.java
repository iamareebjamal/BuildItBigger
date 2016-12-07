package com.joker;

import java.util.List;

public class Joker {

    private static List<Joke> jokes;

    static {
        jokes = Joke.addJokes(
                "Two bytes meet.  The first byte asks, “Are you ill?”  \n" +
                "The second byte replies, “No, just feeling a bit off.”",

                "Eight bytes walk into a bar.  The bartender asks, “Can I get you anything?”\n" +
                "\n" +
                "“Yeah,” reply the bytes.  “Make us a double.”",

                "Why do programmers always mix up Halloween and Christmas? \n" +
                "Because Oct 31 equals Dec 25.",

                "A programmer walks to the butcher shop and buys a kilo of meat.  " +
                "An hour later he comes back upset that the butcher shortchanged him by 24 grams.",

                "“Knock, knock.”\n" +
                "“Who’s there?”\n" +
                "very long pause….\n" +
                "“Java.”",

                "Programming is like sex:\n" +
                "One mistake and you have to support it for the rest of your life.",

                "A man is smoking a cigarette and blowing smoke rings into the air.  " +
                "His girlfriend becomes irritated with the smoke and says, " +
                "“Can’t you see the warning on the cigarette pack?  Smoking is hazardous to your health!” \n" +
                "\n" +
                "To which the man replies, “I am a programmer.  We don’t worry about warnings; we only worry about errors.”"
        );
    }

    public static Joke getJoke() {
        return jokes.get((int) (Math.random()*jokes.size()));
    }
}
