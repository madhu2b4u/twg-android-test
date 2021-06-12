package nz.co.warehouseandroidtest.productdetail.data.repository


import androidx.lifecycle.liveData
import nz.co.warehouseandroidtest.common.Result
import nz.co.warehouseandroidtest.productdetail.data.remote.source.ProductRemoteDataSource
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProductRemoteDataSource
) : ProductRepository {
    override suspend fun getProductPrice(map: Map<String, Any>) = liveData {
        emit(Result.loading())
        try {
            val response = remoteDataSource.getProductprice(map)
            emit(Result.success(response))

        } catch (exception: Exception) {
            emit(Result.error(exception.message ?: ""))
        }
    }
}