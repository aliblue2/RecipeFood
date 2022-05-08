package com.alireza.recipefood.DetailRecipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alireza.recipefood.Models.InstructionResponse;
import com.alireza.recipefood.R;

import java.util.List;

public class InstructionAdapter extends RecyclerView.Adapter<InstructionAdapter.InstructionViewHolder> {
    private List<InstructionResponse> instructionResponseList;
    private Context context;

    public InstructionAdapter(List<InstructionResponse> instructionResponseList, Context context) {
        this.instructionResponseList = instructionResponseList;
        this.context = context;
    }

    @NonNull
    @Override
    public InstructionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recy_instructions,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionViewHolder holder, int position) {
        holder.bindInstruction(instructionResponseList.get(position));
    }

    @Override
    public int getItemCount() {
        return instructionResponseList.size();
    }

    public class InstructionViewHolder extends RecyclerView.ViewHolder{
        TextView tvInstructionName;
        RecyclerView rvStepInstruction;
        public InstructionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvInstructionName=itemView.findViewById(R.id.tvInstructionName);
            rvStepInstruction=itemView.findViewById(R.id.recyclerStepInstruction);
        }
        public void bindInstruction(InstructionResponse response){
            tvInstructionName.setText(response.name);
            rvStepInstruction.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
            rvStepInstruction.setAdapter(new InstructionStepAdapter(context,response.steps));
        }
    }
}
