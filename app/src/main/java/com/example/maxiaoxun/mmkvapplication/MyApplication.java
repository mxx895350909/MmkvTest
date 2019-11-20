package com.example.maxiaoxun.mmkvapplication;

import android.app.Application;

/**
 * Author: maxiaoxun@qutoutiao.net
 * Time: 2019/11/20 17:58
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MmkvUtil.init(getApplicationContext());
    }
}
