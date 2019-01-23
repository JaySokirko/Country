package com.jay.country.contract;

import android.content.Context;

import java.util.List;

public interface RestoredCountriesContract {

    interface View {

    }


    interface Presenter {

    }


    interface Model {

        interface DatabaseTransactor {

            void insertIntoDataBase(Context context, DataBaseInferFeedback feedback,
                                    List<String> countries, List<String> china,
                                    List<String> japan, List<String> thailand, List<String> india,
                                    List<String> malaysia);

            void restoreFromDataBase();


            interface DataBaseInferFeedback {

                void onSaveIntoDataBaseFinish();

                void onFailureSaveIntoDatabase(Throwable throwable);
            }


            interface DataBaseRestoreFeedback {

                void onRestoreFromDatabaseSuccessful(Context context,
                                                     List<String> countries, List<String> china,
                                                     List<String> japan, List<String> thailand,
                                                     List<String> india, List<String> malaysia);
            }
        }
    }
}
