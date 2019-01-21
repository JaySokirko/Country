package com.jay.country.contract;

import java.util.List;

public interface DownloadedCountriesContract {

     interface View {

         void showProgressBar();

         void hideProgressBar();

         void downloadSuccessful(List<String> china, List<String> japan, List<String> thailand,
                               List<String> india, List<String> malaysia);

         void downloadFailure(Throwable throwable);
     }


     interface Presenter{

         void downloadCountriesList();

         void onCitySelected(String city);

         void onDestroy();
     }


     interface Model{

         interface DownloadFeedback {

             void onDownloadSuccessful(List<String> china, List<String> japan, List<String> thailand,
                                     List<String> india, List<String> malaysia);

             void onDownloadFailure(Throwable throwable);
         }

         void startDownloadCountriesList(DownloadFeedback feedback);
     }
}
