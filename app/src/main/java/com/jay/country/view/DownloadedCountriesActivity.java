package com.jay.country.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;
import com.jay.country.R;
import com.jay.country.contract.DownloadedCountriesContract;
import com.jay.country.di.component.DaggerAppComponent;
import com.jay.country.di.module.PresenterModule;
import com.jay.country.di.module.SharedPreferencesModule;
import com.jay.country.model.adapter.CountriesAdapter;
import com.jay.country.model.sharedpreferencies.SharedPreferencesManager;
import com.jay.country.presenter.DownloadedCountriesPresenter;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DownloadedCountriesActivity extends AppCompatActivity implements DownloadedCountriesContract.View {

    @Inject
    DownloadedCountriesPresenter presenter;

    @Inject
    SharedPreferencesManager preferencesManager;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.parent_layout)
    FrameLayout parentLayout;

    @BindView(R.id.countries_expandable_list_view)
    ExpandableListView countriesExpandableList;

    CountriesAdapter countriesAdapter;

    private HashMap<String, List<String>> childTitleList = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        DaggerAppComponent.builder()
                .presenterModule(new PresenterModule(this))
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .build()
                .inject(this);

        presenter.downloadCountriesList();

        onCityClickListener();
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
    public void downloadSuccessful(List<String> countries, List<String> china, List<String> japan,
                                   List<String> thailand, List<String> india, List<String> malaysia) {

        childTitleList.put(countries.get(0), china);
        childTitleList.put(countries.get(1), japan);
        childTitleList.put(countries.get(2), thailand);
        childTitleList.put(countries.get(3), india);
        childTitleList.put(countries.get(4), malaysia);

        countriesAdapter = new CountriesAdapter(countries, childTitleList, this);
        countriesExpandableList.setAdapter(countriesAdapter);

        countriesAdapter.notifyDataSetChanged();

        presenter.saveIntoDatabase(this, countries, china, japan, thailand, india, malaysia);
    }


    @Override
    public void downloadFailure(Throwable throwable) {

        Snackbar.make(parentLayout, throwable.getMessage(), Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void successfulSaveIntoDatabase() {

        preferencesManager.putBoolean("downloadSuccess", true);

        Snackbar.make(parentLayout, getResources().getString(R.string.saving_successful),
                Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void failureSaveIntoDatabase(Throwable throwable) {

        Snackbar.make(parentLayout, throwable.getMessage(), Snackbar.LENGTH_LONG).show();
    }


    void onCityClickListener() {

        countriesExpandableList.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {

            String city = countriesAdapter.getCity(groupPosition, childPosition);

            startActivity(new Intent(this, CityDetailedActivity.class)
                    .putExtra("city", city));

            return false;
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
