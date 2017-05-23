package io.bunnyblue.android.pmsprotect;

import android.app.Application;

import io.bunnyblue.android.pmshooker.ServiceManagerWraper;

/**
 * Created by bunnyblue on 5/23/17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        ServiceManagerWraper.hookPMS(this, "308201fb30820164a003020102020450", 1338303158);
        super.onCreate();
    }
}
