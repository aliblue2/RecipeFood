package com.alireza.recipefood.DetailRecipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alireza.recipefood.Models.Equipment;
import com.alireza.recipefood.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstructionEquipmentsAdapter extends RecyclerView.Adapter<InstructionEquipmentsAdapter.InstructionIngredientsViewHolder> {
    private Context context;
    private List<Equipment> equipmentList;

    public InstructionEquipmentsAdapter(Context context, List<Equipment> equipmentList) {
        this.context = context;
        this.equipmentList = equipmentList;
    }

    @NonNull
    @Override
    public InstructionIngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionIngredientsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recy_ingredients_step,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionIngredientsViewHolder holder, int position) {
        holder.bindIngredient(equipmentList.get(position));
    }

    @Override
    public int getItemCount() {
        return equipmentList.size();
    }
    ImageView ivEquipmentsImageSteps;
    TextView tvEquipmentsStepItem;
    public class InstructionIngredientsViewHolder extends RecyclerView.ViewHolder{

        public InstructionIngredientsViewHolder(@NonNull View itemView) {
            super(itemView);
            ivEquipmentsImageSteps =itemView.findViewById(R.id.ivIngredientsImageSteps);
            tvEquipmentsStepItem =itemView.findViewById(R.id.tvIngredientsStepItem);
        }
        public void bindIngredient(Equipment equipment){
            tvEquipmentsStepItem.setSelected(true);
            tvEquipmentsStepItem.setText(equipment.name);
            Picasso.get()
                    .load("https://spoonacular.com/cdn/equipment_100x100/"+equipment.image)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(ivEquipmentsImageSteps);
        }
    }
}
