package nz.co.warehouseandroidtest.productdetail.data.repository

import androidx.lifecycle.LiveData
import nz.co.warehouseandroidtest.common.Result
import nz.co.warehouseandroidtest.productdetail.data.models.ProductDetailsResponse


interface ProductRepository {

    suspend fun getProductPrice(map : Map<String, Any>): LiveData<Result<ProductDetailsResponse>>


}