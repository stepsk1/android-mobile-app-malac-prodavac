package com.triforce.malacprodavac.domain.util.filter

data class Filter<T : Any>(
    val filter: List<SingleFilter<T>>?,
    val order: List<SingleOrder<T>>?,
    val offset: Int?,
    val limit: Int?
)

sealed class SingleFilterOrOrder<T : Any>(
    field: String?,
    type: FilterOperation?,
    value: T?,
    dir: FilterOrder?
)

class SingleFilter<T : Any>(
    val field: String,
    val type: FilterOperation,
    val value: T
) : SingleFilterOrOrder<T>(field, type, value, null)

class SingleOrder<T : Any>(
    val field: String,
    val dir: FilterOrder
) : SingleFilterOrOrder<T>(field, null, null, dir)