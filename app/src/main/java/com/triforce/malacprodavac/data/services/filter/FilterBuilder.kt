package com.triforce.malacprodavac.data.services.filter

import kotlin.reflect.full.declaredMemberProperties

class FilterBuilder<T> {

    companion object {
        fun <T : Any> buildFilterQueryMap(filter: Filter<T>): MutableMap<String, String> {
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

        private fun <T : Any> buildQueryString(
            paramName: String,
            array: List<SingleFilterOrOrder<T>>
        ): MutableMap<String, String>? {
            val parts = mutableMapOf<String, String>()

            array.forEachIndexed { i, it ->
                if (it is SingleFilter<T>) {
                    for (prop in SingleFilter::class.declaredMemberProperties) {
                        val key = prop.name
                        val value = prop.get(it)
                        if (value is List<*> && value.isEmpty()) {
                            parts["${paramName}[${i}][${key}]"] = ""
                        } else if (value is List<*>) {value.forEachIndexed { j, jt ->
                            parts["${paramName}[${i}][${key}][${j}]"] =  jt?.toString() ?: ""
                        }
                        } else {
                            parts["${paramName}[${i}][${key}]"] = value?.toString() ?: ""
                        }
                    }
                } else if (it is SingleOrder<T>) {
                    for (prop in SingleOrder::class.declaredMemberProperties) {
                        val key = prop.name
                        val value = prop.get(it)
                        if (value is List<*> && value.isEmpty()) {
                            parts["${paramName}[${i}][${key}]"] = ""
                        } else if (value is List<*>) {value.forEachIndexed { j, jt ->
                            parts["${paramName}[${i}][${key}][${j}]"] =  jt?.toString() ?: ""
                        }
                        } else {
                            parts["${paramName}[${i}][${key}]"] = value?.toString() ?: ""
                        }
                    }
                }
            }
            if (parts.isEmpty())
                return null
            return parts
        }

    }
}