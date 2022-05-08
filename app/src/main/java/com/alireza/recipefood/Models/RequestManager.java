package com.alireza.recipefood.Models;

import android.content.Context;

import com.alireza.recipefood.DetailRecipe.InstructionResponselistener;
import com.alireza.recipefood.DetailRecipe.RecipeDetailListener;
import com.alireza.recipefood.DetailRecipe.SimilarRecipeResponseListener;
import com.alireza.recipefood.R;
import com.alireza.recipefood.Main.RandomRecipeResponseListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager {
    private ApiService apiService;
    private Context context;
    public RequestManager(Context context) {
        this.context = context;
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService=retrofit.create(ApiService.class);
    }


    public void getDetailRecipe(RecipeDetailListener recipeDetailListener, int id){
        apiService.getInformationRecipe(id,context.getString(R.string.api_key)).enqueue(new Callback<RecipeDetailResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailResponse> call, Response<RecipeDetailResponse> response) {
                if (response.isSuccessful()) {
                    recipeDetailListener.didFetch(response.body(), response.message());
                }
            }

            @Override
            public void onFailure(Call<RecipeDetailResponse> call, Throwable t) {
                recipeDetailListener.didError(t.getMessage());
            }
        });
    }

    public void GetSimilarRecipe(SimilarRecipeResponseListener similarResponse, int id){
        apiService.getSimilarRecipe(id,context.getString(R.string.api_key),"10").enqueue(new Callback<List<SimilarRecipeResponse>>() {
            @Override
            public void onResponse(Call<List<SimilarRecipeResponse>> call, Response<List<SimilarRecipeResponse>> response) {
                if (response.isSuccessful()) {
                    similarResponse.didFetch(response.body(), response.message());
                }else if (!response.isSuccessful()){
                    similarResponse.didError(response.message());
                    return;
                }
            }

            @Override
            public void onFailure(Call<List<SimilarRecipeResponse>> call, Throwable t) {
                similarResponse.didError(t.getMessage());
            }
        });
    }

    public void getInstructionResponse(InstructionResponselistener instructionResponselistener,int id){
        apiService.getInstructionResponse(id,context.getString(R.string.api_key)).enqueue(new Callback<List<InstructionResponse>>() {
            @Override
            public void onResponse(Call<List<InstructionResponse>> call, Response<List<InstructionResponse>> response) {
                if (response.isSuccessful()){
                    instructionResponselistener.didFetch(response.body(), response.message());
                }else if (!response.isSuccessful()){
                    instructionResponselistener.didError(response.message());
                    return;
                }
            }

            @Override
            public void onFailure(Call<List<InstructionResponse>> call, Throwable t) {
                instructionResponselistener.didError(t.getMessage());
            }
        });
    }

    public void getRandomRecipeListener(RandomRecipeResponseListener responseListener , List<String> tags){
        apiService.getRandomRecipe(context.getString(R.string.api_key),"20",tags).enqueue(new Callback<RandomRecipesApi>() {
            @Override
            public void onResponse(Call<RandomRecipesApi> call, Response<RandomRecipesApi> response) {
                if (!response.isSuccessful()){
                    responseListener.didError(response.message());
                    return;
                }else if (response.isSuccessful()){
                    responseListener.didFetch(response.body(),response.message());
                }
            }

            @Override
            public void onFailure(Call<RandomRecipesApi> call, Throwable t) {
                responseListener.didError(t.getMessage());
            }
        });

    }
}
