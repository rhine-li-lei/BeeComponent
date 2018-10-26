package com.lilei.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.lang.ref.WeakReference;

/**
 * Created by lilei
 * Date : 2018/9/28
 */

public class BaseApplication extends Application {

    private static WeakReference<Activity> mTopActivity = null;
    private Activity topActivityCopyed = null;
    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                mTopActivity = new WeakReference<Activity>(activity);
                topActivityCopyed = activity;
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                mTopActivity = null;  // help Gc
                topActivityCopyed = null;
            }
        });
    }

    public static WeakReference<Activity> getmTopActivity() {
        return mTopActivity;
    }

}
