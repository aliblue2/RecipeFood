package com.alireza.recipefood.Models;

import com.alireza.recipefood.Models.RandomRecipesApi;
import com.alireza.recipefood.Models.RecipeDetailResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("recipes/random")
    Call<RandomRecipesApi> getRandomRecipe(@Query("apiKey") String apiKey, @Query("number") String number, @Query("tags")List<String> tags);

    @GET("recipes/{id}/information")
    Call<RecipeDetailResponse> getInformationRecipe(@Path("id") int id, @Query("apiKey") String apiKey );

    @GET("recipes/{id}/similar")
    Call<List<SimilarRecipeResponse>> getSimilarRecipe(@Path("id") int id,@Query("apiKey") String apiKey , @Query("number") String number);

    @GET("recipes/{id}/analyzedInstructions")
    Call<List<InstructionResponse>> getInstructionResponse(@Path("id") int id,@Query("apiKey")String apiKey);
}
