package com.jay.country.view;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.jay.country.R;
import com.jay.country.contract.CityDetailedContract;
import com.jay.country.presenter.CityDetailedPresenter;

public class CityDetailedActivity extends AppCompatActivity implements CityDetailedContract.View {

    private String TAG = CityDetailedActivity.class.getName();

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.article)
    TextView articleTextView;

    @BindView(R.id.parent_layout)
    FrameLayout parentLayout;


    CityDetailedPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        ButterKnife.bind(this);

        String city = getIntent().getStringExtra("city");

        presenter = new CityDetailedPresenter(this);
        presenter.loadArticle(city);
    }


    @Override
    public void showProgressBar() {

        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void hideProgressBar() {

        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void onLoadArticleSuccessful(String article) {

        articleTextView.setText(article);
    }


    @Override
    public void onLoadArticleFailure(Throwable throwable) {

        Snackbar.make(parentLayout, throwable.getMessage(), Snackbar.LENGTH_LONG).show();
    }
}
