package com.ctc.android.widget;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

/**
 * This class helps caching images for faster loading on second activity open.
 * May also be used at startup to load all images into memory.
 * Created by fess on 2/6/14.
 *
 * The implementation is taken from the official manual:
 * @link http://developer.android.com/training/displaying-bitmaps/cache-bitmap.html
 *
 * TODO: implement asynchronous image loading.
 * TODO: configurable cache size.
 *
 */
public class BitmapHelper
{
	private LruCache<String, Bitmap> mMemoryCache;

	public static BitmapHelper instance;

	public static BitmapHelper getInstance()
	{
		if (null == instance)
		{
			instance = new BitmapHelper();
		}
		return instance;
	}

	private BitmapHelper()
	{
		//trying to implement image caching
		// Get max available VM memory, exceeding this amount will throw an
		// OutOfMemory exception. Stored in kilobytes as LruCache takes an
		// int in its constructor.
		final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

		// Use 1/2nd of the available memory for this memory cache.
		final int cacheSize = maxMemory / 2;

		mMemoryCache = new LruCache<String, Bitmap>(cacheSize)
		{
			@Override
			protected int sizeOf(String key, Bitmap bitmap)
			{
				// The cache size will be measured in kilobytes rather than
				// number of items.
				return bitmap.getRowBytes()*bitmap.getHeight() / 1024;
			}
		};
	}

	public void addBitmapToMemoryCache(String key, Bitmap bitmap)
	{
		if (getBitmapFromMemCache(key) == null)
		{
			Log.e("Bitmap Helper", "Putting bitmap to cache for key: " + key);
			mMemoryCache.put(key, bitmap);
		}
	}

	public Bitmap getBitmapFromMemCache(String key)
	{
		Log.e("Bitmap Helper", "Loading bitmap from cache for key: " + key);
		return mMemoryCache.get(key);
	}
}
