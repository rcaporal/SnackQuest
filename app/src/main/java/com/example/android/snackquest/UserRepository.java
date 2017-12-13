package com.example.android.snackquest;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by caporal on 28/10/17.
 */

public class UserRepository {

    private DatabaseReference databaseReference;
    private final User user;
    private Context context;

    public UserRepository(Context context,User user) {
        this.user = user;
        this.context = context;
    }

    public void createUserWithEmail(final OnCreateUserWithEmail onCreateUserWithEmail){
        FirebaseAuth firebaseAuth = FireBaseConfiguration.getFirebaseAuth();
        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    databaseReference = FireBaseConfiguration.getDatabase();
                    databaseReference.child("user").child(user.getId()).setValue(user)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        onCreateUserWithEmail.onCreateUserWithEmailIsSuccessful(user);
                                    }else {
                                        onCreateUserWithEmail.onCreateUserWithEmailIsFailed(context.getResources().getString(R.string.create_user_error));
                                    }
                                }
                            });
                }else {
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e) {
                        onCreateUserWithEmail.onCreateUserWithEmailIsFailed(context.getResources().getString(R.string.weak_password));
                    }catch (FirebaseAuthUserCollisionException e) {
                        onCreateUserWithEmail.onCreateUserWithEmailIsFailed(context.getResources().getString(R.string.user_cosilion));
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        onCreateUserWithEmail.onCreateUserWithEmailIsFailed(context.getResources().getString(R.string.invalid_credentials));
                    } catch (Exception e) {
                        onCreateUserWithEmail.onCreateUserWithEmailIsFailed(context.getResources().getString(R.string.create_user_error));
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void getUserByEmail(final OnGetUserByEmail onGetUserByEmail){
        databaseReference = FireBaseConfiguration.getDatabase();
        databaseReference.orderByChild("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    User userLoop = data.getValue(User.class);
                    userLoop.setId(data.getKey());
                    if(userLoop.getEmail().equals(user)){
                        onGetUserByEmail.onGetUserByEmailIsSuccessful(userLoop);

                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                onGetUserByEmail.onGetUserByEmailIsFailed(context.getResources().getString(R.string.database_error));
            }
        });
    }



    public interface OnCreateUserWithEmail{
        void onCreateUserWithEmailIsSuccessful(User user);
        void onCreateUserWithEmailIsFailed(String errorMessage);
    }

    public interface OnGetUserByEmail{
        void onGetUserByEmailIsSuccessful(User user);
        void onGetUserByEmailIsFailed(String errorMessage);
    }
}
