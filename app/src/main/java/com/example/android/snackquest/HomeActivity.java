package com.example.android.snackquest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.logoff_button)
    Button mLogoffButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mLogoffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loggoff();
            }
        });
    }

    private void loggoff() {
        Toast.makeText(getApplicationContext(), R.string.logoff_message, Toast.LENGTH_SHORT).show();
        goToLoginActivity();
    }

    private void goToLoginActivity() {
        Intent goToLoginActivity = new Intent(this, LoginActivity.class);
        startActivity(goToLoginActivity);
        finish();
    }
}
