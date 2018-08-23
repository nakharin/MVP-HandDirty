package com.mobotechnology.bipinpandey.mvp_hand_dirty.main_activity.presenter;

import android.os.Handler;

import com.mobotechnology.bipinpandey.mvp_hand_dirty.main_activity.model.User;

/**
 * Created by bpn on 11/30/17.
 * <p>
 * 0. In MVP the presenter assumes the functionality of the "middle-man". All presentation logic is pushed to the presenter.
 * 1. Listens to user action and model updates
 * 2. Updates model and view
 */

public class MainActivityPresenter {

    private User user;
    private View view;

    public MainActivityPresenter(View view) {
        this.user = new User();
        this.view = view;
    }

    public void updateFullName(String fullName) {
        user.setFullName(fullName);
        view.updateUserInfoTextView(user.toString());
    }

    public void updateEmail(String email) {
        user.setEmail(email);
        view.updateUserInfoTextView(user.toString());
    }

    public void Login() {

        view.showProgressBar();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (user.getFullName() == null) {
                    view.hideProgressBar();
                    view.onLoginFailed();
                    return;
                }

                if (user.getFullName().equals("peet")) {
                    view.hideProgressBar();
                    view.onLoginResponse(true);
                } else {
                    view.hideProgressBar();
                    view.onLoginResponse(false);
                }
            }
        }, 2000);
    }

    public interface View {
        void updateUserInfoTextView(String info);
        void showProgressBar();
        void hideProgressBar();
        void onLoginResponse(boolean isSuccess);
        void onLoginFailed();
    }
}
