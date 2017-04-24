package uca.apps.isi.munchisuca.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import io.realm.RealmResults;
import uca.apps.isi.munchisuca.R;
import uca.apps.isi.munchisuca.models.PlaceModel;
import uca.apps.isi.munchisuca.models.ProfileModel;

/**
 * Created by mcama on 19/4/2017.
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder>{

    public RealmResults<PlaceModel> placeModelRealmResults;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView description;
        public SimpleDraweeView cover;


        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            description = (TextView) view.findViewById(R.id.description);
            cover = (SimpleDraweeView) view.findViewById(R.id.cover);
        }
    }

    public PlaceAdapter(RealmResults<PlaceModel> placeModelRealmResults){this.placeModelRealmResults = placeModelRealmResults;}

    @Override
    public PlaceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place,parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PlaceModel placeModel = this.placeModelRealmResults.get(position);
        holder.name.setText(placeModel.getName());
        holder.description.setText(placeModel.getDescription());
        holder.cover.setImageURI(placeModel.getCover());
    }

    @Override
    public int getItemCount() {
        return this.placeModelRealmResults.size();
    }

}
