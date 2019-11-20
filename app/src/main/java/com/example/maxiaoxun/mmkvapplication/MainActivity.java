package com.example.maxiaoxun.mmkvapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    private static final int TEST_TIMES = 10000;
    private static final String TEST_KEY_STRING = "test_key_string";
    private static final String TEST_VALUE_STRING = "test_value_string";
    private static final String TEST_KEY_BOOLEAN = "test_key_boolean";
    private static final Boolean TEST_VALUE_BOOLEAN = true;
    private static final String TEST_KEY_INT = "test_key_int";
    private static final int TEST_VALUE_INT = 123456;
    private static final String TEST_KEY_FLOAT = "test_key_float";
    private static final Float TEST_VALUE_FLOAT = 12345.6F;
    private static final String TEST_KEY_LONG = "test_key_long";
    private static final int TEST_VALUE_LONG = 1234567891;
    private static final String TEST_KEY_OBJECT = "test_key_object";
    private static final MemberInfo TEST_VALUE_OBJECT = new MemberInfo();


    private TextView tvTitle;
    private Button btnString;
    private Button btnBoolean;
    private Button btnInt;
    private Button btnFloat;
    private Button btnLong;
    private Button btnObject;
    private TextView tvPutString;
    private TextView tvGetString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();
    }

    private void initView() {
        tvTitle = findViewById(R.id.tv_title);
        tvPutString = findViewById(R.id.tv_put);
        tvGetString = findViewById(R.id.tv_get);
        btnString = findViewById(R.id.btn_string);
        btnBoolean = findViewById(R.id.btn_boolean);
        btnInt = findViewById(R.id.btn_int);
        btnFloat = findViewById(R.id.btn_float);
        btnLong = findViewById(R.id.btn_long);
        btnObject = findViewById(R.id.btn_object);

        tvTitle.setText("Mmkv与SP执行" + TEST_TIMES + "次耗时对比");

        TEST_VALUE_OBJECT.name = "maxiaoxun";
        TEST_VALUE_OBJECT.age = 20;
        TEST_VALUE_OBJECT.sex = true;
    }

    private void setListener() {
        btnString.setOnClickListener(this);
        btnBoolean.setOnClickListener(this);
        btnInt.setOnClickListener(this);
        btnFloat.setOnClickListener(this);
        btnLong.setOnClickListener(this);
        btnObject.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_string) {
            putStringClick();
            getStringClick();
        } else if (id == R.id.btn_boolean) {
            putBooleanClick();
            getBooleanClick();
        } else if (id == R.id.btn_int) {
            putIntClick();
            getIntClick();
        } else if (id == R.id.btn_float) {
            putFloatClick();
            getFloatClick();
        } else if (id == R.id.btn_long) {
            putLongClick();
            getLongClick();
        } else if (id == R.id.btn_object) {
            putObjectClick();
            getObjectClick();
        }

    }

    private void putStringClick() {
        long mmkvStartTime = SystemClock.elapsedRealtime();
        for (int i = 0; i < TEST_TIMES; i++) {
            MmkvUtil.getInstance().putString(TEST_KEY_STRING, TEST_VALUE_STRING);
        }
        long mmkvUseTime = SystemClock.elapsedRealtime() - mmkvStartTime;

        long spStartTime = SystemClock.elapsedRealtime();
        for (int i = 0; i < TEST_TIMES; i++) {
            PreferenceUtil.putString(this, TEST_KEY_STRING, TEST_VALUE_STRING);
        }
        long spUseTime = SystemClock.elapsedRealtime() - spStartTime;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("putString Mmkv耗时：");
        stringBuilder.append(mmkvUseTime);
        stringBuilder.append(" , ");
        stringBuilder.append("SP耗时：");
        stringBuilder.append(spUseTime);
        tvPutString.setText(stringBuilder.toString());
    }

    private void getStringClick() {
        long mmkvStartTime = SystemClock.elapsedRealtime();
        for (int i = 0; i < TEST_TIMES; i++) {
            MmkvUtil.getInstance().getString(TEST_KEY_STRING, TEST_VALUE_STRING);
        }
        long mmkvUseTime = SystemClock.elapsedRealtime() - mmkvStartTime;

        long spStartTime = SystemClock.elapsedRealtime();
        for (int i = 0; i < TEST_TIMES; i++) {
            PreferenceUtil.getString(this, TEST_KEY_STRING, TEST_VALUE_STRING);
        }
        long spUseTime = SystemClock.elapsedRealtime() - spStartTime;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getString Mmkv耗时：");
        stringBuilder.append(mmkvUseTime);
        stringBuilder.append(" , ");
        stringBuilder.append("SP耗时：");
        stringBuilder.append(spUseTime);
        tvGetString.setText(stringBuilder.toString());
    }

    private void putBooleanClick() {
        long mmkvStartTime = SystemClock.elapsedRealtime();
        for (int i = 0; i < TEST_TIMES; i++) {
            MmkvUtil.getInstance().putBoolean(TEST_KEY_BOOLEAN, TEST_VALUE_BOOLEAN);
        }
        long mmkvUseTime = SystemClock.elapsedRealtime() - mmkvStartTime;

        long spStartTime = SystemClock.elapsedRealtime();
        for (int i = 0; i < TEST_TIMES; i++) {
            PreferenceUtil.putBoolean(this, TEST_KEY_BOOLEAN, TEST_VALUE_BOOLEAN);
        }
        long spUseTime = SystemClock.elapsedRealtime() - spStartTime;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("putBoolean Mmkv耗时：");
        stringBuilder.append(mmkvUseTime);
        stringBuilder.append(" , ");
        stringBuilder.append("SP耗时：");
        stringBuilder.append(spUseTime);
        tvPutString.setText(stringBuilder.toString());
    }

    private void getBooleanClick() {
        long mmkvStartTime = SystemClock.elapsedRealtime();
        for (int i = 0; i < TEST_TIMES; i++) {
            MmkvUtil.getInstance().getBoolean(TEST_KEY_BOOLEAN, TEST_VALUE_BOOLEAN);
        }
        long mmkvUseTime = SystemClock.elapsedRealtime() - mmkvStartTime;

        long spStartTime = SystemClock.elapsedRealtime();
        for (int i = 0; i < TEST_TIMES; i++) {
            PreferenceUtil.getBoolean(this, TEST_KEY_BOOLEAN, TEST_VALUE_BOOLEAN);
        }
        long spUseTime = SystemClock.elapsedRealtime() - spStartTime;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getBoolean Mmkv耗时：");
        stringBuilder.append(mmkvUseTime);
        stringBuilder.append(" , ");
        stringBuilder.append("SP耗时：");
        stringBuilder.append(spUseTime);
        tvGetString.setText(stringBuilder.toString());
    }

    private void putIntClick() {
        long mmkvStartTime = SystemClock.elapsedRealtime();
        for (int i = 0; i < TEST_TIMES; i++) {
            MmkvUtil.getInstance().putInt(TEST_KEY_INT, TEST_VALUE_INT);
        }
        long mmkvUseTime = SystemClock.elapsedRealtime() - mmkvStartTime;

        long spStartTime = SystemClock.elapsedRealtime();
        for (int i = 0; i < TEST_TIMES; i++) {
            PreferenceUtil.putInt(this, TEST_KEY_INT, TEST_VALUE_INT);
        }
        long spUseTime = SystemClock.elapsedRealtime() - spStartTime;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("putInt Mmkv耗时：");
        stringBuilder.append(mmkvUseTime);
        stringBuilder.append(" , ");
        stringBuilder.append("SP耗时：");
        stringBuilder.append(spUseTime);
        tvPutString.setText(stringBuilder.toString());
    }

    private void getIntClick() {
        long mmkvStartTime = SystemClock.elapsedRealtime();
        for (int i = 0; i < TEST_TIMES; i++) {
            MmkvUtil.getInstance().getInt(TEST_KEY_INT, TEST_VALUE_INT);
        }
        long mmkvUseTime = SystemClock.elapsedRealtime() - mmkvStartTime;

        long spStartTime = SystemClock.elapsedRealtime();
        for (int i = 0; i < TEST_TIMES; i++) {
            PreferenceUtil.getInt(this, TEST_KEY_INT, TEST_VALUE_INT);
        }
        long spUseTime = SystemClock.elapsedRealtime() - spStartTime;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getInt Mmkv耗时：");
        stringBuilder.append(mmkvUseTime);
        stringBuilder.append(" , ");
        stringBuilder.append("SP耗时：");
        stringBuilder.append(spUseTime);
        tvGetString.setText(stringBuilder.toString());
    }

    private void putFloatClick() {
        long mmkvStartTime = SystemClock.elapsedRealtime();
        for (int i = 0; i < TEST_TIMES; i++) {
            MmkvUtil.getInstance().putFloat(TEST_KEY_FLOAT, TEST_VALUE_FLOAT);
        }
        long mmkvUseTime = SystemClock.elapsedRealtime() - mmkvStartTime;

        long spStartTime = SystemClock.elapsedRealtime();
        for (int i = 0; i < TEST_TIMES; i++) {
            PreferenceUtil.putFloat(this, TEST_KEY_FLOAT, TEST_VALUE_FLOAT);
        }
        long spUseTime = SystemClock.elapsedRealtime() - spStartTime;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("putFloat Mmkv耗时：");
        stringBuilder.append(mmkvUseTime);
        stringBuilder.append(" , ");
        stringBuilder.append("SP耗时：");
        stringBuilder.append(spUseTime);
        tvPutString.setText(stringBuilder.toString());
    }

    private void getFloatClick() {
        long mmkvStartTime = SystemClock.elapsedRealtime();
        for (int i = 0; i < TEST_TIMES; i++) {
            MmkvUtil.getInstance().getFloat(TEST_KEY_FLOAT, TEST_VALUE_FLOAT);
        }
        long mmkvUseTime = SystemClock.elapsedRealtime() - mmkvStartTime;

        long spStartTime = SystemClock.elapsedRealtime();
        for (int i = 0; i < TEST_TIMES; i++) {
            PreferenceUtil.getFloat(this, TEST_KEY_FLOAT, TEST_VALUE_FLOAT);
        }
        long spUseTime = SystemClock.elapsedRealtime() - spStartTime;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getFloat Mmkv耗时：");
        stringBuilder.append(mmkvUseTime);
        stringBuilder.append(" , ");
        stringBuilder.append("SP耗时：");
        stringBuilder.append(spUseTime);
        tvGetString.setText(stringBuilder.toString());
    }

    private void putLongClick() {
        long mmkvStartTime = SystemClock.elapsedRealtime();
        for (int i = 0; i < TEST_TIMES; i++) {
            MmkvUtil.getInstance().putLong(TEST_KEY_LONG, TEST_VALUE_LONG);
        }
        long mmkvUseTime = SystemClock.elapsedRealtime() - mmkvStartTime;

        long spStartTime = SystemClock.elapsedRealtime();
        for (int i = 0; i < TEST_TIMES; i++) {
            PreferenceUtil.putLong(this, TEST_KEY_LONG, TEST_VALUE_LONG);
        }
        long spUseTime = SystemClock.elapsedRealtime() - spStartTime;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("putLong Mmkv耗时：");
        stringBuilder.append(mmkvUseTime);
        stringBuilder.append(" , ");
        stringBuilder.append("SP耗时：");
        stringBuilder.append(spUseTime);
        tvPutString.setText(stringBuilder.toString());
    }

    private void getLongClick() {
        long mmkvStartTime = SystemClock.elapsedRealtime();
        for (int i = 0; i < TEST_TIMES; i++) {
            MmkvUtil.getInstance().getLong(TEST_KEY_LONG, TEST_VALUE_LONG);
        }
        long mmkvUseTime = SystemClock.elapsedRealtime() - mmkvStartTime;

        long spStartTime = SystemClock.elapsedRealtime();
        for (int i = 0; i < TEST_TIMES; i++) {
            PreferenceUtil.getLong(this, TEST_KEY_LONG, TEST_VALUE_LONG);
        }
        long spUseTime = SystemClock.elapsedRealtime() - spStartTime;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getLong Mmkv耗时：");
        stringBuilder.append(mmkvUseTime);
        stringBuilder.append(" , ");
        stringBuilder.append("SP耗时：");
        stringBuilder.append(spUseTime);
        tvGetString.setText(stringBuilder.toString());
    }

    private void putObjectClick() {
        long mmkvStartTime = SystemClock.elapsedRealtime();
        for (int i = 0; i < TEST_TIMES; i++) {
            MmkvUtil.getInstance().putObject(TEST_KEY_OBJECT, TEST_VALUE_OBJECT);
        }
        long mmkvUseTime = SystemClock.elapsedRealtime() - mmkvStartTime;

        long spStartTime = SystemClock.elapsedRealtime();
        for (int i = 0; i < TEST_TIMES; i++) {
            PreferenceUtil.setParam(this, TEST_KEY_OBJECT, TEST_VALUE_OBJECT);
        }
        long spUseTime = SystemClock.elapsedRealtime() - spStartTime;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("putObject Mmkv耗时：");
        stringBuilder.append(mmkvUseTime);
        stringBuilder.append(" , ");
        stringBuilder.append("SP耗时：");
        stringBuilder.append(spUseTime);
        tvPutString.setText(stringBuilder.toString());
    }

    private void getObjectClick() {
        long mmkvStartTime = SystemClock.elapsedRealtime();
        for (int i = 0; i < TEST_TIMES; i++) {
            MmkvUtil.getInstance().getObject(TEST_KEY_OBJECT, TEST_VALUE_OBJECT.getClass());
        }
        long mmkvUseTime = SystemClock.elapsedRealtime() - mmkvStartTime;

        long spStartTime = SystemClock.elapsedRealtime();
        for (int i = 0; i < TEST_TIMES; i++) {
            PreferenceUtil.getParam(this, TEST_KEY_OBJECT, TEST_VALUE_OBJECT);
        }
        long spUseTime = SystemClock.elapsedRealtime() - spStartTime;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getObject Mmkv耗时：");
        stringBuilder.append(mmkvUseTime);
        stringBuilder.append(" , ");
        stringBuilder.append("SP耗时：");
        stringBuilder.append(spUseTime);
        tvGetString.setText(stringBuilder.toString());
    }
}
