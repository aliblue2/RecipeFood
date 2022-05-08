package com.alireza.recipefood.DetailRecipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alireza.recipefood.Models.ExtendedIngredient;
import com.alireza.recipefood.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {
    private List<ExtendedIngredient> ingredientsList;
    private Context context;

    public IngredientsAdapter(List<ExtendedIngredient> ingredients, Context context) {
        this.ingredientsList = ingredients;
        this.context = context;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recy_detail,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        holder.bindIngredients(ingredientsList.get(position));
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }

    public class IngredientsViewHolder extends RecyclerView.ViewHolder{
        TextView tvIngredientsQuantity,tvIngredientsName;
        ImageView ivIngredientsImage;
        public IngredientsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIngredientsQuantity=itemView.findViewById(R.id.tvIngredientsQuantity);
            tvIngredientsName=itemView.findViewById(R.id.tvIngredientsName);
            ivIngredientsImage=itemView.findViewById(R.id.ivIngredientsImage);
        }
        public void bindIngredients(ExtendedIngredient ingredient){
            tvIngredientsName.setSelected(true);
            tvIngredientsQuantity.setSelected(true);
            tvIngredientsName.setText(ingredient.name);
            tvIngredientsQuantity.setText(ingredient.original);
            Picasso.get()
                    .load("https://spoonacular.com/cdn/ingredients_100x100/"+ingredient.image)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(ivIngredientsImage);

        }
    }
}
