package com.triforce.malacprodavac.data.repository.notifications

import com.triforce.malacprodavac.data.local.MalacProdavacDatabase
import com.triforce.malacprodavac.data.local.notifications.relations.NotificationPayloadWithRelations
import com.triforce.malacprodavac.data.mappers.notifications.toNotificationPayloadWithRelations
import com.triforce.malacprodavac.data.remote.notifications.NotificationsApi
import com.triforce.malacprodavac.data.services.SessionManager
import com.triforce.malacprodavac.data.services.filter.Filter
import com.triforce.malacprodavac.data.services.filter.FilterBuilder
import com.triforce.malacprodavac.data.services.filter.FilterOrder
import com.triforce.malacprodavac.data.services.filter.SingleOrder
import com.triforce.malacprodavac.domain.model.notifications.Notification
import com.triforce.malacprodavac.domain.model.pagination.PaginationResult
import com.triforce.malacprodavac.domain.repository.notifications.NotificationsRepository
import com.triforce.malacprodavac.domain.util.Resource
import com.triforce.malacprodavac.domain.util.helpers.calculateLimitAndOffset
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationsRepositoryImpl @Inject constructor(
    private val api: NotificationsApi,
    private val db: MalacProdavacDatabase,
    private val sessionManager: SessionManager
) : NotificationsRepository {
    private val notificationPayloadDao = db.notificationPayloadDao
    private val notificationDao = db.notificationDao

    override suspend fun getNotifications(
        page: Int,
        perPage: Int
    ): Flow<Resource<PaginationResult<Notification>>> {
        return flow {
            emit(Resource.Loading(true))
            val authUserId = sessionManager.getAuthUserId()
//            val notifications =
//                notificationPayloadDao.findNotificationPayloadsWithRelations(authUserId)

//            emit(Resource.Success(data = notifications.map { it.toNotification() }))
            val (limit, offset) = calculateLimitAndOffset(page, perPage)
            val filters = FilterBuilder.buildFilterQueryMap(
                Filter(
                    null,
                    listOf(SingleOrder("createdAt", FilterOrder.Desc)),
                    offset,
                    limit
                )
            )
            val remoteNotificationsResp = try {
                api.getNotifications(filters)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't find user."))
                null
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't find user."))
                null
            }

            remoteNotificationsResp?.let {
                it.data.forEach { notification ->
                    insertNotificationPayloadWithRelations(notification.toNotificationPayloadWithRelations())
                }
                emit(Resource.Success(data = it))
            }

            emit(Resource.Loading(false))
        }
    }

    override suspend fun getNotification(id: Int): Flow<Resource<Notification>> {
        return flow {

        }
    }


    private suspend fun insertNotificationPayloadWithRelations(
        notificationPayloadWithRelations:
        NotificationPayloadWithRelations
    ) {
        notificationDao.insertNotifications(listOf(notificationPayloadWithRelations.notification))
        notificationPayloadDao.insertNotificationPayloads(listOf(notificationPayloadWithRelations.notificationPayload))
    }
}