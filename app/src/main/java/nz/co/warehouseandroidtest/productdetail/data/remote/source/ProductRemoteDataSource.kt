package nz.co.warehouseandroidtest.productdetail.data.remote.source

import nz.co.warehouseandroidtest.productdetail.data.models.ProductDetailsResponse


interface ProductRemoteDataSource {


    suspend fun getProductprice(map : Map<String, Any>): ProductDetailsResponse


}