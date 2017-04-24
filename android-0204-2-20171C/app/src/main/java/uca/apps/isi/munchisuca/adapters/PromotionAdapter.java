package uca.apps.isi.munchisuca.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import io.realm.RealmResults;
import uca.apps.isi.munchisuca.R;
import uca.apps.isi.munchisuca.models.PromotionModel;

/**
 * Created by Grecia Guzmán on 23/4/2017.
 */

public class PromotionAdapter extends RecyclerView.Adapter<PromotionAdapter.ViewHolder> {

    public RealmResults<PromotionModel> PromotionModelRealmResults;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView namePromotion;
        public TextView descriptionPromotion;


        public ViewHolder(View view) {
            super(view);
            namePromotion = (TextView) view.findViewById(R.id.namePromotion);
            descriptionPromotion = (TextView) view.findViewById(R.id.descriptionPromotion);
        }
    }

    public PromotionAdapter(RealmResults<PromotionModel> PromotionModelRealmResults){this.PromotionModelRealmResults = PromotionModelRealmResults;}

    @Override
    public PromotionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_promotion,parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PromotionModel PromotionModel = this.PromotionModelRealmResults.get(position);
        holder.namePromotion.setText(PromotionModel.getName());
        holder.descriptionPromotion.setText(PromotionModel.getDescription());
    }

    @Override
    public int getItemCount() {
        return this.PromotionModelRealmResults.size();
    }
}
