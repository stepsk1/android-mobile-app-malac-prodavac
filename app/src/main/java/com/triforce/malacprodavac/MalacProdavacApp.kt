package com.triforce.malacprodavac

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import com.launchdarkly.eventsource.MessageEvent
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.triforce.malacprodavac.domain.repository.notifications.NotificationsSSEClient
import com.triforce.malacprodavac.domain.repository.sse.SSEHandler
import com.triforce.malacprodavac.domain.util.enum.MessageEventType
import com.triforce.malacprodavac.presentation.notifications.NotificationDataType
import com.triforce.malacprodavac.presentation.notifications.NotificationsViewModel
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltAndroidApp
class MalacProdavacApp : Application(), ImageLoaderFactory, SSEHandler {
    @Inject
    lateinit var notificationsSSEClient: NotificationsSSEClient

    @Inject
    lateinit var notificationBuilder: NotificationCompat.Builder

    @Inject
    lateinit var notificationManager: NotificationManagerCompat

    @Inject
    lateinit var coroutineScope: CoroutineScope

    @Inject
    lateinit var moshi: Moshi

    override fun onCreate() {
        super.onCreate()
        coroutineScope.launch {
            notificationsSSEClient.initSse(this@MalacProdavacApp) { error ->
                error.printStackTrace()
            }
        }
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

    @SuppressLint("MissingPermission")
    fun showSimpleNotification(title: String, text: String) {
        notificationManager.notify(
            Random.nextInt(),
            notificationBuilder
                .setContentTitle(title)
                .setContentText(text)
                .setAutoCancel(true)
                .build()
        )
    }

    override fun onSSEConnectionOpened() {
        Log.i(NotificationsViewModel::class.simpleName, "CONN OPEN")
    }

    override fun onSSEConnectionClosed() {
        Log.i(NotificationsViewModel::class.simpleName, "CONN CLOSED")
    }

    @OptIn(ExperimentalStdlibApi::class)
    override fun onSSEEventReceived(event: String, messageEvent: MessageEvent) {
        Log.i(
            NotificationsViewModel::class.simpleName,
            "EVENT NAME - $event : DATA - ${messageEvent.data}"
        )

        val trimmedData = messageEvent.data.replace("\"", "")
        when (MessageEventType.from(event)) {
            MessageEventType.CourierInAreaForCustomer -> {
                val adapter = moshi.adapter<NotificationDataType.CourierInAreaForCustomer>()
                val result = adapter.fromJson(messageEvent.data)
                showSimpleNotification(
                    result?.title ?: "",
                    result?.shops ?: ""
                )
            }

            MessageEventType.CourierInAreaForShop -> {
                val adapter = moshi.adapter<NotificationDataType.CourierInAreaForShop>()
                val result = adapter.fromJson(messageEvent.data)
                showSimpleNotification(
                    result?.text ?: "",
                    result?.title ?: ""
                )
            }

            MessageEventType.NewProductFromFavoriteShop -> {
                val adapter = moshi.adapter<NotificationDataType.NewProductFromFavoriteShop>()
                val result = adapter.fromJson(messageEvent.data)
                showSimpleNotification(
                    result?.title ?: "",
                    result?.product ?: ""
                )
            }

            MessageEventType.ProductsAvailableAtNewLocation -> {
                val adapter = moshi.adapter<NotificationDataType.ProductsAvailableAtNewLocation>()
                val result = adapter.fromJson(messageEvent.data)
                showSimpleNotification(
                    result?.title ?: "",
                    result?.location ?: ""
                )
            }

            MessageEventType.PickupScheduled -> {
                val adapter = moshi.adapter<NotificationDataType.PickupScheduled>()
                val result = adapter.fromJson(messageEvent.data)
                showSimpleNotification(
                    result?.text ?: "",
                    result?.title ?: ""
                )
            }
        }

    }

    override fun onSSEError(t: Throwable) {
        t.printStackTrace()
    }
}