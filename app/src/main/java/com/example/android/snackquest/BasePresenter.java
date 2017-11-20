package com.example.android.snackquest;

/**
 * Created by rafael on 20/11/17.
 */

public interface BasePresenter {

    void resume();
    void pause();
    void stop();
    void destroy();
    void onError(String msg);

}
