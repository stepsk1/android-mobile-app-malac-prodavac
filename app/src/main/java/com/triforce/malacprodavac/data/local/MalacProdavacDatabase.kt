package com.triforce.malacprodavac.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.triforce.malacprodavac.data.local.category.CategoryDao
import com.triforce.malacprodavac.data.local.category.CategoryEntity
import com.triforce.malacprodavac.data.local.couriers.CourierEntity
import com.triforce.malacprodavac.data.local.customers.CustomerEntity
import com.triforce.malacprodavac.data.local.favoriteProduct.FavouriteProductDao
import com.triforce.malacprodavac.data.local.favoriteProduct.FavouriteProductEntity
import com.triforce.malacprodavac.data.local.favouriteShop.FavouriteShopDao
import com.triforce.malacprodavac.data.local.favouriteShop.FavouriteShopEntity
import com.triforce.malacprodavac.data.local.order.OrderDao
import com.triforce.malacprodavac.data.local.order.OrderEntity
import com.triforce.malacprodavac.data.local.product.ProductDao
import com.triforce.malacprodavac.data.local.product.ProductEntity
import com.triforce.malacprodavac.data.local.shops.ShopEntity
import com.triforce.malacprodavac.data.local.shops.ShopDao
import com.triforce.malacprodavac.data.local.user.UserDao
import com.triforce.malacprodavac.data.local.user.UserEntity
import com.triforce.malacprodavac.data.local.schedulePickups.SchedulePickupsDao
import com.triforce.malacprodavac.data.local.schedulePickups.SchedulePickupsEntity

@Database(
    entities = [
        UserEntity::class,
        CategoryEntity::class,
        ProductEntity::class,
        OrderEntity::class,
        CustomerEntity::class,
        CourierEntity::class,
        FavouriteProductEntity::class,
        ShopEntity::class,
        SchedulePickupsEntity::class,
        FavouriteShopEntity::class
    ],
    version = 1,
    exportSchema = false
)

@TypeConverters(RoomConverters::class)
abstract class MalacProdavacDatabase: RoomDatabase() {
    abstract val userDao: UserDao
    abstract val categoryDao: CategoryDao
    abstract val productDao: ProductDao
    abstract val orderDao: OrderDao
    abstract val favoriteProduct: FavouriteProductDao
    abstract val shopDao: ShopDao
    abstract val schedulePickupsDao: SchedulePickupsDao
    abstract val favoriteShopDao: FavouriteShopDao
}