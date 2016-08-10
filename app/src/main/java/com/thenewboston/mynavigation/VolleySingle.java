package com.thenewboston.mynavigation;

import android.graphics.Bitmap;
import android.media.Image;
import android.support.v4.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by monster on 26/1/16.
 */
public class VolleySingle  {
    private static VolleySingle sInstance = null;
    private RequestQueue requestQueue ;
    //Image loader variable
    private ImageLoader imageLoader;

    private VolleySingle(){

        requestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            //LRU CACHE
            private LruCache<String,Bitmap> cache = new LruCache<>((int)Runtime.getRuntime().maxMemory()/1024/8);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url,bitmap);
            }
        });

    }

    public static VolleySingle getInstance(){

        if(sInstance == null){
            sInstance = new VolleySingle();
        }
        return  sInstance;
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }

    //return image loader
    public ImageLoader getImageLoader(){
        return imageLoader;
    }

}
