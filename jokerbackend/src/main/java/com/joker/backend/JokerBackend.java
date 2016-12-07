/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.joker.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.joker.Joke;
import com.joker.Joker;

@Api(
        name = "jokerAPI",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.joker.com",
                ownerName = "backend.joker.com",
                packagePath = ""
        )
)
public class JokerBackend {

    @ApiMethod(name = "getJoke")
    public Joke getJoke() {
        return Joker.getJoke();
    }

}
