package com.triforce.malacprodavac.domain.use_case.category

import com.triforce.malacprodavac.domain.model.Category
import com.triforce.malacprodavac.domain.repository.CategoriesRepository
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetCategory(
    private val repository: CategoriesRepository
) {
    suspend operator fun invoke(id: Int, fetchFromRemote: Boolean): Flow<Resource<Category>> {
        return repository.getCategory(id, fetchFromRemote)
    }
}