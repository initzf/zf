package com.md.zhihu.main.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.md.zhihu.R;
import com.md.zhihu.main.base.BaseActivity;
import com.md.zhihu.main.tools.StatusBarCompat;

public class MainActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarCompat.compat(this, getResources().getColor(R.color.colorPrimary));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("无聊");
        setSupportActionBar(toolbar);

        initView();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container);
        recyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
    }
}
