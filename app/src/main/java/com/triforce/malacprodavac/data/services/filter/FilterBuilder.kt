package com.triforce.malacprodavac.data.services.filter

import android.util.Log
import kotlin.reflect.full.memberProperties

class FilterBuilder<T> {

    companion object {
        fun <T : Any> buildFilterQueryString(filter: Filter<T>): MutableMap<String, String> {
            val parts = mutableMapOf<String, String>()

            if (filter.offset != null) {
                parts["offset"] = "${filter.offset}"
            }
            if (filter.limit != null) {
                parts["limit"] = "${filter.limit}"
            }

            val filterQuery = if (filter.filter != null) FilterBuilder.buildQueryString(
                "filter",
                filter.filter
            ) else null

            if (filterQuery != null) {
                parts.putAll(filterQuery.map { it.key to it.value })
            }

            val orderQuery = if (filter.order != null) FilterBuilder.buildQueryString(
                "order",
                filter.order
            ) else null

            if (orderQuery != null) {
                parts.putAll(orderQuery.map { it.key to it.value })
            }

            return parts
        }

        private fun <T> buildQueryString(
            paramName: String,
            array: ArrayList<SingleFilterOrOrder<T>>
        ): MutableMap<String, String>? {
            val parts = mutableMapOf<String, String>()

            for (index in 0 until array.size) {
                if(array[index] is SingleFilter){
                    for (prop in SingleFilter::class.memberProperties) {
                        val key = prop.name
                        val value = prop.get(array[index] as SingleFilter<*>)
                        if (value is ArrayList<*>) {
                            if (value.isEmpty()) {
                                parts["${paramName}[${index}][${key}]"] = ""
                            }
                        } else {
                            if(value != null)
                                parts["${paramName}[${index}][${key}]"] = value.toString()
                        }
                        Log.d("KEY", key)
                    }
                }

            }
            if (parts.isEmpty())
                return null
            Log.d("PARTS", parts.toString())
            return parts
        }
    }
}