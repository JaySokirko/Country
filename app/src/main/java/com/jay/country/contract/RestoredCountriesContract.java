package com.jay.country.contract;

import android.content.Context;

import java.util.List;

public interface RestoredCountriesContract {

    interface View {

        void hideProgressBarr();

        void showProgressBar();

        void onGetDataSuccessful( List<String> countries, List<String> china, List<String> japan,
                                  List<String> thailand, List<String> india, List<String> malaysia);

        void onGetDataFailure(String error);
    }



    interface Presenter {

        void getDataFromDatabase(Context context);

        void onDestroy();

    }



    interface Model {

        interface DatabaseTransaction {

            void insertIntoDataBase(Context context, DatabaseInsertFeedback feedback,
                                    List<String> countries, List<String> china,
                                    List<String> japan, List<String> thailand, List<String> india,
                                    List<String> malaysia);

            void restoreFromDataBase(Context context, DatabaseRestoreFeedback feedback);

            interface DatabaseInsertFeedback {

                void onSaveIntoDataBaseFinish();

                void onFailureSaveIntoDatabase(Throwable throwable);
            }


            interface DatabaseRestoreFeedback {

                void onRestoreFromDatabaseSuccessful(List<String> countries, List<String> china,
                                                     List<String> japan, List<String> thailand,
                                                     List<String> india, List<String> malaysia);

                void onRestoreFromDatabaseFailure(String error);
            }
        }
    }
}
