package com.example.android.snackquest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.register_button)
    Button mRegisterButton;

    @BindView(R.id.email)
    EditText mEmail;

    @BindView(R.id.password)
    EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToLoginActivity();
            }
        });
    }

    private void backToLoginActivity(){
        Intent backToLoginActivity = new Intent(this, MainActivity.class);
        startActivity(backToLoginActivity);
        finish();
    }
}
