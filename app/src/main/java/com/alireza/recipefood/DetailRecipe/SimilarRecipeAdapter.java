package com.alireza.recipefood.DetailRecipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alireza.recipefood.Models.SimilarRecipeResponse;
import com.alireza.recipefood.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarRecipeAdapter extends RecyclerView.Adapter<SimilarRecipeAdapter.SimilarViewHolder>{
    private Context context;
    private List<SimilarRecipeResponse> similarRecipeResponseList;
    private RecipeClickListener listener;
    public SimilarRecipeAdapter(Context context, List<SimilarRecipeResponse> similarRecipeResponseList, RecipeClickListener listener) {
        this.context = context;
        this.similarRecipeResponseList = similarRecipeResponseList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SimilarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimilarViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe_simple,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarViewHolder holder, int position) {
        holder.bindSimilarRecipe(similarRecipeResponseList.get(position));
    }

    @Override
    public int getItemCount() {
        return similarRecipeResponseList.size();
    }

    public class SimilarViewHolder extends RecyclerView.ViewHolder{
        TextView tvSimilarTittle,tvSimilarServing;
        ImageView ivSimilarImage;
        public SimilarViewHolder(@NonNull View itemView) {
            super(itemView);
            ivSimilarImage=itemView.findViewById(R.id.ivImageRecipeSimilar);
            tvSimilarTittle=itemView.findViewById(R.id.tvTittleSimilar);
            tvSimilarServing=itemView.findViewById(R.id.tvServingSimilar);
        }

        public void bindSimilarRecipe(SimilarRecipeResponse similarRecipeResponse){
            tvSimilarTittle.setSelected(true);
            tvSimilarServing.setSelected(true);
            Picasso.get().load("https://spoonacular.com/recipeImages/"+similarRecipeResponse.id+"-556x370."+similarRecipeResponse.imageType)
                    .placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(ivSimilarImage);
            tvSimilarServing.setText(similarRecipeResponse.servings+" Person");
            tvSimilarTittle.setText(similarRecipeResponse.title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onRecipeClick(String.valueOf(similarRecipeResponse.id));
                }
            });
        }
    }
}
