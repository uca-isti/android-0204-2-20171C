package uca.apps.isi.munchisuca.Api;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import uca.apps.isi.munchisuca.models.PlaceModel;
import uca.apps.isi.munchisuca.models.ProfileModel;
import uca.apps.isi.munchisuca.models.PromotionModel;
import uca.apps.isi.munchisuca.models.TypeModel;


/**
 * Created by mcama on 19/4/2017.
 */

public interface ApiInterface {
    @GET("places")
    Call<List<PlaceModel>> getPlaces();
    @GET("promotions")
    Call<List<PromotionModel>> getPromotions();
    @GET("types")
    Call<List<TypeModel>> getType();
}
