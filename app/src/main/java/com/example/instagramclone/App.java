package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("hRiUp8wzsV4lE7IG7ZBFFHCCOORpzNoxFeEbol3U")
                // if defined
                .clientKey("R1FjaNd6CI4YFmaxxiqxTsaJenfjvo2bnggtA6IW")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
