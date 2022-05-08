package com.alireza.recipefood.DetailRecipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alireza.recipefood.Models.Ingredient;
import com.alireza.recipefood.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstructionIngredientsAdapter extends RecyclerView.Adapter<InstructionIngredientsAdapter.InstructionIngredientsViewHolder> {
    private Context context;
    private List<Ingredient> ingredientList;

    public InstructionIngredientsAdapter(Context context, List<Ingredient> ingredientList) {
        this.context = context;
        this.ingredientList = ingredientList;
    }

    @NonNull
    @Override
    public InstructionIngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionIngredientsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recy_ingredients_step,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionIngredientsViewHolder holder, int position) {
        holder.bindIngredient(ingredientList.get(position));
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public class InstructionIngredientsViewHolder extends RecyclerView.ViewHolder{
        ImageView ivIngredientsImageSteps;
        TextView tvIngredientsStepItem;
        public InstructionIngredientsViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIngredientsImageSteps=itemView.findViewById(R.id.ivIngredientsImageSteps);
            tvIngredientsStepItem=itemView.findViewById(R.id.tvIngredientsStepItem);
        }
        public void bindIngredient(Ingredient ingredient){
            tvIngredientsStepItem.setSelected(true);
            tvIngredientsStepItem.setText(ingredient.name);
            Picasso.get()
                    .load("https://spoonacular.com/cdn/ingredients_100x100/"+ingredient.image)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(ivIngredientsImageSteps);
        }
    }
}
