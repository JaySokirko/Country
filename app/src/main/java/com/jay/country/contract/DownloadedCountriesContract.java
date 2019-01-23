package com.jay.country.contract;

import android.content.Context;

import java.util.List;

public interface DownloadedCountriesContract {

    interface View {

        void showProgressBar();

        void hideProgressBar();

        void downloadSuccessful(List<String> countries, List<String> china, List<String> japan,
                                List<String> thailand, List<String> india, List<String> malaysia);

        void downloadFailure(Throwable throwable);

        void successfulSaveIntoDatabase();

        void failureSaveIntoDatabase(Throwable throwable);
    }


    interface Presenter {

        void downloadCountriesList();

        void saveIntoDatabase(Context context, List<String> countries, List<String> china, List<String> japan,
                              List<String> thailand, List<String> india, List<String> malaysia);

        void onDestroy();
    }


    interface Model {

        interface DownloadFeedback {

            void onDownloadSuccessful(List<String> countries, List<String> china, List<String> japan,
                                      List<String> thailand, List<String> india, List<String> malaysia);

            void onDownloadFailure(Throwable throwable);
        }

        void startDownloadCountriesList(DownloadFeedback feedback);
    }
}
