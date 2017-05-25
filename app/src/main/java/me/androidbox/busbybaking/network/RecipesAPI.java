package me.androidbox.busbybaking.network;

import me.androidbox.busbybaking.model.Recipe;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

/**
 * Created by steve on 5/25/17.
 */

public interface RecipesAPI {
    @GET
    Call<Recipe> getReceipe(@Body Recipe recipe);
}
