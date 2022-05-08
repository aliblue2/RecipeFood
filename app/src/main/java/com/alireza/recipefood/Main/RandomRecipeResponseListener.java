package com.alireza.recipefood.Main;

import com.alireza.recipefood.Models.RandomRecipesApi;

public interface RandomRecipeResponseListener {

    void didFetch(RandomRecipesApi response , String message);

    void didError(String message);
}
