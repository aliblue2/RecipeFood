package com.alireza.recipefood.DetailRecipe;

import com.alireza.recipefood.Models.SimilarRecipeResponse;

import java.util.List;

public interface SimilarRecipeResponseListener {

    void didFetch(List<SimilarRecipeResponse> similarRecipeResponses, String message);

    void didError(String message);
}
