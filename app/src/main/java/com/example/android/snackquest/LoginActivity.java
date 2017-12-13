package com.example.android.snackquest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.register_button)
    TextView mRegisterButton;

    @BindView(R.id.email)
    EditText mEmail;

    @BindView(R.id.password)
    EditText mPassword;

    @BindView(R.id.login_button)
    Button mLoginButton;

    private FirebaseAuth auth;
    private User user;
    private UserPreferences userPreferences;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegisterActivity();
            }
        });
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    setUser();
            }
        });
        userPreferences = new UserPreferences(getApplicationContext());

        if(userPreferences.getUserID() != null){
            goToHomeActivity();
        }


    }

    private void setUser() {
        if(!(mPassword.getText().toString().equals("")) && !(mEmail.getText().toString().equals(""))){
            user = new User(mEmail.getText().toString(), mPassword.getText().toString());
            login(user);
        }else {
            Toast.makeText(getApplicationContext(), R.string.empty_fields_error, Toast.LENGTH_SHORT).show();
        }
    }

    private void goToRegisterActivity(){
        Intent goToRegisterActivity = new Intent(this, RegisterActivity.class);
        startActivity(goToRegisterActivity);
    }

    private void goToHomeActivity(){
        Intent goToHomeActivity = new Intent(this, HomeActivity.class);
        startActivity(goToHomeActivity);
        finish();
    }

    private void login(final User user){
        auth = FireBaseConfiguration.getFirebaseAuth();
        auth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            userRepository = new UserRepository(getApplicationContext(), user);
                            userRepository.getUserByEmail(new UserRepository.OnGetUserByEmail() {
                                @Override
                                public void onGetUserByEmailIsSuccessful(User user) {
                                    userPreferences.saveUserPreferences(user.getId(), user.getName());
                                    goToHomeActivity();
                                }

                                @Override
                                public void onGetUserByEmailIsFailed(String errorMessage) {

                                }
                            });

                            String id = userPreferences.getUserID();
                            String name = userPreferences.getUserName();
                        }else {
                            Toast.makeText(getApplicationContext(), R.string.login_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
