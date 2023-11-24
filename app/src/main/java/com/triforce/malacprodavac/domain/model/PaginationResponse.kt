package com.triforce.malacprodavac.domain.model

data class PaginationMeta(val nzm: Int)
data class PaginationResponse<T>(val data: List<T>)//, val meta: PaginationMeta)
