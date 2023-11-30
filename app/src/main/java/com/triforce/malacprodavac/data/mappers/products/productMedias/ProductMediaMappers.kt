package com.triforce.malacprodavac.data.mappers.products.productMedias

import com.triforce.malacprodavac.data.local.product.productMedia.ProductMediaEntity
import com.triforce.malacprodavac.domain.model.products.productMedias.ProductMedia

fun ProductMedia.toProductMediaEntity(): ProductMediaEntity = ProductMediaEntity(
    id = id,
    productId = productId,
    originalName = originalName,
    name = name,
    key = key,
    mimetype = mimetype,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun ProductMediaEntity.toProductMedia(): ProductMedia = ProductMedia(
    id = id,
    productId = productId,
    originalName = originalName,
    name = name,
    key = key,
    mimetype = mimetype,
    createdAt = createdAt,
    updatedAt = updatedAt
)