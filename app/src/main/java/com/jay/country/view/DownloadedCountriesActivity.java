package com.jay.country.view;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;
import com.jay.country.R;
import com.jay.country.contract.DownloadedCountriesContract;
import com.jay.country.di.DaggerAppComponent;
import com.jay.country.di.PresenterModule;
import com.jay.country.di.SharedPreferenciesModule;
import com.jay.country.model.SharedPreferencesManager;
import com.jay.country.presenter.DownloadedCountriesPresenter;

import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DownloadedCountriesActivity extends AppCompatActivity implements DownloadedCountriesContract.View {

    public static final String TAG = "LOG_TAG";

    @Inject
    DownloadedCountriesPresenter presenter;

    @Inject
    SharedPreferencesManager preferencesManager;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.parent_layout)
    FrameLayout parentLayout;

    @BindView(R.id.countries_recycler_view)
    RecyclerView countriesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        DaggerAppComponent.builder()
                .presenterModule(new PresenterModule(this))
                .sharedPreferenciesModule(new SharedPreferenciesModule(this))
                .build()
                .inject(this);

        presenter.downloadCountriesList();
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
    public void downloadSuccessful(List<String> china, List<String> japan, List<String> thailand,
                                   List<String> india, List<String> malaysia) {


        //todo putBoolean
    }


    @Override
    public void downloadFailure(Throwable throwable) {

        Snackbar.make(parentLayout, throwable.getMessage(), Snackbar.LENGTH_LONG).show();
    }
}
