package nz.co.warehouseandroidtest.productdetail.data.remote.source

import kotlinx.coroutines.withContext
import nz.co.warehouseandroidtest.di.qualifiers.IO
import nz.co.warehouseandroidtest.productdetail.data.remote.services.ProductService
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class ProductRemoteDataSourceImpl @Inject constructor(
    private val service: ProductService,
    @IO private val context: CoroutineContext
) : ProductRemoteDataSource {
    override suspend fun getProductprice(map: Map<String, Any>) = withContext(context) {

        val response = service.getProductPriceAsync(map).await()
        if (response.isSuccessful)
            response.body()?: throw Exception("no response")
        else
            throw Exception("invalid request with code ${response.code()}")
    }

}
