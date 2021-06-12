package nz.co.warehouseandroidtest.productdetail.domain

import androidx.lifecycle.LiveData
import nz.co.warehouseandroidtest.common.Result
import nz.co.warehouseandroidtest.productdetail.data.models.ProductDetailsResponse

interface ProductUseCase {
    suspend fun getProductPrice(map : Map<String, Any>): LiveData<Result<ProductDetailsResponse>>

}