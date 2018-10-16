package com.tobidaada.primetablechallenge.presentation.main;

public interface MainContract {

    interface MainView {

        void showErrorMessage();
        void sendDataToSheetActivity(int length);
        void refreshLayout();
    }

    interface MainPresenter {

        void verifyUserInput(String userInput);
        void onDestroy();

    }
}
