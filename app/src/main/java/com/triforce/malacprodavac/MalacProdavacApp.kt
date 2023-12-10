package com.triforce.malacprodavac

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MalacProdavacApp : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        val notificationChannel = NotificationChannel(
            "malac_prodavac",
            "Malac Prodavac",
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.20)
                    .build()
            }
            .logger(DebugLogger())
            .diskCachePolicy(CachePolicy.DISABLED)
            .respectCacheHeaders(true)
            .build()
    }
}