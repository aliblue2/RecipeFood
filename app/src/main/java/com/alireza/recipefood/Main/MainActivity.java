package com.alireza.recipefood.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.alireza.recipefood.DetailRecipe.RecipeClickListener;
import com.alireza.recipefood.DetailRecipe.RecipeDetailActivity;
import com.alireza.recipefood.Models.RandomRecipesApi;
import com.alireza.recipefood.Models.RequestManager;
import com.alireza.recipefood.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RequestManager manager;
    private RecyclerView recyclerView;
    private ProgressDialog dialog;
    private SearchView searchView;
    private Spinner spinner;
    List<String> tags=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog=new ProgressDialog(this);
        dialog.setTitle("Loading...");
        searchView=findViewById(R.id.searchMain);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tags.clear();
                tags.add(query);
                manager.getRandomRecipeListener(randomRecipeResponseListener,tags);
                dialog.show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        spinner=findViewById(R.id.spinnerMain);
        ArrayAdapter adapter=ArrayAdapter.createFromResource(MainActivity.this,
                R.array.tags,
                R.layout.spinner_text);
        adapter.setDropDownViewResource(R.layout.inner_spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(spinnerItemListener);

        manager=new RequestManager(this);
//        manager.getRandomRecipeListener(randomRecipeResponseListener);
//        dialog.show();
    }

    private final RandomRecipeResponseListener randomRecipeResponseListener=new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipesApi response, String message) {
            recyclerView=findViewById(R.id.rvMain);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
            recyclerView.setAdapter(new RandomRecipeAdapter(MainActivity.this, response.recipes, new RecipeClickListener() {
                @Override
                public void onRecipeClick(String id) {
                    Intent intent=new Intent(MainActivity.this, RecipeDetailActivity.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
            }));
            dialog.dismiss();
        }

        @Override
        public void didError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private AdapterView.OnItemSelectedListener spinnerItemListener =new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            tags.clear();
            tags.add(adapterView.getSelectedItem().toString());
            manager.getRandomRecipeListener(randomRecipeResponseListener,tags);
            dialog.show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
}