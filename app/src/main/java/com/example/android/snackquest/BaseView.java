package com.example.android.snackquest;

import android.app.Activity;

/**
 * Created by rafael on 20/11/17.
 */

interface BaseView {
    void showSnackBar(String msg);
    Activity getActivityFromView();
}
