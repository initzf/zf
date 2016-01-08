package com.md.zhihu.main.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.md.bean.CacheBean;
import com.md.zhihu.R;
import com.md.zhihu.main.base.BaseActivity;
import com.md.zhihu.main.bean.SplashEntity;
import com.md.zhihu.main.config.Constant;
import com.md.zhihu.main.db.CacheDao;
import com.md.zhihu.main.tools.DebugLog;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;

/**
 * 作者：zf on 2016/1/6 18:13
 * 邮箱：initzf@126.com
 */
public class SplashActivity extends BaseActivity {
    private final String TAG = SplashActivity.class.getSimpleName();

    private ImageView imgSplash;
    private TextView tvCopyright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // 设置全屏
        imgSplash = (ImageView) findViewById(R.id.imgSplash);
        tvCopyright = (TextView) findViewById(R.id.tvCopyright);

        new Handler().postDelayed(goToMainActivity, 3000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSplashImage();
    }

    Runnable goToMainActivity = new Runnable() {
        @Override
        public void run() {
            startActivity();
        }
    };


    private void getSplashImage() {
        final File dir = getFilesDir();
        DebugLog.i(TAG + "----" + dir.getAbsolutePath());
        final File imgFile = new File(dir, "start.jpg");
        if (imgFile.exists()) {
            imgSplash.setImageBitmap(BitmapFactory.decodeFile(imgFile.getAbsolutePath()));
        } else {
            imgSplash.setImageResource(R.mipmap.start);
        }
        final ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnim.setFillAfter(true);
        scaleAnim.setDuration(3000);
        imgSplash.startAnimation(scaleAnim);

        scaleAnim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                final String url = Constant.SPLASH_IMAGE;
                OkHttpUtils.get().url(url).build().execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        CacheBean bean = CacheDao.getIntstance().getCacheBeanByUrl(url);
                        if (bean != null) {
                            parseLatestJson(bean.getJson());
                        }
                    }

                    @Override
                    public void onResponse(String response) {
                        DebugLog.i(TAG + response);
                        CacheDao.getIntstance().replaceAdd(new CacheBean(url, response));
                        parseLatestJson(response);
                        SplashEntity entity = JSON.parseObject(response, SplashEntity.class);
                        OkHttpUtils.get().url(entity.getImg()).build().execute(new FileCallBack(dir.getAbsolutePath(), "start.jpg") {
                            @Override
                            public void inProgress(float progress) {
                            }

                            @Override
                            public void onError(Request request, Exception e) {
                            }

                            @Override
                            public void onResponse(File response) {
                                response.getAbsolutePath();
                            }
                        });
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void parseLatestJson(String json) {
        SplashEntity entity = JSON.parseObject(json, SplashEntity.class);
        tvCopyright.setText(entity.getText());
        tvCopyright.bringToFront();
    }

    private void startActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();

        /*Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        intent.putExtra(Constant.START_LOCATION, startingLocation);
        intent.putExtra("entity", storiesEntity);
        intent.putExtra("isLight", ((MainActivity) mActivity).isLight());
        startActivity(intent);
        mActivity.overridePendingTransition(0, 0);*/

    }
}
