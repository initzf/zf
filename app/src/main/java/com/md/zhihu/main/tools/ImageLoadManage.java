package com.md.zhihu.main.tools;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

/**
 * 作者：zf on 2016/1/6 17:49
 * 邮箱：initzf@126.com
 */
public class ImageLoadManage {
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;

    public ImageLoadManage() {
        options = null;
    }

    public void imageLoading(String url, ImageView imageView, int imgOnLoading, int imgEmty, int imgFail) {
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(imgOnLoading)
                .showImageForEmptyUri(imgFail)
                .showImageOnFail(imgOnLoading)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(new SimpleBitmapDisplayer())
                .build();
        imageLoader.displayImage(url, imageView, options);
    }


    public void imageLoading(String url, ImageView imageView, int imgFail) {
        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(imgFail)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(new SimpleBitmapDisplayer())
                .build();
        imageLoader.displayImage(url, imageView, options);
    }




    public void clear(){
        if(null!=imageLoader){
            imageLoader.clearDiskCache();
            imageLoader.clearMemoryCache();
        }
    }

}
