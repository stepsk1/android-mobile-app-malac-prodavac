package com.triforce.malacprodavac.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.triforce.malacprodavac.data.local.MalacProdavacDatabase
import com.triforce.malacprodavac.data.local.RoomConverters
import com.triforce.malacprodavac.data.remote.Api
import com.triforce.malacprodavac.data.remote.auth.AuthApi
import com.triforce.malacprodavac.data.remote.auth.interceptors.AuthInterceptorImpl
import com.triforce.malacprodavac.data.remote.categories.CategoriesApi
import com.triforce.malacprodavac.data.remote.couriers.CouriersApi
import com.triforce.malacprodavac.data.remote.customers.CustomersApi
import com.triforce.malacprodavac.data.remote.orders.OrderApi
import com.triforce.malacprodavac.data.remote.products.ProductsApi
import com.triforce.malacprodavac.data.remote.products.productMedias.ProductMediasApi
import com.triforce.malacprodavac.data.remote.shops.ShopsApi
import com.triforce.malacprodavac.data.remote.users.UsersApi
import com.triforce.malacprodavac.data.remote.users.userMedias.UserMediasApi
import com.triforce.malacprodavac.data.repository.products.productMedias.ProductMediasRepositoryImpl
import com.triforce.malacprodavac.data.services.AppSharedPreferences
import com.triforce.malacprodavac.data.services.SessionManager
import com.triforce.malacprodavac.domain.repository.AuthRepository
import com.triforce.malacprodavac.domain.repository.CourierRepository
import com.triforce.malacprodavac.domain.repository.CustomerRepository
import com.triforce.malacprodavac.domain.repository.OrderRepository
import com.triforce.malacprodavac.domain.repository.ShopRepository
import com.triforce.malacprodavac.domain.repository.products.ProductRepository
import com.triforce.malacprodavac.domain.repository.products.produtMedias.ProductMediasRepository
import com.triforce.malacprodavac.domain.repository.users.userMedias.UserMediasRepository
import com.triforce.malacprodavac.domain.use_case.GetToken
import com.triforce.malacprodavac.domain.use_case.favoriteProduct.AddFavProduct
import com.triforce.malacprodavac.domain.use_case.favoriteProduct.DeleteFavProduct
import com.triforce.malacprodavac.domain.use_case.favoriteProduct.FavoriteProduct
import com.triforce.malacprodavac.domain.use_case.favoriteProduct.GetFavProducts
import com.triforce.malacprodavac.domain.use_case.login.Login
import com.triforce.malacprodavac.domain.use_case.login.LoginUser
import com.triforce.malacprodavac.domain.use_case.login.Me
import com.triforce.malacprodavac.domain.use_case.order.AddOrder
import com.triforce.malacprodavac.domain.use_case.order.DeleteOrder
import com.triforce.malacprodavac.domain.use_case.order.GetAllOrders
import com.triforce.malacprodavac.domain.use_case.order.GetOrderForId
import com.triforce.malacprodavac.domain.use_case.order.Order
import com.triforce.malacprodavac.domain.use_case.order.UpdateOrder
import com.triforce.malacprodavac.domain.use_case.product.AddProductImages
import com.triforce.malacprodavac.domain.use_case.product.GetAllProducts
import com.triforce.malacprodavac.domain.use_case.product.GetProductForId
import com.triforce.malacprodavac.domain.use_case.product.ProductUseCase
import com.triforce.malacprodavac.domain.use_case.profile.Logout
import com.triforce.malacprodavac.domain.use_case.profile.Profile
import com.triforce.malacprodavac.domain.use_case.profile.SetProfilePicture
import com.triforce.malacprodavac.domain.use_case.registration.RegisterCourier
import com.triforce.malacprodavac.domain.use_case.registration.RegisterCustomer
import com.triforce.malacprodavac.domain.use_case.registration.RegisterShop
import com.triforce.malacprodavac.domain.use_case.registration.Registration
import com.triforce.malacprodavac.domain.use_case.schedulePickup.AddSchedulePickup
import com.triforce.malacprodavac.domain.use_case.schedulePickup.GetAllScheduledPickups
import com.triforce.malacprodavac.domain.use_case.schedulePickup.GetSchedulePickupForId
import com.triforce.malacprodavac.domain.use_case.schedulePickup.SchedulePickupUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder().add(
            KotlinJsonAdapterFactory()
        ).build()


    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, client: OkHttpClient): Retrofit =
        Retrofit.Builder().client(client).baseUrl(Api.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()


    /*  USER API DEPENDENCY CHAIN */

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi =
        retrofit.create()

    @Provides
    @Singleton
    fun provideUsersApi(retrofit: Retrofit): UsersApi =
        retrofit.create()

    @Provides
    @Singleton
    fun provideUserMediasApi(retrofit: Retrofit): UserMediasApi =
        retrofit.create()


    @Provides
    @Singleton
    fun provideCustomersApi(retrofit: Retrofit): CustomersApi = retrofit.create()

    @Provides
    @Singleton
    fun provideCategoriesApi(retrofit: Retrofit): CategoriesApi = retrofit.create()

    @Provides
    @Singleton
    fun provideProductsApi(retrofit: Retrofit): ProductsApi = retrofit.create()

    @Provides
    @Singleton
    fun provideCouriersApi(retrofit: Retrofit): CouriersApi = retrofit.create()


    @Provides
    @Singleton
    fun provideShopsApi(retrofit: Retrofit): ShopsApi = retrofit.create()

    @Provides
    @Singleton
    fun provideOrderApi(retrofit: Retrofit): OrderApi = retrofit.create()

    @Provides
    @Singleton
    fun provideProductMediasApi(retrofit: Retrofit): ProductMediasApi = retrofit.create()

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptorImpl: AuthInterceptorImpl): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(authInterceptorImpl)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
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
    fun provideRoomConverters(moshi: Moshi): RoomConverters =
        RoomConverters(moshi)

    @Provides
    @Singleton
    fun provideRegisterCustomer(repository: CustomerRepository) =
        RegisterCustomer(repository)

    @Provides
    @Singleton
    fun provideRegisterCourier(repository: CourierRepository) =
        RegisterCourier(repository)

    @Provides
    @Singleton
    fun provideRegisterShop(repository: ShopRepository) =
        RegisterShop(repository)

    @Provides
    @Singleton
    fun provideRegistrationUseCase(
        registerCustomer: RegisterCustomer,
        registerCourier: RegisterCourier,
        registerShop: RegisterShop
    ) =
        Registration(registerCustomer, registerCourier, registerShop)


    @Provides
    @Singleton
    fun provideLoginUser(repository: AuthRepository) =
        LoginUser(repository)

    @Provides
    @Singleton
    fun provideMe(repository: AuthRepository) =
        Me(repository)

    @Provides
    @Singleton
    fun provideLogoutUseCase(repository: AuthRepository) =
        Logout(repository)

    @Provides
    @Singleton
    fun provideLoginUseCase(loginUser: LoginUser, me: Me) =
        Login(loginUser, me)

    @Provides
    @Singleton
    fun provideGetToke(sessionManager: SessionManager) =
        GetToken(sessionManager)

    @Provides
    @Singleton
    fun provideSetProfilePicture(repository: UserMediasRepository) =
        SetProfilePicture(repository)

    @Provides
    @Singleton
    fun provideProfileUseCase(
        logout: Logout,
        me: Me,
        getToken: GetToken,
        setProfilePicture: SetProfilePicture
    ) =
        Profile(me, logout, getToken, setProfilePicture)

    @Provides
    @Singleton
    fun provideOrderUseCase(
        addOrder: AddOrder,
        deleteOrder: DeleteOrder,
        getAllOrders: GetAllOrders,
        getOrderForId: GetOrderForId
    ) =
        Order(getAllOrders, getOrderForId, addOrder, deleteOrder)

    @Provides
    @Singleton
    fun provideFavProductUseCase(
        addFavProduct: AddFavProduct,
        deleteFavProduct: DeleteFavProduct,
        getFavProducts: GetFavProducts
    ) =
        FavoriteProduct(getFavProducts, deleteFavProduct, addFavProduct)


    @Provides
    @Singleton
    fun provideProductUseCase(
        getAllProducts: GetAllProducts,
        addProductImages: AddProductImages,
        getProductForId: GetProductForId,
        updateOrder: UpdateOrder,
    ) = ProductUseCase(getAllProducts, getProductForId, updateOrder, addProductImages)

    @Provides
    @Singleton
    fun provideUpdateOrderUseCase(repository: OrderRepository) = UpdateOrder(repository)

    @Provides
    @Singleton
    fun provideGetProductUseCase(
        repository: ProductRepository
    ) =
        GetProductForId(repository)

    @Provides
    @Singleton
    fun provideSchedulePickupUseCase(
        addSchedulePickup: AddSchedulePickup,
        getAllScheduledPickups: GetAllScheduledPickups,
        getSchedulePickupForId: GetSchedulePickupForId,
        updateSchedulePickup: com.triforce.malacprodavac.domain.use_case.schedulePickup.UpdateScheduledPickup
    ) =
        SchedulePickupUseCase(
            getAllScheduledPickups,
            getSchedulePickupForId,
            addSchedulePickup,
            updateSchedulePickup
        )

    @Provides
    @Singleton
    fun provideProductMediasRepositoryImpl(
        api: ProductMediasApi,
        db: MalacProdavacDatabase,
        sessionManager: SessionManager
    ): ProductMediasRepository = ProductMediasRepositoryImpl(api, db, sessionManager)

    @Provides
    @Singleton
    fun provideAddProductImagesUseCase(repository: ProductMediasRepository) =
        AddProductImages(repository)

    @Provides
    @Singleton
    fun provideGetProductsUseCase(repository: ProductRepository) =
        GetAllProducts(repository)

    @Provides
    @Singleton
    fun provideMalacProdavacDatabase(
        app: Application,
        converters: RoomConverters
    ): MalacProdavacDatabase =
        Room.databaseBuilder(
            app,
            MalacProdavacDatabase::class.java,
            "malacprodavac.db"
        ).addTypeConverter(converters)
            .fallbackToDestructiveMigration()
            .build()

}