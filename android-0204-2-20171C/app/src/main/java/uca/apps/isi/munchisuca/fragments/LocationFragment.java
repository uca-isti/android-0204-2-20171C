package uca.apps.isi.munchisuca.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uca.apps.isi.munchisuca.Api.Api;
import uca.apps.isi.munchisuca.MainActivity;
import uca.apps.isi.munchisuca.R;
import uca.apps.isi.munchisuca.activites.MapsActivity;
import uca.apps.isi.munchisuca.adapters.PlaceAdapter;
import uca.apps.isi.munchisuca.models.PlaceModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends Fragment {
    private static final String TAG = "LocationFragment";
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    LinearLayoutManager mLayoutManager;


    public LocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sync();
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_location);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(new PlaceAdapter(getData()));

        // Inflate the layout for this fragment
        return view;
    }

    private RealmResults<PlaceModel> getData(){
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<PlaceModel> placeModelRealmResults = realm.where(PlaceModel.class).findAll();

        return placeModelRealmResults;
    }

    private void sync(){
        Call<List<PlaceModel>> call = Api.instance().getPlaces();
        call.enqueue(new Callback<List<PlaceModel>>() {
            @Override
            public void onResponse(Call<List<PlaceModel>> call, Response<List<PlaceModel>> response) {
                if(response != null && response.body() != null){
                    for (PlaceModel placeModel : response.body()){
                        add(placeModel);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PlaceModel>> call, Throwable t) {

            }
        });
    }

    private void add(PlaceModel placeModelitem) {
            Realm realm = Realm.getDefaultInstance();

            realm.beginTransaction();
            PlaceModel placeModel = realm.createObject(PlaceModel.class);
            placeModel.setName(placeModelitem.getName());
            placeModel.setDescription(placeModelitem.getDescription());
            placeModel.setCover(placeModelitem.getCover());
            realm.commitTransaction();
    }

}
