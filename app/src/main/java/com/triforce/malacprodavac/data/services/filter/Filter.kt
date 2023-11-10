package com.triforce.malacprodavac.data.services.filter

data class Filter<T>(
    val filter: ArrayList<SingleFilterOrOrder<T>>?,
    val order: ArrayList<SingleFilterOrOrder<T>>?,
    val offset: Int?,
    val limit: Int?
)

sealed class SingleFilterOrOrder<T>(
    val field: String? = null,
    val type: FilterOperationType? = null,
    val value: T? = null,
    val dir: FilterOrder? = null
)

class SingleFilter<T>(
    field: String?,
    type: FilterOperationType?,
    value: T?
) : SingleFilterOrOrder<T>(field, type, value, null)

class SingleOrder<T>(
    field: String?,
    dir: FilterOrder?
) : SingleFilterOrOrder<T>(field, null, null, dir)