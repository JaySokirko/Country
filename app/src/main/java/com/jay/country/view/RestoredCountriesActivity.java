package com.jay.country.view;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;
import com.jay.country.R;
import com.jay.country.contract.RestoredCountriesContract;
import com.jay.country.di.component.DaggerAppComponent;
import com.jay.country.di.module.PresenterModule;
import com.jay.country.di.module.SharedPreferencesModule;
import com.jay.country.model.adapter.CountriesAdapter;
import com.jay.country.presenter.RestoreCountriesPresenter;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

public class RestoredCountriesActivity extends AppCompatActivity implements RestoredCountriesContract.View {

    @BindView(R.id.parent_layout)
    FrameLayout parentLayout;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.countries_expandable_list_view)
    ExpandableListView countriesExpandableListView;

    @Inject
    RestoreCountriesPresenter presenter;

    private CountriesAdapter countriesAdapter;

    private HashMap<String, List<String>> childTitleList = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restore_countries);

        ButterKnife.bind(this);

        DaggerAppComponent.builder()
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .presenterModule(new PresenterModule(this))
                .build()
                .inject(this);

        presenter.getDataFromDatabase(this);

        onCityClickListener();
    }


    @Override
    public void hideProgressBarr() {

        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void showProgressBar() {

        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void onGetDataSuccessful(List<String> countries, List<String> china, List<String> japan,
                                    List<String> thailand, List<String> india, List<String> malaysia) {

        childTitleList.put(countries.get(0), china);
        childTitleList.put(countries.get(1), japan);
        childTitleList.put(countries.get(2), thailand);
        childTitleList.put(countries.get(3), india);
        childTitleList.put(countries.get(4), malaysia);

        countriesAdapter = new CountriesAdapter(countries, childTitleList, this);
        countriesExpandableListView.setAdapter(countriesAdapter);

        countriesAdapter.notifyDataSetChanged();
    }


    @Override
    public void onGetDataFailure(String error) {

        Snackbar.make(parentLayout, error, Snackbar.LENGTH_LONG).show();
    }


    void onCityClickListener() {

        countriesExpandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {

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
