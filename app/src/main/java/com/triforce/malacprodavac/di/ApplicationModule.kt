package com.triforce.malacprodavac.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.triforce.malacprodavac.data.local.MalacProdavacDatabase
import com.triforce.malacprodavac.data.remote.UserApi
import com.triforce.malacprodavac.data.remote.interceptors.AuthInterceptorImpl
import com.triforce.malacprodavac.data.services.AppSharedPreferences
import com.triforce.malacprodavac.data.services.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    /*  USER API DEPENDENCY CHAIN */
    @Provides
    @Singleton
    fun provideUserApi(): UserApi {
        return Retrofit.Builder()
            .baseUrl(UserApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptorImpl: AuthInterceptorImpl): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(authInterceptorImpl)
            .build()
    @Singleton
    @Provides
    fun provideAuthInterceptorImpl(
        sessionManager: SessionManager
    ): AuthInterceptorImpl = AuthInterceptorImpl(sessionManager)

    /* SESSION MANAGER DEPENDENCY CHAIN */
    @Singleton
    @Provides
    fun provideSessionManager(
        appSharedPreferences: AppSharedPreferences,
    ): SessionManager = SessionManager(appSharedPreferences)

    @Singleton
    @Provides
    fun provideAppSharedPreferences(
        sharedPreferences: SharedPreferences
    ): AppSharedPreferences = AppSharedPreferences(sharedPreferences)

    @Singleton
    @Provides
    fun provideSharedPreferences(
        @ApplicationContext context: Context,
    ): SharedPreferences =
        context.getSharedPreferences(AppSharedPreferences.SHARED_PREFS, Context.MODE_PRIVATE)

    /* DATABASE */
    @Provides
    @Singleton
    fun provideMalacProdavacDatabase(app: Application): MalacProdavacDatabase {
        return Room.databaseBuilder(
            app,
            MalacProdavacDatabase::class.java,
            "malacprodavac.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}