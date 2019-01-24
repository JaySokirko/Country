package com.jay.country.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import com.jay.country.di.component.DaggerAppComponent;
import com.jay.country.di.module.PresenterModule;
import com.jay.country.di.module.SharedPreferencesModule;
import com.jay.country.model.adapter.CityAdapter;
import com.jay.country.presenter.CityDetailedPresenter;

import java.util.List;

import javax.inject.Inject;

public class CityDetailedActivity extends AppCompatActivity implements CityDetailedContract.View {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.recycler_view_article)
    RecyclerView articleRecyclerView;

    @BindView(R.id.parent_layout)
    FrameLayout parentLayout;

    @Inject
    CityDetailedPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        ButterKnife.bind(this);

        DaggerAppComponent.builder()
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .presenterModule(new PresenterModule(this))
                .build()
                .inject(this);

        articleRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        String city = getIntent().getStringExtra("city");

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
    public void onLoadArticleSuccessful(List<String> articleList, List<String> imageUtlList) {

        CityAdapter cityAdapter = new CityAdapter(articleList, imageUtlList, this);
        articleRecyclerView.setAdapter(cityAdapter);

        cityAdapter.notifyDataSetChanged();
    }


    @Override
    public void onLoadArticleFailure(Throwable throwable) {

        Snackbar.make(parentLayout, throwable.getMessage(), Snackbar.LENGTH_LONG).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
