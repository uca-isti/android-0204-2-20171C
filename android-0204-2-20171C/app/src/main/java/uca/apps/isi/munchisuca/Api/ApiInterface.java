package uca.apps.isi.munchisuca.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import uca.apps.isi.munchisuca.models.ProfileModel;

/**
 * Created by mcama on 19/4/2017.
 */

public interface ApiInterface {
    @GET("profiles")
    Call<List<ProfileModel>> getProfile();


}
