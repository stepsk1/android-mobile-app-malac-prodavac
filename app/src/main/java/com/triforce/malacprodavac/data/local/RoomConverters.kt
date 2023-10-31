package com.triforce.malacprodavac.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.triforce.malacprodavac.domain.model.Courier
import com.triforce.malacprodavac.domain.model.Customer
import com.triforce.malacprodavac.domain.model.Shop
import java.time.LocalDateTime
import javax.inject.Inject

class RoomConverters @Inject constructor(){
    @TypeConverter
    fun fromListOfString(value: List<String>): String{
        val listOfStringType = Types.newParameterizedType(List::class.java, String::class.java)
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter<List<String>>(listOfStringType)
        return adapter.toJson(value)
    }
    @TypeConverter
    fun toListOfString(value: String): List<String>{
        val listOfStringType = Types.newParameterizedType(List::class.java, String::class.java)
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter<List<String>>(listOfStringType)
        return adapter.fromJson(value)!!
    }

    @TypeConverter
    fun timeToString(time: LocalDateTime): String {
        return time.toString()
    }

    @TypeConverter
    fun StringToTime(string: String): LocalDateTime {
        return LocalDateTime.parse(string)
    }

    @TypeConverter
    fun fromCustomer(customer: Customer): String {
        return Gson().toJson(customer)
    }

    @TypeConverter
    fun toCustomer(json: String): Customer {
        return Gson().fromJson(json, Customer::class.java)
    }

    @TypeConverter
    fun fromCourier(courier: Courier): String {
        return Gson().toJson(courier)
    }

    @TypeConverter
    fun toCourier(json: String): Courier {
        return Gson().fromJson(json, Courier::class.java)
    }

    @TypeConverter
    fun fromShop(shop: Shop): String {
        return Gson().toJson(shop)
    }

    @TypeConverter
    fun toShop(json: String): Shop {
        return Gson().fromJson(json, Shop::class.java)
    }
}
