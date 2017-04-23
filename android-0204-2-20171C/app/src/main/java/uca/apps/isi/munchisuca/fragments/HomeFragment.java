package uca.apps.isi.munchisuca.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uca.apps.isi.munchisuca.Api.Api;
import uca.apps.isi.munchisuca.R;
import uca.apps.isi.munchisuca.adapters.ProfileAdapter;
import uca.apps.isi.munchisuca.models.ProfileModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    LinearLayoutManager mLayoutManager;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recicler_munchies);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        getData();
            // Inflate the layout for this fragment
        return view;
    }

    private void getData() {
        Call<List<ProfileModel>> call = Api.instance().getProfile();
        call.enqueue(new Callback<List<ProfileModel>>() {
            @Override
            public void onResponse(Call<List<ProfileModel>> call, Response<List<ProfileModel>> response) {
                //valida si la respusta fue nula porque no habia tweets
                if (response != null && response.body() != null) {
                    mAdapter = new ProfileAdapter(response.body());
                    mRecyclerView.setAdapter(mAdapter);
                    //for (ProfileModel profileModel : response.body()) {
                    //Log.d(TAG, profileModel.getFirstName());
                    //Log.d(TAG, profileModel.getLastName());
                    //Log.d(TAG, profileModel.getEmail());
                    //Log.d(TAG, profileModel.getPassword());
                    //Log.d(TAG, String.valueOf(profileModel.getId()));
                    //Log.d(TAG, String.valueOf(profileModel.getProfile_id()));
                    //Log.d(TAG, String.valueOf(profileModel.getProfilePetsId()));
                    //}
                } else {
                    Log.d(TAG, "La respuesta es incorrecta");
                }
            }

                @Override
                public void onFailure(Call<List<ProfileModel>> call, Throwable throwable) {
                    Log.d(TAG, throwable.getMessage());
                }
            });
        }
    }