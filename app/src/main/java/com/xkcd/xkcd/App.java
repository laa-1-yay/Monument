package com.xkcd.xkcd;


import android.app.Application;
import com.parse.Parse;

public class App extends Application {

    @Override public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "19wAWLIcYLvXaBbCRwgldyZGodMat8JfdufoFNql", "EEGzUhQkrU9alfGjui8ElFaq8qdG2oL8WXZhYQkd"); // Your Application ID and Client Key are defined elsewhere
    }
}