package com.triforce.malacprodavac.data.repository.notifications

import com.triforce.malacprodavac.data.local.MalacProdavacDatabase
import com.triforce.malacprodavac.data.local.notifications.relations.NotificationPayloadWithRelations
import com.triforce.malacprodavac.data.mappers.notifications.notificationPayload.toNotification
import com.triforce.malacprodavac.data.mappers.notifications.toNotificationPayloadWithRelations
import com.triforce.malacprodavac.data.remote.notifications.NotificationsApi
import com.triforce.malacprodavac.data.services.SessionManager
import com.triforce.malacprodavac.domain.model.notifications.Notification
import com.triforce.malacprodavac.domain.repository.notifications.NotificationsRepository
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
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

    override suspend fun subscribe(): Flow<Resource<String>> {
        return flow {
            coroutineScope {
                val resp = api.subscribe().execute()
                resp.isSuccessful.let {
//                    try {
//                        while (isActive) {
//                            val line = input?.readLine() ?: continue
//                            if (line.startsWith("data:")) {
//                                try {
//                                    emit(Resource.Success(data = line.toString()))
//                                } catch (e: Exception) {
//                                    e.printStackTrace()
//                                }
//                            }
//                        }
//                    } catch (e: IOException) {
//                        e.printStackTrace()
//                    } finally {
//                        input?.close()
//                    }
                }
            }

            emit(Resource.Loading(false))
        }
    }

    override suspend fun getNotifications(): Flow<Resource<List<Notification>>> {
        return flow {
            emit(Resource.Loading(true))
            val authUserId = sessionManager.getAuthUserId()
            val notifications =
                notificationPayloadDao.findNotificationPayloadsWithRelations(authUserId)

            emit(Resource.Success(data = notifications.map { it.toNotification() }))

            val remoteNotificationsResp = try {
                api.getNotifications()
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
                emit(Resource.Success(data = it.data))
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