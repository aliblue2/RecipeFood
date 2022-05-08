package com.alireza.recipefood.DetailRecipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alireza.recipefood.Models.Step;
import com.alireza.recipefood.R;

import java.lang.invoke.LambdaConversionException;
import java.util.List;

public class InstructionStepAdapter extends RecyclerView.Adapter<InstructionStepAdapter.InstructionStepViewHolder> {
    private Context context;
    private List<Step> stepList;

    public InstructionStepAdapter(Context context, List<Step> stepList) {
        this.context = context;
        this.stepList = stepList;
    }

    @NonNull
    @Override
    public InstructionStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionStepViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recy_steps,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionStepViewHolder holder, int position) {
        holder.bindSteps(stepList.get(position));
    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }

    public class InstructionStepViewHolder extends RecyclerView.ViewHolder{
        TextView tvNumberStep,tvTittleStep;
        RecyclerView rvIngredientsStep,rvEquipments;
        public InstructionStepViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumberStep=itemView.findViewById(R.id.tvInstructionStepNumber);
            tvTittleStep=itemView.findViewById(R.id.tvInstructionStepTittle);
            rvEquipments=itemView.findViewById(R.id.recyclerInstructionStepsEquipments);
            rvIngredientsStep=itemView.findViewById(R.id.recyclerInstructionStepsIngredients);
        }
        public void bindSteps(Step step){
            tvTittleStep.setText(step.step);
            tvNumberStep.setText(String.valueOf(step.number));
            rvIngredientsStep.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
            rvIngredientsStep.setAdapter(new InstructionIngredientsAdapter(context,step.ingredients));

            rvEquipments.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
            rvEquipments.setAdapter(new InstructionEquipmentsAdapter(context,step.equipment));
        }
    }
}
