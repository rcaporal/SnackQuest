package com.example.android.snackquest;

import android.app.Activity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael on 20/11/17.
 */

public class HomeActivityImpl implements HomePresenter{


    private RecipeAdapter mAdapter;
    private HomePresenter.View mView;
    private HomeActivity mActivity;
    private Recipe mRecipe;

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String msg) {

    }

    public void setView(HomePresenter.View view){
        mView = view;
        mActivity = (HomeActivity) view;
    }

    public void configAdapter(){
        final Activity activity = mView.getActivityFromView();
        mAdapter = new RecipeAdapter(mView.getActivityFromView(), new RecipeAdapter.OnRecipeClickListener() {
            @Override
            public void onItemRecipeClick(Recipe recipe) {
                Toast.makeText(mActivity, mActivity.getResources().getString(R.string.on_recipe_click) + recipe.getName(), Toast.LENGTH_SHORT).show();
                //mView.showSnackBar(mActivity.getResources().getString(R.string.on_recipe_click));
            }
        });

//        RecipeRepository recipeRepository = new RecipeRepository(mUser.getUid());
//        recipeRepository.findAllRecipesFromUserListener(new RecipeRepository.OnFindAllChannels() {
//            @Override
//            public void onFindAllChannelsSuccess(ArrayList<Recipe> channels) {
//                mAdapter.setContentOnList(channels);
//            }
//
//            @Override
//            public void onFindAllChannelsFailed(String e) {
//                mView.showSnackBar(activity.getString(R.string.loading_recipe_list_error));
//            }
//        });

        mAdapter.setRecipeListContent(generateTestRecipeList());
        mView.setAdapterOnRecycler(mAdapter);
    }

    private List<Recipe> generateTestRecipeList(){
        List<Recipe> recipeList = new ArrayList<>();
        for(int i = 1; i < 11; i++){
            recipeList.add(new Recipe("Receita "+i, i + "minutos"));
        }
        return recipeList;
    }
}
