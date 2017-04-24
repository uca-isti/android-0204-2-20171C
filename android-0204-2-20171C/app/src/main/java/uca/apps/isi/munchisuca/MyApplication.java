package uca.apps.isi.munchisuca;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tumblr.remember.Remember;

import io.realm.Realm;

/**
 * Created by mcama on 23/4/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Remember.init(getApplicationContext(), getApplicationContext().getPackageName());
        Fresco.initialize(this);
        Realm.init(this);

    }
}