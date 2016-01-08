package com.md.zhihu.main.db;

import com.md.bean.CacheBean;
import com.md.dao.CacheBeanDao;
import com.md.zhihu.main.MainApp;
import com.md.zhihu.main.tools.StringUtil;

import java.util.List;

/**
 * 作者：zf on 2016/1/7 11:28
 * 邮箱：initzf@126.com
 */
public class CacheDao {
    private static CacheDao instance;
    private CacheBeanDao dao;

    private CacheDao() {
        dao = MainApp.getDaoSession().getCacheBeanDao();
    }

    public static CacheDao getIntstance() {
        if (null == instance) {
            synchronized (CacheDao.class) {
                if (instance == null) {
                    instance = new CacheDao();
                }
            }
        }
        return instance;
    }

    public CacheBean getCacheBeanByUrl(String url) {
        if (!StringUtil.isNullOrEmpty(url)) {
            return dao.load(url);
        }
        return null;
    }

    public void replaceAdd(CacheBean bean){
        if(bean==null){
            return;
        }
        dao.insertOrReplace(bean);
    }


    public List<CacheBean> getAll() {
        return dao.loadAll();
    }
}
