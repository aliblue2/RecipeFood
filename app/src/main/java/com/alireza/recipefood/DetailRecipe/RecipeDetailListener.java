package com.alireza.recipefood.DetailRecipe;

import com.alireza.recipefood.Models.RecipeDetailResponse;

public interface RecipeDetailListener {
    void didFetch(RecipeDetailResponse detailResponse,String message);

    void didError(String message);
}
