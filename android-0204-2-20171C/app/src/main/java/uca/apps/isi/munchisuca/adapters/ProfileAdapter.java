package uca.apps.isi.munchisuca.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import uca.apps.isi.munchisuca.R;
import uca.apps.isi.munchisuca.models.ProfileModel;

/**
 * Created by mcama on 19/4/2017.
 */

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder>{

    public List<ProfileModel> profileModels;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView email;
        public TextView password;
        public TextView name;
        public TextView id;
        public TextView profile_id;

        public ViewHolder(View view) {
            super(view);
            email = (TextView) view.findViewById(R.id.email);
            password = (TextView) view.findViewById(R.id.password);
            name = (TextView) view.findViewById(R.id.name);
            id = (TextView) view.findViewById(R.id.id);
            profile_id = (TextView) view.findViewById(R.id.profile_id);
        }
    }

    public ProfileAdapter(List<ProfileModel> profileModels){this.profileModels = profileModels;}

    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile,parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProfileModel profileModel = this.profileModels.get(position);
        holder.email.setText(profileModel.getEmail());
        holder.password.setText(profileModel.getPassword());
        holder.name.setText(profileModel.getName());
        holder.id.setText(profileModel.getId());
        holder.profile_id.setText(profileModel.getProfile_id());
    }

    @Override
    public int getItemCount() {
        return this.profileModels.size();
    }




}
