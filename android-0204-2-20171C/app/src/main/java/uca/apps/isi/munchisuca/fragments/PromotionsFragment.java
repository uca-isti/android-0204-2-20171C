package uca.apps.isi.munchisuca.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uca.apps.isi.munchisuca.Api.Api;
import uca.apps.isi.munchisuca.R;
import uca.apps.isi.munchisuca.adapters.PromotionAdapter;
import uca.apps.isi.munchisuca.models.PromotionModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class PromotionsFragment extends Fragment {
    private static final String TAG = "PromotionFragment";
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    LinearLayoutManager mLayoutManager;

    public PromotionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        sync();
        View view = inflater.inflate(R.layout.fragment_promotions, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_promotion);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(new PromotionAdapter(getData()));
        // Inflate the layout for this fragment
        return view;
    }

    private RealmResults<PromotionModel> getData(){
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<PromotionModel> promotionModelRealmResults = realm.where(PromotionModel.class).findAll();

        return promotionModelRealmResults;
    }

    private void sync(){
        Call<List<PromotionModel>> call = Api.instance().getPromotions();
        call.enqueue(new Callback<List<PromotionModel>>() {
            @Override
            public void onResponse(Call<List<PromotionModel>> call, Response<List<PromotionModel>> response) {
                if(response != null && response.body() != null){
                    for (PromotionModel promotionModel : response.body()){
                        add(promotionModel);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PromotionModel>> call, Throwable t) {

            }
        });
    }

    private void add(PromotionModel promotionModelitem) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        PromotionModel promotionModel = realm.createObject(PromotionModel.class);
        promotionModel.setName(promotionModelitem.getName());
        promotionModel.setDescription(promotionModelitem.getDescription());
        realm.commitTransaction();
    }

}
