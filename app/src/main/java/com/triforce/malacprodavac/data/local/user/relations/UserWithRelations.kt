package com.triforce.malacprodavac.data.local.user.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.triforce.malacprodavac.data.local.couriers.CourierEntity
import com.triforce.malacprodavac.data.local.customers.CustomerEntity
import com.triforce.malacprodavac.data.local.shops.ShopEntity
import com.triforce.malacprodavac.data.local.user.UserEntity
import com.triforce.malacprodavac.data.local.user.userMedias.UserMediaEntity


data class UserWithRelations(
    @Embedded
    val user: UserEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val profilePicture: UserMediaEntity?,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val customer: CustomerEntity?,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val courier: CourierEntity?,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val shop: ShopEntity?
)