package com.md.zhihu.main.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.md.zhihu.main.tools.AppManager;

/**
 * 作者：zf on 2016/1/6 18:13
 * 邮箱：initzf@126.com
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppManager.getAppManager().addActivity(this);
        //noinspection ResourceType
    }
}
