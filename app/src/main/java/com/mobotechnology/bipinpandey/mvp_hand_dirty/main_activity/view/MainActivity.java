package com.mobotechnology.bipinpandey.mvp_hand_dirty.main_activity.view;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mobotechnology.bipinpandey.mvp_hand_dirty.R;
import com.mobotechnology.bipinpandey.mvp_hand_dirty.main_activity.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.View {

    private MainActivityPresenter presenter;
    private TextView myTextView;
    private ProgressBar progressBar;

    private EditText email, userName;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new MainActivityPresenter(this);

        myTextView = findViewById(R.id.myTextView);

        email = findViewById(R.id.email);
        userName = findViewById(R.id.username);

        fab = findViewById(R.id.fab);

        initProgressBar();

        fab.setOnClickListener(onClickListener);
    }

    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleSmall);
        progressBar.setIndeterminate(true);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(Resources.getSystem().getDisplayMetrics().widthPixels,
                250);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        this.addContentView(progressBar, params);
    }

    @Override
    public void updateUserInfoTextView(String info) {
        myTextView.setText(info);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLoginResponse(boolean isSuccess) {
        Toast.makeText(this, "onLoginResponse : " + isSuccess, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailed() {
        Toast.makeText(this, "onLoginFailed", Toast.LENGTH_SHORT).show();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.updateEmail(email.getText().toString());
            presenter.updateFullName(userName.getText().toString());
            presenter.Login();
        }
    };
}
