package uca.apps.isi.munchisuca.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Type;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uca.apps.isi.munchisuca.Api.Api;
import uca.apps.isi.munchisuca.R;
import uca.apps.isi.munchisuca.adapters.TypeAdapter;
import uca.apps.isi.munchisuca.models.TypeModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class TypeFragment extends Fragment {
    private static final String TAG = "TypeFragment";
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    LinearLayoutManager mLayoutManager;

    public TypeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        sync();
        View view = inflater.inflate(R.layout.fragment_type, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_type);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(new TypeAdapter(getData()));
        // Inflate the layout for this fragment
        return view;
    }

    private RealmResults<TypeModel> getData(){
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<TypeModel> typeModelRealmResults = realm.where(TypeModel.class).findAll();

        return typeModelRealmResults;
    }

    private void sync(){
        Call<List<TypeModel>> call = Api.instance().getType();
        call.enqueue(new Callback<List<TypeModel>>() {
            @Override
            public void onResponse(Call<List<TypeModel>> call, Response<List<TypeModel>> response) {
                if(response != null && response.body() != null){
                    for (TypeModel typeModel : response.body()){
                        add(typeModel);
                    }
                }
                else {
                    Log.i("DEBUG xD", "LOL");
                }
            }

            @Override
            public void onFailure(Call<List<TypeModel>> call, Throwable t) {
                Log.i("DEBUG", t.getMessage());
            }
        });
    }

    private void add(TypeModel typeModelitem) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        TypeModel typeModel = realm.createObject(TypeModel.class);
        Log.i("DEBUG", typeModelitem.getName());
        typeModel.setDescription(typeModelitem.getDescription());
        typeModel.setId(typeModelitem.getId());
        realm.commitTransaction();
    }

}
