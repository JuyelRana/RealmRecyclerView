package dreamycoding.com.realmrecyclerview.config;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Juyel on 10/18/2017.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Realm Configuration
        RealmConfiguration configuration = new RealmConfiguration.Builder(this).schemaVersion(3).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(configuration);
    }
}
