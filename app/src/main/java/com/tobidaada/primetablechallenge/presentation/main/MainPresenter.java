package com.tobidaada.primetablechallenge.presentation.main;

import android.text.TextUtils;

/**
 *  Java Class that handles the business logic for
 *  the MainActivity
 */
public class MainPresenter implements MainContract.MainPresenter {

    private MainContract.MainView mView;

    public MainPresenter(MainContract.MainView view) {
        mView = view;
    }

    @Override
    public void verifyUserInput(String userInput) {
        boolean isOk = true;

        if (TextUtils.isEmpty(userInput)) {
            mView.showErrorMessage();
            isOk = false;
        }

        if (isOk) {
            int length = Integer.parseInt(userInput);
            mView.sendDataToSheetActivity(length);
        }
    }

    @Override
    public void onDestroy() {
        mView = null;
    }
}
