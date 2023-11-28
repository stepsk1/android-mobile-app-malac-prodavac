package com.triforce.malacprodavac.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.triforce.malacprodavac.data.local.category.CategoryDao
import com.triforce.malacprodavac.data.local.category.CategoryEntity
import com.triforce.malacprodavac.data.local.couriers.CourierDao
import com.triforce.malacprodavac.data.local.couriers.CourierEntity
import com.triforce.malacprodavac.data.local.customers.CustomerDao
import com.triforce.malacprodavac.data.local.customers.CustomerEntity
import com.triforce.malacprodavac.data.local.favoriteProduct.FavouriteProductDao
import com.triforce.malacprodavac.data.local.favoriteProduct.FavouriteProductEntity
import com.triforce.malacprodavac.data.local.favouriteShop.FavouriteShopDao
import com.triforce.malacprodavac.data.local.favouriteShop.FavouriteShopEntity
import com.triforce.malacprodavac.data.local.order.OrderDao
import com.triforce.malacprodavac.data.local.order.OrderEntity
import com.triforce.malacprodavac.data.local.product.ProductDao
import com.triforce.malacprodavac.data.local.product.ProductEntity
import com.triforce.malacprodavac.data.local.scheduledPickup.ScheduledPickupDao
import com.triforce.malacprodavac.data.local.scheduledPickup.ScheduledPickupEntity
import com.triforce.malacprodavac.data.local.shops.ShopDao
import com.triforce.malacprodavac.data.local.shops.ShopEntity
import com.triforce.malacprodavac.data.local.user.UserDao
import com.triforce.malacprodavac.data.local.user.UserEntity
import com.triforce.malacprodavac.data.local.user.userMedias.UserMediaDao
import com.triforce.malacprodavac.data.local.user.userMedias.UserMediaEntity

@Database(
    entities = [
        UserEntity::class,
        UserMediaEntity::class,
        CustomerEntity::class,
        CourierEntity::class,
        ShopEntity::class,
        ProductEntity::class,
        CategoryEntity::class,
        OrderEntity::class,
        FavouriteProductEntity::class,
        ScheduledPickupEntity::class,
        FavouriteShopEntity::class
    ],
    version = 1,
    exportSchema = false
)

@TypeConverters(RoomConverters::class)
abstract class MalacProdavacDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val userMediaDao: UserMediaDao
    abstract val customerDao: CustomerDao
    abstract val courierDao: CourierDao
    abstract val shopDao: ShopDao
    abstract val productDao: ProductDao
    abstract val categoryDao: CategoryDao
    abstract val orderDao: OrderDao
    abstract val schedulePickupsDao: ScheduledPickupDao
    abstract val favoriteProduct: FavouriteProductDao
    abstract val favoriteShopDao: FavouriteShopDao
}