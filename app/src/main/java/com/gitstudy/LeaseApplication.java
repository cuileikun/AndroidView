package com.gitstudy;


import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by wbxmz15 on 2017/11/9.
 */

public class LeaseApplication extends Application {
    public static LeaseApplication mInstance;
    public Intent mPushIntent = null;
    private ArrayList<WeakReference<Activity>> mAllActivitys = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
      //  BlockDetectByPrinter.start();
    }
    public void addActivity(Activity activity){
        mAllActivitys.add(new WeakReference<Activity>(activity));
        Log.e("chunhui","-------add");
    }
    //杀死所有的activity
    public void killAllActivitys(){
        int size = mAllActivitys.size();
        mPushIntent = null;
        for(int i = 0;i<size;i++){
            WeakReference<Activity> a = mAllActivitys.get(i);
            if(a != null){
                Activity as = a.get();
                if(as != null){
                    as.finish();
                }
            }
        }
        Log.e("chunhui","-------delete");
        mAllActivitys.clear();
    }
    //移除某个activity
    public void removeActivity(Activity activity){
        int size = mAllActivitys.size();
        for(int i = 0;i<size;i++){
            WeakReference<Activity> a = mAllActivitys.get(i);
            if(a != null){
                if(activity == a.get()){
                    mAllActivitys.remove(i);
                    break;
                };
            }
        }
    }

}
