package com.jay.country.contract;

import java.util.List;

public interface CityDetailedContract {

    interface View{

        void showProgressBar();

        void hideProgressBar();

        void onLoadArticleSuccessful(List<String> articleList, List<String> imageUtlList);

        void onLoadArticleFailure(Throwable throwable);
    }


    interface Presenter{

        void loadArticle(String city);

        void onDestroy();
    }


    interface Model{

        interface LoadFeedback{

            void onLoadSuccessful(List<String> articleList, List<String> imageUtlList);

            void onLoadFailure(Throwable throwable);
        }

        void loadArticle(LoadFeedback feedback, String city);
    }
}
