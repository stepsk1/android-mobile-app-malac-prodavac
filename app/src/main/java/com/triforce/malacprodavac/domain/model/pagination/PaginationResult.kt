package com.triforce.malacprodavac.domain.model.pagination


data class PaginationResult<T>(val data: List<T>, val meta: PaginationMeta)
