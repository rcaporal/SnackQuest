package com.example.android.snackquest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rafael on 20/11/17.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder>{

    private final Context context;
    private final OnRecipeClickListener onRecipeClickListener;
    private List<Recipe> mRecipeList;
    private FirebaseUser currentUser;

    public RecipeAdapter(Context context, OnRecipeClickListener onRecipeClickListener) {
        this.context = context;
        this.onRecipeClickListener = onRecipeClickListener;
        this.mRecipeList = new ArrayList<>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recipe_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //TODO: make recipe's bind
        final Recipe recipe = mRecipeList.get(position);
        holder.recipeName.setText(recipe.getName());
        holder.recipeTime.setText(recipe.getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecipeClickListener.onItemRecipeClick(recipe);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    public void setRecipeListContent(List<Recipe> recipes) {
        mRecipeList.clear();
        mRecipeList.addAll(recipes);
        notifyDataSetChanged();
    }

    public List<Recipe> getChannelList(){
        return this.mRecipeList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.recipe_image)
        ImageView recipeImage;
        @BindView(R.id.recipe_name)
        TextView recipeName;
        @BindView(R.id.recipe_time)
        TextView recipeTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnRecipeClickListener {
        void onItemRecipeClick(Recipe recipe);
    }
}
