package com.triforce.malacprodavac.domain.use_case.product

import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.domain.repository.ProductRepository

class DeleteProduct (

    private val repository: ProductRepository

){

    suspend operator fun invoke(id: Int){

        repository.deleteProduct(id)

    }

}