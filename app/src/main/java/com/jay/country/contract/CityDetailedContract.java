package com.jay.country.contract;

public interface CityDetailedContract {

    interface View{

        void showProgressBar();

        void hideProgressBar();

        void onLoadArticleSuccessful(String article);

        void onLoadArticleFailure(Throwable throwable);
    }


    interface Presenter{

        void loadArticle(String city);

        void onDestroy();
    }


    interface Model{

        interface LoadFeedback{

            void onLoadSuccessful(String article);

            void onLoadFailure(Throwable throwable);
        }

        void loadArticle(LoadFeedback feedback, String city);
    }
}
