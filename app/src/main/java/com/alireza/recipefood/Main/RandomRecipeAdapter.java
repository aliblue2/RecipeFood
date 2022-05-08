package com.alireza.recipefood.Main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.alireza.recipefood.DetailRecipe.RecipeClickListener;
import com.alireza.recipefood.Models.Recipe;
import com.alireza.recipefood.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeAdapter.RandomRecipeViewHolder> {
    private Context context;
    private List<Recipe> recipeList;
    private RecipeClickListener listener;
    public RandomRecipeAdapter(Context context, List<Recipe> recipeList, RecipeClickListener listener) {
        this.context = context;
        this.recipeList = recipeList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recy,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        holder.bindRecipe(recipeList.get(position));
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class RandomRecipeViewHolder extends RecyclerView.ViewHolder{
        CardView cardViewRecipe;
        TextView tvRecipeName,tvLike,tvServing,tvTimer;
        ImageView ivRecipeImage;
        public RandomRecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewRecipe=itemView.findViewById(R.id.cardViewRecipe);
            tvRecipeName=itemView.findViewById(R.id.tvRecipe);
            tvLike=itemView.findViewById(R.id.tvLike);
            tvServing=itemView.findViewById(R.id.tvServing);
            tvTimer=itemView.findViewById(R.id.tvTimer);
            ivRecipeImage=itemView.findViewById(R.id.ivRecipeImage);
        }

        public void bindRecipe(Recipe recipe){
            tvRecipeName.setText(recipe.title);
            tvTimer.setText(recipe.readyInMinutes+" Minute");
            tvLike.setText(recipe.aggregateLikes+" like");
            tvServing.setText(recipe.servings+" serving");
            itemView.setOnClickListener(view -> {
                listener.onRecipeClick(String.valueOf(recipe.id));
            });
            Picasso.get()
                    .load(recipe.image)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(ivRecipeImage);

        }
    }
}
