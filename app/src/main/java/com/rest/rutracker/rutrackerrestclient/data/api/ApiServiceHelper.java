package com.rest.rutracker.rutrackerrestclient.data.api;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.os.ResultReceiver;

import com.rest.rutracker.rutrackerrestclient.AppController;
import com.rest.rutracker.rutrackerrestclient.data.api.request.DataRequest;
import com.rest.rutracker.rutrackerrestclient.data.api.request.DeleteDataRequest;

import static com.rest.rutracker.rutrackerrestclient.data.api.ApiService.*;

public class ApiServiceHelper {

    private ApiServiceHelper() {
        super();
    }

    public static void getCategories(ResultReceiver onServiceResult) {
        startService(null, ACTION_GET_CATEGORIES, onServiceResult);
    }

    public static void getArticles(ResultReceiver onServiceResult) {
        startService(null, ACTION_GET_ARTICLES, onServiceResult);
    }

    public static void addArticle(DataRequest data, ResultReceiver onServiceResult) {
        startService(data, ACTION_ADD_ARTICLE, onServiceResult);
    }

    public static void editArticle(DataRequest data, ResultReceiver onServiceResult) {
        startService(data, ACTION_EDIT_ARTICLE, onServiceResult);
    }

    public static void deleteArticle(DeleteDataRequest data, ResultReceiver onServiceResult) {
        startService(data, ACTION_DELETE_ARTICLE, onServiceResult);
    }

    private static void startService(Parcelable data, int action, ResultReceiver onServiceResult) {
        Intent intent = getIntent(action, onServiceResult);
        if (data != null) {
            intent.putExtra(REQUEST_OBJECT_KEY, data);
        }
        getContext().startService(intent);
    }

    private static Intent getIntent(int action, ResultReceiver onServiceResult) {
        final Intent i = new Intent(getContext(), ApiService.class);
        i.putExtra(ACTION_KEY, action);
        i.putExtra(CALLBACK_KEY, onServiceResult);
        return i;
    }

    private static Context getContext() {
        return AppController.getAppContext();
    }

    public static void getTorrentFeed(ResultReceiver resultReceiver) {
        startService(null, ACTION_GET_OUR_MOVIE_RSS_FEED, resultReceiver);
    }
}
