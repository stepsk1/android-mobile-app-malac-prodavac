package com.triforce.malacprodavac.di

import com.triforce.malacprodavac.data.repository.auth.AuthRepositoryImpl
import com.triforce.malacprodavac.data.repository.categories.CategoryRepositoryImpl
import com.triforce.malacprodavac.data.repository.couriers.CourierRepositoryImpl
import com.triforce.malacprodavac.data.repository.customers.CustomerRepositoryImpl
import com.triforce.malacprodavac.data.repository.notifications.NotificationsRepositoryImpl
import com.triforce.malacprodavac.data.repository.notifications.NotificationsSSEClientImpl
import com.triforce.malacprodavac.data.repository.orders.OrderRepositoryImpl
import com.triforce.malacprodavac.data.repository.products.ProductRepositoryImpl
import com.triforce.malacprodavac.data.repository.products.reviews.ReviewsRepositoryImpl
import com.triforce.malacprodavac.data.repository.products.reviews.replies.ReviewRepliesRepositoryImpl
import com.triforce.malacprodavac.data.repository.scheduledPickups.ScheduledPickupsRepositoryImpl
import com.triforce.malacprodavac.data.repository.shops.ShopRepositoryImpl
import com.triforce.malacprodavac.data.repository.users.UserRepositoryImpl
import com.triforce.malacprodavac.data.repository.users.userMedias.UserMediasRepositoryImpl
import com.triforce.malacprodavac.domain.repository.AuthRepository
import com.triforce.malacprodavac.domain.repository.CategoriesRepository
import com.triforce.malacprodavac.domain.repository.CourierRepository
import com.triforce.malacprodavac.domain.repository.CustomerRepository
import com.triforce.malacprodavac.domain.repository.OrderRepository
import com.triforce.malacprodavac.domain.repository.ScheduledPickupRepository
import com.triforce.malacprodavac.domain.repository.ShopRepository
import com.triforce.malacprodavac.domain.repository.notifications.NotificationsRepository
import com.triforce.malacprodavac.domain.repository.notifications.NotificationsSSEClient
import com.triforce.malacprodavac.domain.repository.products.ProductRepository
import com.triforce.malacprodavac.domain.repository.products.reviews.ReviewsRepository
import com.triforce.malacprodavac.domain.repository.products.reviews.replies.ReviewRepliesRepository
import com.triforce.malacprodavac.domain.repository.users.UserRepository
import com.triforce.malacprodavac.domain.repository.users.userMedias.UserMediasRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindsUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindsUserMediasRepository(
        userMediaRepositoryImpl: UserMediasRepositoryImpl
    ): UserMediasRepository

    @Binds
    @Singleton
    abstract fun bindsCustomerRepository(
        customerRepositoryImpl: CustomerRepositoryImpl
    ): CustomerRepository

    @Binds
    @Singleton
    abstract fun bindsCourierRepository(
        courierRepositoryImpl: CourierRepositoryImpl
    ): CourierRepository

    @Binds
    @Singleton
    abstract fun bindsShopRepository(
        shopRepositoryImpl: ShopRepositoryImpl
    ): ShopRepository

    @Binds
    @Singleton
    abstract fun bindsCategoryRepository(
        categoryRepositoryImpl: CategoryRepositoryImpl
    ): CategoriesRepository

    @Binds
    @Singleton
    abstract fun bindsProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindsOrderRepository(
        orderRepositoryImpl: OrderRepositoryImpl
    ): OrderRepository

    @Binds
    @Singleton
    abstract fun bindsSchedulePickupRepository(
        scheduledPickupsRepositoryImpl: ScheduledPickupsRepositoryImpl
    ): ScheduledPickupRepository

    @Binds
    @Singleton
    abstract fun bindsNotificationsRepository(
        notificationsRepositoryImpl: NotificationsRepositoryImpl
    ): NotificationsRepository

    @Binds
    @Singleton
    abstract fun bindReviewsRepository(
        reviewsRepositoryImpl: ReviewsRepositoryImpl
    ): ReviewsRepository


    @Binds
    @Singleton
    abstract fun bindReviewRepliesRepository(
        reviewRepliesRepositoryImpl: ReviewRepliesRepositoryImpl
    ): ReviewRepliesRepository

    @Binds
    @Singleton
    abstract fun bindNotificationsSSEClient(
        notificationsSSEClientImpl: NotificationsSSEClientImpl
    ): NotificationsSSEClient
}