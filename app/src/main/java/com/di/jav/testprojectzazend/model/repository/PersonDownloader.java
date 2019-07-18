package com.di.jav.testprojectzazend.model.repository;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.di.jav.testprojectzazend.activity.personslist.PersonsListFragment;
import com.di.jav.testprojectzazend.model.entity.Result;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PersonDownloader<T> extends HandlerThread {
    private static final String TAG = "PersonDownloader";
    private static final int MESSAGE_DOWNLOAD = 0;

    private boolean mHasQuit = false;
    private Handler mRequestHandler;
    private ConcurrentMap<T, String> mRequestMap = new ConcurrentHashMap<>();

    public PersonDownloader() {
        super(TAG);
    }

    @Override
    protected void onLooperPrepared() {
        mRequestHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == MESSAGE_DOWNLOAD) {
                    T target = (T) msg.obj;
                    handleRequest(target);
                }
            }
        };
    }

    @Override
    public boolean quit() {
        mHasQuit = true;
        return super.quit();
    }

    public void queuePerson(T target, String url) {
        Log.i(TAG, "Got a URL: " + url);

        if(url == null) {
            mRequestMap.remove(target);
        }else {
            mRequestMap.put(target, url);
            mRequestHandler.obtainMessage(MESSAGE_DOWNLOAD, target).sendToTarget();
        }
    }

    private void handleRequest(final T target){
        final String url = mRequestMap.get(target);

        if(url == null){
            return;
        }

//        Result result = new Result();
//        PersonsListFragment listFragment = new PersonsListFragment();
//        listFragment.getPeople(result);
    }
}
