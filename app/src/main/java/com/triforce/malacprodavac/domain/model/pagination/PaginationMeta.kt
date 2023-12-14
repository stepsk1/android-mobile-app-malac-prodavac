package com.triforce.malacprodavac.domain.model.pagination

data class PaginationMeta(
//offset pagination
    val isFirstPage: Boolean = true,
    val isLastPage: Boolean = true,
    val currentPage: Int = 1,
    val previousPage: Int? = null,
    val nextPage: Int? = null,
    val pageCount: Int = 0,
    val totalCount: Int = 0,
//    val hasNextPage: Boolean,  //!cursor pagination
//    val hasPreviousPage: Boolean,
//    val startCursor: String,
//    val endCursor: String
)