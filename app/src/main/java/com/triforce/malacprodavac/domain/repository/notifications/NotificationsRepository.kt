package com.triforce.malacprodavac.domain.repository.notifications

import com.triforce.malacprodavac.domain.model.notifications.Notification
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface NotificationsRepository {
    suspend fun subscribe(): Flow<Resource<String>>

    suspend fun getNotifications(): Flow<Resource<List<Notification>>>

    suspend fun getNotification(id: Int): Flow<Resource<Notification>>
}