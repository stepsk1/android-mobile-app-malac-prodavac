package com.triforce.malacprodavac.domain.repository

import com.triforce.malacprodavac.domain.model.Category
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {

    suspend fun getCategories(fetchFromRemote: Boolean): Flow<Resource<List<Category>>>
    suspend fun getCategory(id: Int, fetchFromRemote: Boolean): Flow<Resource<Category>>
}