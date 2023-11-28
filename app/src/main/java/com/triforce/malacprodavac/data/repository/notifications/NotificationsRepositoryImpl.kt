package com.triforce.malacprodavac.data.repository.notifications

import com.triforce.malacprodavac.data.local.MalacProdavacDatabase
import com.triforce.malacprodavac.data.remote.notifications.NotificationsApi
import com.triforce.malacprodavac.data.services.SessionManager
import com.triforce.malacprodavac.domain.repository.notifications.NotificationsRepository
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationsRepositoryImpl @Inject constructor(
    private val api: NotificationsApi,
    private val db: MalacProdavacDatabase,
    private val sessionManager: SessionManager
) : NotificationsRepository {
    override suspend fun subscribe(): Flow<Resource<Unit>> {
        return flow {
            api.subscribe()
        }
    }
}