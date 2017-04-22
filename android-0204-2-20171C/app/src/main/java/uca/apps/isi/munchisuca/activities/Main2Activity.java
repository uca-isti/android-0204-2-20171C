package uca.apps.isi.munchisuca.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IntDef;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import uca.apps.isi.munchisuca.R;
import uca.apps.isi.munchisuca.models.ProfileModel;

/**
 * Created by mcama on 20/4/2017.
 */

public class Main2Activity extends AppCompatActivity{
    private EditText email;
    private EditText password;
    private EditText name;
    private EditText profile_id;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
    }

    private void initViews() {
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.email);
        name = (EditText) findViewById(R.id.name);
        profile_id = (EditText) findViewById(R.id.profile_id);

        add = (Button) findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                finish();
            }
        });
    }

    private boolean validate(){
        boolean success= false;
        if(email.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Debe ingresar un email", Toast.LENGTH_LONG).show();
        } else if(password.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Debe ingresar un numero celular", Toast.LENGTH_LONG).show();
        }else if(name.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Debe ingresar un numero celular", Toast.LENGTH_LONG).show();
        }else if(profile_id.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Debe ingresar un numero celular", Toast.LENGTH_LONG).show();
        } else {
            success = true;
        }

        return success;
    }

    private void save(){
        if (validate()){
            Realm realm = Realm.getDefaultInstance();

            realm.beginTransaction();
            ProfileModel profileModel = realm.createObject(ProfileModel.class);
            profileModel.setEmail(email.getText().toString());
            profileModel.setPassword(password.getText().toString());
            profileModel.setName(name.getText().toString());
            profileModel.setProfile_id(Integer.parseInt(String.valueOf(profile_id.getText())));
            realm.commitTransaction();
        }
    }

}
