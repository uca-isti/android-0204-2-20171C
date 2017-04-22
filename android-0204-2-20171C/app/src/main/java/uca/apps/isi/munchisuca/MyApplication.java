package uca.apps.isi.munchisuca;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by mcama on 20/4/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
    }
}
