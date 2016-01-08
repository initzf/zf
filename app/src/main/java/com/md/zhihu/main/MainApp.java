package com.md.zhihu.main;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.md.dao.DaoMaster;
import com.md.dao.DaoSession;
import com.md.zhihu.main.config.CommConstant;
import com.md.zhihu.main.db.THDevOpenHelper;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * 作者：zf on 2016/1/4 16:49
 * 邮箱：initzf@126.com
 */
public class MainApp extends Application {

    private static MainApp instances;
    private static  DaoMaster daoMaster;
    private static  DaoSession daoSession;
    private static THDevOpenHelper dbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        initImageLoader(getContext());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }


    public static MainApp getContext(){
        return  instances;
    }

    /**
     * 取得DaoMaster
     *
     * @return
     */
    public static DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            dbHelper = new THDevOpenHelper(getContext(), CommConstant.DB_NAME, null);
            daoMaster = new DaoMaster(dbHelper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     *
     * @return
     */
    public static DaoSession getDaoSession() {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

}
