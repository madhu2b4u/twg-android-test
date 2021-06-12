package nz.co.warehouseandroidtest.productdetail.domain

import nz.co.warehouseandroidtest.productdetail.data.repository.ProductRepository
import javax.inject.Inject

class ProductUseCaseImpl @Inject constructor(private val mProductRepository: ProductRepository) :
    ProductUseCase {
    override suspend fun getProductPrice(map: Map<String, Any>) =
        mProductRepository.getProductPrice(map)
}
