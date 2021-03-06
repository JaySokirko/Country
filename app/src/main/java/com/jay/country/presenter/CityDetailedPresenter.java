package com.jay.country.presenter;

import com.jay.country.contract.CityDetailedContract;
import com.jay.country.model.network.api.CityDownloader;

import java.util.List;

import javax.inject.Inject;

public class CityDetailedPresenter implements CityDetailedContract.Presenter,
        CityDetailedContract.Model.LoadFeedback {

    private CityDetailedContract.View view;

    private CityDetailedContract.Model model = new CityDownloader();


    @Inject
    public CityDetailedPresenter(CityDetailedContract.View view) {
        this.view = view;
    }


    @Override
    public void loadArticle(String city) {

        if (view != null){

            view.showProgressBar();

            model.loadArticle(this, city);
        }
    }


    @Override
    public void onDestroy() {

        view = null;
        model = null;
    }


    @Override
    public void onLoadSuccessful(List<String> articleList, List<String> imageUtlList) {

        if (view != null){

            view.onLoadArticleSuccessful(articleList, imageUtlList);
            view.hideProgressBar();
        }
    }


    @Override
    public void onLoadFailure(Throwable throwable) {

        if (view != null){

            view.onLoadArticleFailure(throwable);
            view.hideProgressBar();
        }
    }
}
