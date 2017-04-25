package uca.apps.isi.munchisuca.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.RealmResults;
import uca.apps.isi.munchisuca.R;
import uca.apps.isi.munchisuca.models.TypeModel;

/**
 * Created by Mayuyo on 24/4/2017.
 */

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.ViewHolder> {

    public RealmResults<TypeModel> TypeModelRealmResults;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nameType;
        public TextView descriptionType;


        public ViewHolder(View view) {
            super(view);
            nameType = (TextView) view.findViewById(R.id.nameType);
            descriptionType = (TextView) view.findViewById(R.id.descriptionType);
        }
    }

    public TypeAdapter(RealmResults<TypeModel> TypeModelRealmResults){this.TypeModelRealmResults= TypeModelRealmResults;}

    @Override
    public TypeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type,parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TypeModel TypeModel = this.TypeModelRealmResults.get(position);
        holder.nameType.setText(TypeModel.getName());
        holder.descriptionType.setText(TypeModel.getDescription());
    }

    @Override
    public int getItemCount() {
        return this.TypeModelRealmResults.size();
    }
}
