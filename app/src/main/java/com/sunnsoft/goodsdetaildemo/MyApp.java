package com.sunnsoft.goodsdetaildemo;

import android.app.Application;

/**
 * Created by Administrator on 2019-03-08.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Global.init(this);
    }
}
