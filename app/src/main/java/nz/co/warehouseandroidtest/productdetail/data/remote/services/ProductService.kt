package nz.co.warehouseandroidtest.productdetail.data.remote.services

import kotlinx.coroutines.Deferred
import nz.co.warehouseandroidtest.productdetail.data.models.ProductDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ProductService {
    @JvmSuppressWildcards
    @GET("bolt/price.json")
    fun getProductPriceAsync(@QueryMap paramMap: Map<String, Any>): Deferred<Response<ProductDetailsResponse>>

}