package com.example.maxiaoxun.mmkvapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.tencent.mmkv.MMKV;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.Map;
import java.util.Set;

/**
 * Author: maxiaoxun@qutoutiao.net
 * Time: 2019/11/19 14:44
 */
public class MmkvUtil {

    private static final String MMKV_PREFERENCES = "qk_app";
    private MMKV mMkv;

    private MmkvUtil() {
        mMkv = MMKV.mmkvWithID(MMKV_PREFERENCES, MMKV.SINGLE_PROCESS_MODE);
    }

    public static MmkvUtil getInstance() {
        return Inner.instance;
    }

    private static class Inner {
        private static final MmkvUtil instance = new MmkvUtil();
    }

    public void sp2mmkv(SharedPreferences sp) {
        this.mMkv.importFromSharedPreferences(sp);
        sp.edit().clear().apply();
    }

    public boolean getBoolean(String key, boolean defaultVal) {
        return this.mMkv.getBoolean(key, defaultVal);
    }

    public boolean getBoolean(String key) {
        return this.mMkv.getBoolean(key, false);
    }

    public String getString(String key, String defaultVal) {
        return this.mMkv.getString(key, defaultVal);
    }

    public String getString(String key) {
        return this.mMkv.getString(key, null);
    }

    public int getInt(String key, int defaultVal) {
        return this.mMkv.getInt(key, defaultVal);
    }

    public int getInt(String key) {
        return this.mMkv.getInt(key, 0);
    }

    public float getFloat(String key, float defaultVal) {
        return this.mMkv.getFloat(key, defaultVal);
    }

    public float getFloat(String key) {
        return this.mMkv.getFloat(key, 0f);
    }

    public long getLong(String key, long defaultVal) {
        return this.mMkv.getLong(key, defaultVal);
    }

    public long getLong(String key) {
        return this.mMkv.getLong(key, 0l);
    }

    public Set<String> getStringSet(String key, Set<String> defaultVal) {
        return this.mMkv.getStringSet(key, defaultVal);
    }

    public Set<String> getStringSet(String key) {
        return this.mMkv.getStringSet(key, null);
    }

    public Map<String, ?> getAll() {
        return this.mMkv.getAll();
    }

    public boolean exists(String key) {
        return mMkv.contains(key);
    }

    public MmkvUtil putString(String key, String value) {
        mMkv.putString(key, value);
        return this;
    }

    public MmkvUtil putInt(String key, int value) {
        mMkv.putInt(key, value);
        return this;
    }

    public MmkvUtil putFloat(String key, float value) {
        mMkv.putFloat(key, value);
        return this;
    }

    public MmkvUtil putLong(String key, long value) {
        mMkv.putLong(key, value);
        return this;
    }

    public MmkvUtil putBoolean(String key, boolean value) {
        mMkv.putBoolean(key, value);
        return this;
    }

    public void commit() {
        mMkv.commit();
    }

    public MmkvUtil putStringSet(String key, Set<String> value) {
        mMkv.putStringSet(key, value);
        return this;
    }

    public void putObject(String key, Object object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(baos);
            out.writeObject(object);
            String objectVal = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            mMkv.putString(key, objectVal);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T getObject(String key, Class<T> clazz) {
        if (mMkv.contains(key)) {
            String objectVal = mMkv.getString(key, null);
            byte[] buffer = Base64.decode(objectVal, Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(bais);
                T t = (T) ois.readObject();
                return t;
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bais != null) {
                        bais.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public MmkvUtil remove(String key) {
        mMkv.remove(key);
        return this;
    }

    public MmkvUtil removeAll() {
        mMkv.clear();
        return this;
    }
}

