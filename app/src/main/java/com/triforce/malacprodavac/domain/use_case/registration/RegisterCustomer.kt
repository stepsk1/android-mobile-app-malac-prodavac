package com.triforce.malacprodavac.domain.use_case.registration

import com.triforce.malacprodavac.domain.model.CreateCustomer
import com.triforce.malacprodavac.domain.model.Customer
import com.triforce.malacprodavac.domain.repository.CustomerRepository
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class RegisterCustomer(private val repository: CustomerRepository) {

    suspend operator fun invoke(createCustomer: CreateCustomer): Flow<Resource<Customer>> {
        return repository.registerCustomer(createCustomer)
    }
}