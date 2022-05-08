package com.alireza.recipefood.DetailRecipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alireza.recipefood.Main.MainActivity;
import com.alireza.recipefood.Models.InstructionResponse;
import com.alireza.recipefood.Models.RecipeDetailResponse;
import com.alireza.recipefood.Models.SimilarRecipeResponse;
import com.alireza.recipefood.R;
import com.alireza.recipefood.Models.RequestManager;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeDetailActivity extends AppCompatActivity {
    RequestManager manager;
    int id;
    TextView tvRecipeNameDetail,tvSourceRecipeDetail,tvSummaryDetail;
    ImageView ivImageRecipeDetail;
    RecyclerView recyclerView_simpleRecipe , recyclerView_instruction;
    RecyclerView recyclerView_ingrediants;
    ProgressDialog dialog;
    SimilarRecipeAdapter similarRecipeAdapter;
    IngredientsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        findViews();
        dialog=new ProgressDialog(this);
        dialog.setTitle("Loading...");
        dialog.show();
        manager=new RequestManager(this);
        id=Integer.parseInt(getIntent().getStringExtra("id"));
        manager.getDetailRecipe(new RecipeDetailListener() {
            @Override
            public void didFetch(RecipeDetailResponse detailResponse, String message) {
                tvRecipeNameDetail.setText(detailResponse.title);
                tvSourceRecipeDetail.setText(detailResponse.sourceName);
                tvSummaryDetail.setText(detailResponse.summary);
                Picasso.get()
                        .load(detailResponse.image)
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.placeholder)
                        .into(ivImageRecipeDetail);
                adapter=new IngredientsAdapter(detailResponse.extendedIngredients,RecipeDetailActivity.this);
                recyclerView_ingrediants.setLayoutManager(new LinearLayoutManager(RecipeDetailActivity.this,RecyclerView.HORIZONTAL,false));
                recyclerView_ingrediants.setAdapter(adapter);
                dialog.dismiss();
            }

            @Override
            public void didError(String message) {
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
            }
        },id);


        manager.GetSimilarRecipe(new SimilarRecipeResponseListener() {
            @Override
            public void didFetch(List<SimilarRecipeResponse> similarRecipeResponses, String message) {
                similarRecipeAdapter=new SimilarRecipeAdapter(RecipeDetailActivity.this, similarRecipeResponses, new RecipeClickListener() {
                    @Override
                    public void onRecipeClick(String id) {
                        Intent intent=new Intent(RecipeDetailActivity.this, RecipeDetailActivity.class);
                        intent.putExtra("id",id);
                        startActivity(intent);
                    }
                });
                recyclerView_simpleRecipe=findViewById(R.id.recyclerRecipeSimple);
                recyclerView_simpleRecipe.setLayoutManager(new LinearLayoutManager(RecipeDetailActivity.this,RecyclerView.HORIZONTAL,false));
                recyclerView_simpleRecipe.setAdapter(similarRecipeAdapter);
            }

            @Override
            public void didError(String message) {

            }
        },id);

        manager.getInstructionResponse(new InstructionResponselistener() {
            @Override
            public void didFetch(List<InstructionResponse> instructionResponseList, String message) {
                recyclerView_instruction.setLayoutManager(new LinearLayoutManager(RecipeDetailActivity.this,RecyclerView.VERTICAL,false));
                recyclerView_instruction.setAdapter(new InstructionAdapter(instructionResponseList,RecipeDetailActivity.this));
            }

            @Override
            public void didError(String message) {

            }
        }, id);
    }


    private void findViews(){
        ivImageRecipeDetail=findViewById(R.id.ivImageRecipeDetail);
        tvRecipeNameDetail=findViewById(R.id.tvRecipeNameDetail);
        tvRecipeNameDetail.setSelected(true);
        recyclerView_instruction=findViewById(R.id.recyclerInstruction);
        tvSourceRecipeDetail=findViewById(R.id.tvSourceRecipeDetail);
        tvSummaryDetail=findViewById(R.id.tvSummaryDetail);
        recyclerView_ingrediants =findViewById(R.id.rvDetail);
    }

}