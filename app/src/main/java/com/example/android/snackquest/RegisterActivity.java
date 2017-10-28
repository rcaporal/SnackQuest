package com.example.android.snackquest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.register_button)
    Button mRegisterButton;

    @BindView(R.id.name)
    EditText mName;

    @BindView(R.id.email)
    EditText mEmail;

    @BindView(R.id.password)
    EditText mPassword;

    @BindView(R.id.password_confirm)
    EditText mPasswordConfirm;

    private User user;
    private UserRepository userRepository;
    private UserPreferences userPreferences;
    private Base64Custom base64Custom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
    }

    private void backToLoginActivity(){
        Intent backToLoginActivity = new Intent(this, LoginActivity.class);
        startActivity(backToLoginActivity);
        finish();
    }

    private void createUser(){
        user = new User(mName.getText().toString(),mEmail.getText().toString(), mPassword.getText().toString());
        if(isValidUser()){
            user.setId(base64Custom.encodeBase64Custom(user.getEmail()));
            userRepository = new UserRepository(RegisterActivity.this, user);
            userRepository.createUserWithEmail(new UserRepository.OnCreateUserWithEmail() {
                @Override
                public void onCreateUserWithEmailIsSuccessful(User user) {
                    Toast.makeText(RegisterActivity.this, R.string.save_user_success, Toast.LENGTH_SHORT).show();
                    backToLoginActivity();
                }

                @Override
                public void onCreateUserWithEmailIsFailed(String errorMessage) {
                    Toast.makeText(RegisterActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                }
            });

        }
    }

    private Boolean isValidUser(){
        if(user.getEmail().equals("") || user.getPassword().equals("") || user.getName().equals("")){
            Toast.makeText(RegisterActivity.this, R.string.empty_fields_error, Toast.LENGTH_SHORT).show();
            return false;
        }else if(!user.getPassword().equals(mPasswordConfirm.getText().toString())){
            Toast.makeText(RegisterActivity.this, R.string.password_mismatch, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
