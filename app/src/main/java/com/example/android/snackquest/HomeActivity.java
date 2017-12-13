package com.example.android.snackquest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomePresenter.View {

    @BindView(R.id.home_linear_layout)
    LinearLayout homeLinearLayout;
    @BindView(R.id.logoff_button)
    Button mLogoffButton;
    @BindView(R.id.recipe_recycler_view)
    RecyclerView mRecyclerView;
    private RecipeAdapter adapter;
    private HomeActivityImpl mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mPresenter = new HomeActivityImpl();
        mPresenter.setView(this);
        mPresenter.configAdapter();

        UserPreferences userPreferences = new UserPreferences(getApplicationContext());
        String name = userPreferences.getUserName();

        mLogoffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOff();
            }
        });
    }

    private void logOff() {
        Toast.makeText(getApplicationContext(), R.string.logoff_message, Toast.LENGTH_SHORT).show();
        goToLoginActivity();
    }

    private void goToLoginActivity() {
        Intent goToLoginActivity = new Intent(this, LoginActivity.class);
        startActivity(goToLoginActivity);
        finish();
    }

    @Override
    public void showSnackBar(String msg) {
        Snackbar.make(homeLinearLayout, msg, Snackbar.LENGTH_SHORT);
    }

    @Override
    public Activity getActivityFromView() {
        return this;
    }

    @Override
    public void configViews(Recipe recipe) {

    }

    @Override
    public void setAdapterOnRecycler(RecipeAdapter adapter) {
        this.adapter = adapter;
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        //mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setAdapter(adapter);
    }
}
