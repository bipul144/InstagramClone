package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("CScNySlWjvkZPJi1jHXkKEexypcFgPA75bLOfKXV")
                // if defined
                .clientKey("ng363ETiXG4II7HvIAUK5Guj8OIOwK0F0nlW08B5")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
