package com.triforce.malacprodavac.domain.use_case.product

import com.triforce.malacprodavac.domain.repository.products.ProductRepository

class DeleteProduct (

    private val repository: ProductRepository

){

    suspend operator fun invoke(id: Int){

        repository.deleteProduct(id)

    }

}