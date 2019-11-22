package com.example.maxiaoxun.mmkvapplication;

import android.app.Application;

import com.getkeepsafe.relinker.ReLinker;
import com.tencent.mmkv.MMKV;
import com.tencent.mmkv.MMKVLogLevel;

/**
 * Author: maxiaoxun@qutoutiao.net
 * Time: 2019/11/20 17:58
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化MMKV
        String root = getFilesDir().getAbsolutePath().concat("/mmkv");
        MMKV.initialize(root, new MMKV.LibLoader() {

            @Override
            public void loadLibrary(String libName) {
                ReLinker.loadLibrary(getApplicationContext(), libName);
            }
        });
        MMKV.setLogLevel(MMKVLogLevel.LevelNone);
    }
}
