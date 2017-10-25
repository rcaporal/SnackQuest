package com.example.android.snackquest;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by caporal on 25/10/17.
 */

public class FireBaseConfiguration {

    private static FirebaseAuth firebaseAuth;
    private static DatabaseReference databaseReference;

    public static DatabaseReference getDatabase(){
        if(databaseReference == null){
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }
            return databaseReference;
    }

    public static FirebaseAuth getFirebaseAuth(){
        if(firebaseAuth == null){
            firebaseAuth = FirebaseAuth.getInstance();
        }
        return firebaseAuth;
    }


}
