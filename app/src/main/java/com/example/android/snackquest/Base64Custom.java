package com.example.android.snackquest;

import android.util.Base64;

/**
 * Created by caporal on 28/10/17.
 */

public class Base64Custom {

    public static String encodeBase64Custom(String text){
        return Base64.encodeToString(text.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "");
    }

    public static String decodeBase64Custom(String encodedText){
        return new String(Base64.decode(encodedText, Base64.DEFAULT));
    }
}
