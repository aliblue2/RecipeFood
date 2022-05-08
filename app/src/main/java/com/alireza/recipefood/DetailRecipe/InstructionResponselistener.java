package com.alireza.recipefood.DetailRecipe;

import com.alireza.recipefood.Models.InstructionResponse;

import java.util.List;

public interface InstructionResponselistener {

    void didFetch(List<InstructionResponse> instructionResponseList, String message);

    void didError(String message);
}
