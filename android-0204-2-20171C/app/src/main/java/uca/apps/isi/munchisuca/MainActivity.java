package uca.apps.isi.munchisuca;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uca.apps.isi.munchisuca.Api.Api;
import uca.apps.isi.munchisuca.Api.ApiInterface;
import uca.apps.isi.munchisuca.fragments.CheckInFragment;
import uca.apps.isi.munchisuca.fragments.HomeFragment;
import uca.apps.isi.munchisuca.fragments.PromotionsFragment;
import uca.apps.isi.munchisuca.fragments.SettingsFragment;
import uca.apps.isi.munchisuca.models.ProfileModel;
import uca.apps.isi.munchisuca.adapters.ProfileAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final static String TAG ="MainActivity";
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.getBase())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface=retrofit.create(ApiInterface.class);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Call<List<ProfileModel>> call = Api.instance().getProfile();
                call.enqueue(new Callback<List<ProfileModel>>(){

                    @Override
                    public void onResponse(Call<List<ProfileModel>> call, Response<List<ProfileModel>> response) {
                        if (response != null) {
                            for (ProfileModel profileModel : response.body()) {
                                Log.i(TAG, profileModel.getEmail());
                                Log.i(TAG, profileModel.getPassword());
                                Log.i(TAG, profileModel.getName());
                                Log.i(TAG, String.valueOf(profileModel.getProfile_id()));
                            }
                        } else {
                            Log.i(TAG, "response es nulo");
                        }
                    }
                    @Override
                    public void onFailure(Call<List<ProfileModel>> call, Throwable t) {
                        t.printStackTrace();
                        textView = (TextView) findViewById(R.id.textView);
                        textView.setText(t.getMessage());
                    }
                });
            }
        });
        getData();
   }

   public void getData(){
       Realm realm = Realm.getDefaultInstance();
       final RealmResults<ProfileModel> profileModelRealmResults = realm.where(ProfileModel.class).findAll();

       for (ProfileModel profileModel : profileModelRealmResults) {
           Log.i(TAG, profileModel.getEmail());
           Log.i(TAG, profileModel.getPassword());
           Log.i(TAG, profileModel.getName());
           Log.i(TAG, String.valueOf(profileModel.getProfile_id()));

           updateItem(profileModel);

           //deleteItem(profileModel);
       }
   }

   private void updateItem(ProfileModel profileModel){
       Realm realm = Realm.getDefaultInstance();

       String actualizar = String.format(Locale.US, getString(R.string.concat_name), profileModel.getName(), " ACTUALIZADO");;

       realm.beginTransaction();
       profileModel.setName(actualizar);
       realm.commitTransaction();
   }

   private void deleteItem(ProfileModel profileModel){
       Realm realm = Realm.getDefaultInstance();

       realm.beginTransaction();
       profileModel.deleteFromRealm();
       realm.commitTransaction();
   }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;
        Class fragmentClass = null;


        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            fragmentClass = HomeFragment.class;
        } else if (id == R.id.nav_promotions) {
            fragmentClass = PromotionsFragment.class;
        } else if (id == R.id.nav_location) {
            fragmentClass = CheckInFragment.class;
        } else if (id == R.id.nav_configuration) {
            fragmentClass = SettingsFragment.class;
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        } catch (Exception e) {
            e.printStackTrace();
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
