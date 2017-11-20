package com.example.android.snackquest;

/**
 * Created by rafael on 20/11/17.
 */

public interface HomePresenter extends BasePresenter{
    interface View extends BaseView{
        void configViews(Recipe recipe);
        void setAdapterOnRecycler(RecipeAdapter adapter);
    }
}
