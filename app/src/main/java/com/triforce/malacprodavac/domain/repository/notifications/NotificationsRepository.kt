package com.triforce.malacprodavac.domain.repository.notifications

import com.triforce.malacprodavac.domain.model.notifications.Notification
import com.triforce.malacprodavac.domain.model.pagination.PaginationResult
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface NotificationsRepository {

    suspend fun getNotifications(
        page: Int,
        perPage: Int
    ): Flow<Resource<PaginationResult<Notification>>>

    suspend fun getNotification(id: Int): Flow<Resource<Notification>>
}